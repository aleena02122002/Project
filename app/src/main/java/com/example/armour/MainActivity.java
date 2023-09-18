package com.example.armour;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements SensorEventListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private SensorManager sensorManager;
    private Sensor mysensor;
    private long lastupdate, actualtime;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    ConstraintLayout signUpRelat;
    private Activity MainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnlocation = findViewById(R.id.location);
        Button btnalert = findViewById(R.id.alert);
        Button btnself = findViewById(R.id.tips);
        Button btncontacts = findViewById(R.id.contacts);

        btnlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = new Intent(MainActivity.this,MapActivity.class );
                startActivity(intt);
            }
        });

        btnalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = new Intent(MainActivity.this,alert.class );
                startActivity(intt);
            }
        });

        btnself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = new Intent(MainActivity.this,SelfDefenceActivity.class );
                startActivity(intt);
            }
        });
        btncontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = new Intent(MainActivity.this,ContactsActivity.class );
                startActivity(intt);
            }
        });

        //sensor
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mysensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (mysensor == null) {
            Toast.makeText(this, "No Accelerometer detected", Toast.LENGTH_LONG).show();
        } else {
            sensorManager.registerListener(this, mysensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Navigation drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
     /*  if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SafetyTipsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }*/
    }
    //Intent function for activities
    public static void redirectActivity(Activity activity,Class secondActivity){
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
    //Drawer menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {
            redirectActivity(this, com.example.armour.MainActivity.class);
        }else if (itemId == R.id.nav_location) {
                redirectActivity(this,com.example.armour.MapActivity.class);
        }else if (itemId == R.id.nav_alert) {
            redirectActivity(this,com.example.armour.MainActivity.class);
        } else if (itemId == R.id.nav_self_defence) {
            redirectActivity(MainActivity.this, SelfDefenceActivity.class);
        }else if (itemId == R.id.nav_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Women Security");
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } else if (itemId == R.id.nav_log_out) {
            redirectActivity(MainActivity.this, LoginActivity.class);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //Opening & closing of drawer
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //Toolbar menu
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
            redirectActivity(MainActivity.this, LoginActivity.class);

        } else if (itemId == R.id.home) {
            NavUtils.navigateUpFromSameTask(MainActivity);

        } else if (itemId == R.id.share) {
        }
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            float EG = SensorManager.GRAVITY_EARTH;
            float devAccel = (x * x + y * y + z * z) / (EG * EG);

            if (devAccel >= 2.5) {
                actualtime = System.currentTimeMillis();
                if ((actualtime - lastupdate) > 3000) {
                    lastupdate = actualtime;

                    sendsms(null);
                }
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void sendsms(View view) {
        String PHONE_NUMBER = "1234567890";
        String SMS_MESSAGE = "HELP";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(PHONE_NUMBER, null, SMS_MESSAGE, null, null);

        Toast.makeText(this, "SMS Sent", Toast.LENGTH_SHORT).show();
    }
}