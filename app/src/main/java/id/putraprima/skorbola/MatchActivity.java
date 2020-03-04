package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {
    TextView homeoutput;
    TextView awayOutput ;
    TextView homeScoreName;
    TextView awayScoreName;
    ImageView img1;
    ImageView img2;
    TextView homeScore;
    TextView awayScore;
    String homeTeam;
    String awayTeam;
    String returnStringHome;
    String returnStringAway;
    String result, message, scoreName;
    Bitmap bitmap1;
    Bitmap bitmap2;
    private static final String Home="home";
    private static final String Away = "away";
    private static final String hasilHome = "hasilhomeScore";
    private static  final String hasilaway = "hasilawayScore";
    private static final String hasilDraw = "hasildraw";
    Button aadScoreHome;
    Button addScoreAway;
    int scorehome=0;
    int scoreaway = 0;
    Uri uri1, uri2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",

            homeoutput = findViewById(R.id.txt_home);
            awayOutput = findViewById(R.id.txt_away);
            img1 = findViewById(R.id.home_logo);
            img2 = findViewById(R.id.away_logo);
            homeScore = findViewById(R.id.score_home);
            awayScore = findViewById(R.id.score_away);
            homeScoreName = findViewById(R.id.scorer);
            awayScoreName= findViewById(R.id.scorer);

            Bundle extras = getIntent().getExtras();
            homeTeam = extras.getString("inputHome");
            awayTeam = extras.getString("inputAway");
            if(extras != null){
                Bundle extra = getIntent().getExtras();
                Bitmap bmp = extra.getParcelable("homeTeamImage");
                img1.setImageBitmap(bmp);
                Bitmap bimp = extra.getParcelable("awayTeamImage");
                img2.setImageBitmap(bimp);
                try{
                    bitmap1= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri1);

                } catch (IOException e){
                    e.printStackTrace();
                }
                try{
                    bitmap2= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri2);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            homeoutput.setText(homeTeam);
            awayOutput.setText(awayTeam);
            img1.setImageBitmap(bitmap1);
            img2.setImageBitmap(bitmap2);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (requestCode == RESULT_OK){
                scorehome +=1;
                homeScore.setText(String.valueOf(scorehome));
                returnStringHome = data.getStringExtra("scoreName");

                String pencetakHome = returnStringHome;
                String pencetakBaruHome = returnStringAway;
                homeScoreName.setText(String.valueOf(pencetakBaruHome+ " \n "+pencetakHome));
            }
        }else if (requestCode ==2){
            if (requestCode == RESULT_OK){
                scoreaway +=1;
                awayScore.setText(String.valueOf(scoreaway));
                returnStringAway=data.getStringExtra("scoreName");
                String pencetakAway = returnStringAway;
                String pencetakBaruAway= awayScoreName.getText().toString();
                awayScoreName.setText(String.valueOf(pencetakBaruAway+" \n "+pencetakAway));
            }
        }
    }

    public void home_score(int score1){
            homeScore.setText(String.valueOf(score1));
        }
        public void away_score(int score2){
            awayScore.setText(String.valueOf(score2));
        }
        public void homeScore(View view) {
            Intent intent = new Intent(this, ScorerActivity.class);
            startActivityForResult(intent, 1);
        }

        public void awayScore(View view) {
            Intent intent = new Intent(this, ScorerActivity.class);
            startActivityForResult(intent, 2);
        }

    public void Hasil(View view) {
        if(scorehome>scoreaway){
            result = String.valueOf(scorehome)+" - "+ String.valueOf(scoreaway);
            message = homeTeam+"Adalah Pemenang";
            scoreName = homeScoreName.getText().toString();
        }else if(scorehome<scoreaway){
            result = String.valueOf(scorehome)+" - "+ String.valueOf(scoreaway);
            message = awayTeam+"Adalah Pemenang";
            scoreName = awayScoreName.getText().toString();
        }else {
            result = String.valueOf(scorehome)+" - "+String.valueOf(scoreaway);
            message="draw";
            scoreName="";
        }

        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("result", result);
        intent.putExtra("massages", message);
        intent.putExtra("scorer",scoreName );
        startActivity(intent);
    }
}


