package ir.papiloo.fives;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;



import java.io.ByteArrayOutputStream;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;




public class Home extends AppCompatActivity {

    /* Views */
    TextView bestTxt;


    /* Variables */
    SharedPreferences prefs;
    MarshMallowPermission mmp = new MarshMallowPermission(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        // Hide ActionBar
        getSupportActionBar().hide();

        // Hide Status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // Init views
        bestTxt = (TextView)findViewById(R.id.hBestTxt);
        bestTxt.setTypeface(Configs.juneGull);



        // Get Best Score
        prefs = PreferenceManager.getDefaultSharedPreferences(Home.this);
        Configs.bestScore = prefs.getInt("bestScore", Configs.bestScore);
        bestTxt.setText(String.valueOf(Configs.bestScore));





        // MARK: - PLAY BUTTON ------------------------------------
        Button playFa = (Button)findViewById(R.id.btnFa);
        Button playSem = (Button)findViewById(R.id.btnSemnani);
        Button playSan = (Button)findViewById(R.id.btnSangesari);
        Button playMaz = (Button)findViewById(R.id.btnMazani);
        Button btnAbout = (Button)findViewById(R.id.btnAbout);
        Button berate = (Button)findViewById(R.id.btnrate);

        //**************
        playFa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home.this, GameBoardFa.class));
            }});
        //**************
        playSem.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(Home.this, GameBoardSem.class));
        }});
        //**************
        playSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, GameBoardSan.class));
            }});
        //**************
        playMaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, GameBoardMaz.class));
            }});
        //**************
        btnAbout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, About.class));
            }});

        berate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mmp.IS_INTERNET_AVAILABLE(Home.this)) {
                    // Do your stuff
                    // your codes
                    final String PACKAGE_NAME = getPackageName();
                    //String url="https://www.papiloo.ir/documentation/Games/Fives/Papiloo.apk";
                    String url= "myket://comment?id=" + getPackageName();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this);
                    alertDialog.setTitle("اینترنت");
                    alertDialog.setMessage("اینترنت وصل نیست");
                    alertDialog.setIcon(R.drawable.logo);
                    alertDialog.setPositiveButton("خروج از بازی",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    alertDialog.setNegativeButton("درباره ما",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(Home.this, About.class));
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }

    // end onCreate()
  @Override

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    // Method to get URI of a stored image
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "image", null);

        return Uri.parse(path);
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(Home.this);
        alertDialog1.setTitle("نظر دادن");
        alertDialog1.setMessage("خوشتان آمد؟ نظر دهید");
        alertDialog1.setIcon(R.drawable.logo);
        alertDialog1.setPositiveButton("خروج",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alertDialog1.setNegativeButton("نظر دادن",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (mmp.IS_INTERNET_AVAILABLE(Home.this)) {
                            // Do your stuff
                            // your codes
                            final String PACKAGE_NAME = getPackageName();
                            //String url="https://www.papiloo.ir/documentation/Games/Fives/Papiloo.apk";
                            String url= "myket://comment?id=" + getPackageName();
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this);
                            alertDialog.setTitle("اینترنت");
                            alertDialog.setMessage("اینترنت وصل نیست");
                            alertDialog.setIcon(R.drawable.logo);
                            alertDialog.setPositiveButton("خروج از بازی",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    });
                            alertDialog.setNegativeButton("درباره ما",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(Home.this, About.class));
                                        }
                                    });
                            alertDialog.show();
                        }
                    }
                });
        alertDialog1.show();
    }
}
// @end
