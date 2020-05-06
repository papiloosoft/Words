package ir.papiloo.words;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SendWord extends AppCompatActivity {


    Button btnSend;
    TextView word,mean,pronounce,txtResult;
    Spinner category;
    ProgressBar pb;
    List<AsyncTask> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_word);
        word = findViewById(R.id.txtWord);
        mean = findViewById(R.id.txtMeansWord);
        pronounce = findViewById(R.id.pronounce);
        txtResult = findViewById(R.id.result);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        btnSend = findViewById(R.id.btnSend);


        //add value in spinner
        category =(Spinner) findViewById(R.id.selectCategory);
        List<String> list = new ArrayList<String>();
        list.add("فارسی");
        list.add("سمنانی");
        list.add("سنگسری");
        list.add("مازندرانی");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        //____________________
        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String language,w,m,p;
                language= category.getSelectedItem().toString();
                w =  word.getText().toString();
                m = mean.getText().toString();
                p = pronounce.getText().toString();
                String URI_SHOW_PARAMS = "https://Papiloo.ir/Papiloo/Game/Insert.php"+"?"+"Language=" + language + "&Word="+w+"&Mean="+m+"&Pronounce="+p;


                MyHttpUtils.RequestData requestData =
                        new MyHttpUtils.RequestData(URI_SHOW_PARAMS,"GET");
                //requestData.setParameter("name","AmirRasooli");
                new MyTask().execute(requestData);

            }
        });

    }

    public class  MyTask extends AsyncTask<MyHttpUtils.RequestData,Void ,String>
    {

        @Override
        protected void onPreExecute()
        {
            txtResult.setText("");
            if(tasks.isEmpty())
            {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String doInBackground(MyHttpUtils.RequestData... params) {
            MyHttpUtils.RequestData reqData = params[0];
            return  MyHttpUtils.getDataHttpUrlConnection(reqData);
        }

        @Override
        protected void onPostExecute(String result) {

            if (result == null)
            {
                result = "اتصال اینرنت را بررسی کنید";

            }
            txtResult.setText(result);
            tasks.remove(this);
            if(tasks.isEmpty())
            {
                pb.setVisibility(View.INVISIBLE);
            }
        }
    }
}
