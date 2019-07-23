package com.example.fit4me11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Context context;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    TextView callorieTextview, stepCountTextview;
    Button statsButton, rankButton;

    Thread viewThread;
    boolean viewThreadRunning = true;


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        setupUI();

        mAuth = FirebaseAuth.getInstance();

        updateUI();
        viewThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted() && viewThreadRunning) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateUI();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        viewThread.start();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                    finish();
                }
            }
        };
    }

    void setupUI() {
        callorieTextview = (TextView) findViewById(R.id.main_layout_callorie_textview);

        statsButton = (Button) findViewById(R.id.main_layout_stats_button);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity_a.class));
            }
        });
        rankButton = (Button) findViewById(R.id.main_layout_rank_button);
        rankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity_b.class));
            }
        });
    }

    void updateUI() {

        SharedPreferences calorieMapPref = MainActivity.this.getSharedPreferences(SharedPrefData.CALORIEMAP, MODE_PRIVATE);
        callorieTextview.setText("" + calorieMapPref.getInt(new Day().toString(), 0));



    }
    void signOut() {
        viewThreadRunning = false;
        SharedPrefData sharedPrefData = new SharedPrefData(MainActivity.this);
        sharedPrefData.clear();
        Intent intent = new Intent(MainActivity.this, FirebaseUploadService.class);
        stopService(intent);
        mAuth.signOut();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewThreadRunning = false;
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.menu_track_workout:
                startActivity(new Intent(this, Activity_d.class));
                return true;
            case R.id.menu_online_workout:
                startActivity(new Intent(this, Activity_e.class));
                return true;
            case R.id.menu_my_workouts:
                startActivity(new Intent(this, Activity_f.class));
                return true;

            case R.id.menu_info:
                startActivity(new Intent(this, AdditionalInfoInputActivity.class));
                return true;
            case R.id.menu_signout:
                signOut();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
