package com.example.armour;

import static com.example.armour.MainActivity.redirectActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AboutArmourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_armour);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.settings) {
            redirectActivity(this, SettingsActivity.class);

        } else if (itemId == R.id.log_out) {
            redirectActivity(this, LoginActivity.class);

        }
        else if (itemId == R.id.home) {
            redirectActivity(this, MainActivity.class);

        }
        else if (itemId == R.id.share) {
        }
        return true;
    }
}