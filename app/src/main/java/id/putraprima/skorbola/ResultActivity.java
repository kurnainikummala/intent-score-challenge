package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView resultText, messageText, scorerText;
    String result, messages, scorer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.result);
        messageText = findViewById(R.id.winner);
        scorerText = findViewById(R.id.result_name);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            result = bundle.getString("result");
            messages = bundle.getString("messages");
            scorer = bundle.getString("scorer");

            scorerText.setText(scorer);
            messageText.setText(messages);
            resultText.setText(result);
            System.out.println("Okeee  " + scorer);
        }
    }
}
