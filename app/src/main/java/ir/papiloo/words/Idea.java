package ir.papiloo.words;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Idea extends AppCompatActivity {
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idea);
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                if (Item.getItemId() == R.id.nav_home) {
                    startActivity(new Intent(Idea.this, Home.class));
                    finish();
                }
                if (Item.getItemId() == R.id.nav_about) {
                    startActivity(new Intent(Idea.this, About.class));
                    finish();
                }
                if (Item.getItemId() == R.id.nav_idea) {

                }
                return false;
            }
        });
    }


}


