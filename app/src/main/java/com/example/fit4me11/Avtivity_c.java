package com.example.fit4me11;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Avtivity_c extends AppCompatActivity {
    TextView textView;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avtivity_c);
        textView=(TextView)findViewById(R.id.textView4);
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
            case R.id.menu_signout:
                signOut();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    void signOut() {

        SharedPrefData sharedPrefData = new SharedPrefData(Avtivity_c.this);
        sharedPrefData.clear();
        Intent intent = new Intent(Avtivity_c.this, FirebaseUploadService.class);
        stopService(intent);
        mAuth.signOut();
    }
}
