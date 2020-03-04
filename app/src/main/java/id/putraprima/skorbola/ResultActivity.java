package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView resultText, messageText, scoreText;
    String result, message, score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.result);
        messageText = findViewById(R.id.winner);
        scoreText = findViewById(R.id.result_name);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            result = bundle.getString("result");
            message = bundle.getString("message");
            score =  bundle.getString("score");

            scoreText.setText(score);
            messageText.setText(message);
            resultText.setText(result);
            System.out.println("OK"+score);
        }
    }
}
