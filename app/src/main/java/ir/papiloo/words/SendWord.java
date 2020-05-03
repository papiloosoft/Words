package ir.papiloo.words;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;



public class SendWord extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_word);

        getSupportActionBar().hide();


    };

}
