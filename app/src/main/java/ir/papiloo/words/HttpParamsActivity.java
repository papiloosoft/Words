package ir.papiloo.words;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HttpParamsActivity extends AppCompatActivity {

    public static final String URI_SHOW_PARAMS = "https://Papiloo.ir/Papiloo/Game/Insert.php?Language=سنگسری&Word=چاور&Mean=چادر&Pronounce=چاوَر";

    TextView tv,showUrl;
    ProgressBar pb;
    List<AsyncTask> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_params);
        tv = findViewById(R.id.tv);
        //showUrl=findViewById(R.id.showUrl);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item1=menu.add("GET");
        item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                MyHttpUtils.RequestData requestData =
                        new MyHttpUtils.RequestData(URI_SHOW_PARAMS,"GET");
                requestData.setParameter("name","AmirRasooli");
                requestData.setParameter("message","HelloAmir");


                //showUrl.setText(requestData.toString());
                new MyTask().execute(requestData);



                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    public class  MyTask extends AsyncTask<MyHttpUtils.RequestData,Void ,String>
    {

        @Override
        protected void onPreExecute()
        {
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
                result = "null";

            }
            tv.setText(result);
            tasks.remove(this);
            if(tasks.isEmpty())
            {
                pb.setVisibility(View.INVISIBLE);
            }
        }
    }
}
