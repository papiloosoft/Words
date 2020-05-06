package ir.papiloo.words;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AsyncTaskIntroActivity extends AppCompatActivity {

    public static final String SAMPLE_URL="https://www.papiloo.ir/documentation/Games/Words/strings.xml";

    ProgressBar pb;
    TextView tv;
    List<TaskGetData> tasks =new ArrayList<TaskGetData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_intro);

        pb= (ProgressBar) findViewById(R.id.progressBar);
        tv=findViewById(R.id.tv);
        tv.setMovementMethod(new ScrollingMovementMethod());
        findViewById(R.id.btn_intro).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                new TaskIntro()
                        //.execute("bank","shopping","hospital");
                        .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"1","2","3","4");
            }
        });

        findViewById(R.id.btn_get_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TaskGetData().execute(SAMPLE_URL);
            }
        });



    }


    public class TaskIntro extends AsyncTask<String,String,String>
    {


        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
            tv.append("starting ... \n");
        }

        @Override
        protected String doInBackground(String... params) {

             for(String p : params)
             {
                 try
                 {
                     Thread.sleep(1000);

                 }
                 catch (InterruptedException e)
                 {
                     e.printStackTrace();
                 }

                 publishProgress("Working with params : " + p);
             }

            return "done";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            tv.append(values[0] + "\n");
        }

        @Override
        protected void onPostExecute(String result) {
            tv.append(result + "\n");
            tasks.remove(this);
            if(tasks.isEmpty())
            {
                pb.setVisibility(View.INVISIBLE);
            }
        }
    }




    public class  TaskGetData extends  AsyncTask<String , String ,String>
    {

        @Override
        protected void onPreExecute() {
            if(tasks.isEmpty()){
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
            tv.append("Get Data ...\n\n");
        }

        @Override
        protected String doInBackground(String... params) {
            return MyHttpUtils.getDataHttpClient(params[0]);

        }


        @Override
        protected void onPostExecute(String result) {
            tasks.remove(this);
            if (tasks.isEmpty())
            {
                pb.setVisibility(View.INVISIBLE);
            }


            tv.append(result + "\n");



        }
    }
}
