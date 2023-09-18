package com.example.armour;

import static com.example.armour.MainActivity.redirectActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    TextView myAccount,aboutArmour,logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myAccount = findViewById(R.id.myAccount);
        aboutArmour = findViewById(R.id.aboutArmour);
        logOut = findViewById(R.id.logOut);


        aboutArmour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,AboutArmourActivity.class);
                startActivity(intent);
            }
        });
        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,MyAccountActivity.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
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
            redirectActivity(SettingsActivity.this, LoginActivity.class);

        }
        else if (itemId == R.id.home) {
            redirectActivity(SettingsActivity.this, MainActivity.class);

        }
        else if (itemId == R.id.share) {
        }
        return true;
}
}