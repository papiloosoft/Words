package ir.papiloo.words;

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
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class Home extends AppCompatActivity {

    /* Views */
    TextView bestTxt;
    ListView mList;
    ArrayList<Item> arrayItem;
    RadioButton radChar3,radChar4,radChar5;
    BottomNavigationView bottomNav;

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

        //test send to site
        Button btntest=(Button) findViewById(R.id.testSite);
        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, SendWord.class));

            }
        });
        // Init views
        bestTxt = (TextView)findViewById(R.id.hBestTxt);
        bestTxt.setTypeface(Configs.juneGull);
        //RadioButtom
        radChar3 =(RadioButton) findViewById(R.id.char3);
        radChar4 = (RadioButton) findViewById(R.id.char4);
        radChar5 = findViewById(R.id.char5);
        //BottonNavigation
        bottomNav=findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                if(Item.getItemId()==R.id.nav_home)
                {
                    return true;
                }
                if(Item.getItemId()==R.id.nav_about)
                {
                    startActivity(new Intent(Home.this, About.class));
                    return true;
                }
                if (Item.getItemId()==R.id.nav_idea)
                {
                    startActivity(new Intent(Home.this, Idea.class));
                    return true;

                }
                return false;
            }
        });


        // Get Best Score
        prefs = PreferenceManager.getDefaultSharedPreferences(Home.this);
        Configs.bestScore = prefs.getInt("bestScore", Configs.bestScore);
        bestTxt.setText(String.valueOf(Configs.bestScore));

        //*******************************
        mList = findViewById(R.id.list_view);

        arrayItem = new ArrayList<>();

        CustomAdapter mAdapter = new CustomAdapter(this, arrayItem);

        itemDetails();

        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, "Item Number " + i , Toast.LENGTH_SHORT).show();
                if(i==0) {
                    if(radChar5.isChecked())
                    {
                        startActivity(new Intent(Home.this, GameBoardFa.class));
                    }
                    if(radChar4.isChecked())
                    {
                        startActivity(new Intent(Home.this, GameBoardFa4.class));
                    }
                    if(radChar3.isChecked())
                    {
                        startActivity(new Intent(Home.this,GameBoardFa3.class));
                    }
                }
                if(i==1) {
                    if(radChar5.isChecked())
                    {
                        startActivity(new Intent(Home.this, GameBoardSem.class));
                    }
                }
                if(i==2) {
                    if (radChar5.isChecked())
                    {
                        startActivity(new Intent(Home.this, GameBoardSan.class));
                    }
                }
                if(i==3) {
                    if (radChar5.isChecked()) {
                        startActivity(new Intent(Home.this, GameBoardMaz.class));
                    }
                }

            }
        });

    }

    // create other language in list
    public void itemDetails() {

//        arrayItem.add(new Item("ico_fa", "فارسی", "----", "----"));
//        arrayItem.add(new Item("ico_sem", "سمنانی", "----", "----"));
//        arrayItem.add(new Item("ico_san", "سنگسری", "----", "----"));
//        arrayItem.add(new Item("ico_maz", "مازندرانی", "----", "----"));
        String [] wordsArrFa = getResources().getStringArray(R.array.WordsFa);
        arrayItem.add(new Item("ico_fa", Integer.toString(wordsArrFa.length)));

        String [] wordsArrSem = getResources().getStringArray(R.array.WordsSem);
        arrayItem.add(new Item("ico_sem" ,Integer.toString(wordsArrSem.length)));

        String [] wordsArrSan = getResources().getStringArray(R.array.WordsSan);
        arrayItem.add(new Item("ico_san",Integer.toString(wordsArrSan.length)));

        String [] wordsArrMaz = getResources().getStringArray(R.array.WordsMaz);
        arrayItem.add(new Item("ico_maz", Integer.toString(wordsArrMaz.length)));

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
        alertDialog1.setMessage("اگرخوشتان آمد نظر دهید؟");
        alertDialog1.setIcon(R.drawable.logo);
        alertDialog1.setPositiveButton("خروج",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                });
                alertDialog1.setNegativeButton("نظر دادن",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try
                        {
                            if (MarshMallowPermission.IS_INTERNET_AVAILABLE(Home.this)) {
                                // Do your stuff
                                // your codes
                                final String PACKAGE_NAME = getPackageName();
                                //String url="https://www.papiloo.ir/documentation/Games/Fives/Papiloo.apk";
                                String url = "myket://comment?id=" + getPackageName();
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);
                            }
                            else
                                {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this);
                                alertDialog.setTitle("اینترنت");
                                alertDialog.setMessage("اینترنت وصل نیست");
                                alertDialog.setIcon(R.drawable.logo);
                                alertDialog.setPositiveButton("خروج از بازی",
                                        new DialogInterface.OnClickListener()
                                        {
                                            public void onClick(DialogInterface dialog, int which)
                                            {
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
                        catch (Exception e)
                        {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this);
                            alertDialog.setTitle("نظر");
                            alertDialog.setMessage("بزودی فعال میشود");
                            alertDialog.setIcon(R.drawable.logo);
                            alertDialog.setPositiveButton("خروج از بازی",
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
        alertDialog1.show();
    }
}
// @end
