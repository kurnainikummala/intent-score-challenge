package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class MatchActivity extends AppCompatActivity implements Serializable {
    private TextView homeName, awayName, homeScore, awayScore, homeScoreName, awayScoreName;
    private ImageView homeLogo, awayLogo;
    Uri uri1, uri2;
    Bitmap bitmap1, bitmap2;
    String homeTeam, awayTeam;
    int scoreHome = 0;
    int scoreAway = 0;
    String returnStringHome;
    String returnStringAway;
    String result, message, scorerName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        homeName = findViewById(R.id.txt_home);
        awayName = findViewById(R.id.txt_away);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        awayScore = findViewById(R.id.score_away);
        homeScore = findViewById(R.id.score_home);
        homeScoreName = findViewById(R.id.resultNameHome);
        awayScoreName = findViewById(R.id.resultNameAway);

        Bundle bundle = getIntent().getExtras();

        homeTeam = bundle.getString("inputHome");
        awayTeam = bundle.getString("inputAway");

        if (bundle != null){
            uri1 = Uri.parse(bundle.getString("logoHome"));
            uri2 = Uri.parse(bundle.getString("logoAway"));
            bitmap1 = null;
            bitmap2 = null;

            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        homeName.setText(homeTeam);
        awayName.setText(awayTeam);
        homeLogo.setImageBitmap(bitmap1);
        awayLogo.setImageBitmap(bitmap2);
    }



    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                scoreHome += 1;
                homeScore.setText(String.valueOf(scoreHome));
                returnStringHome = data.getStringExtra("scorerName");

                String pencetakHome = returnStringHome;
                String pencetakBaruHome = homeScoreName.getText().toString();
                homeScoreName.setText(String.valueOf(pencetakBaruHome + " \n "+pencetakHome));

            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                scoreAway += 1;
                awayScore.setText(String.valueOf(scoreAway));
                returnStringAway = data.getStringExtra("scorerName");

                String pencetakAway = returnStringAway;
                String pencetakBaruAway = awayScoreName.getText().toString();
                awayScoreName.setText(String.valueOf(pencetakBaruAway + " \n " + pencetakAway));
            }
        }
    }

    //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
    public void handleAddHomeScore(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 1);
    }

    public void handleAddAwayScore(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 2);

    }

    //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    public void handleCekHasil(View view) {
        if (scoreHome > scoreAway){
            result = String.valueOf(scoreHome) + " - " + String.valueOf(scoreAway);
            message = homeTeam + " adalah Pemenang";
            scorerName = homeScoreName.getText().toString();
        } else if(scoreHome < scoreAway){
            result = String.valueOf(scoreHome) + " - " + String.valueOf(scoreAway);
            message = awayTeam + " adalah Pemenang";
            scorerName = awayScoreName.getText().toString();
        } else {
            result = String.valueOf(scoreHome) + " - " + String.valueOf(scoreAway);
            message = "DRAW";
            scorerName = "";
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("result", result);
        intent.putExtra("messages", message);
        intent.putExtra("scorer", scorerName);
        startActivity(intent);
    }

}
