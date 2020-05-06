package ir.papiloo.words;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class SendWord extends AppCompatActivity {
    public static final String URI_SHOW_PARAMS = "https://Papiloo.ir/Papiloo/Game/Insert.php?Language=سنگسری&Word=چاور&Mean=چادر&Pronounce=چاوَر";

    Spinner category;
    TextView Word,MeanWord, pronounce;
    Button btnSend;

    private String EncodingUtils;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_word);
        getSupportActionBar().hide();

        category= findViewById(R.id.selectWord);
        Word=(TextView)findViewById(R.id.txtWord);
        MeanWord= (TextView)findViewById(R.id.txtMeansWord);
        pronounce =findViewById(R.id.pronounce);
        btnSend= (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String word, mean,category;
                word= Word.getText().toString();
                mean=MeanWord.getText().toString();
                category=
                try
                {

                    MyHttpUtils.RequestData requestData =
                            new MyHttpUtils.RequestData(URI_SHOW_PARAMS,"GET");
                    //requestData.setParameter("name","AmirRasooli");
                    //requestData.setParameter("message","HelloAmir");

                    //new HttpParamsActivity.MyTask().execute(requestData);

                }
                catch (Exception e)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(SendWord.this);
                    alertDialog.setTitle("نظر");
                    alertDialog.setMessage("بزودی فعال میشود");
                    alertDialog.setIcon(R.drawable.logo);
                    alertDialog.setPositiveButton("خروج ",
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
//                             alertDialog.setNegativeButton("درباره ما",
//                            new DialogInterface.OnClickListener()
//                            {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    startActivity(new Intent(Home.this, About.class));
//                                }
//                            });
                    alertDialog.show();
                }

            }
        });
    };


}
