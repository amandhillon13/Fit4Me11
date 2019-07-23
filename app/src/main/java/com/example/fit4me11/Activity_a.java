package com.example.fit4me11;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.List;
import java.util.Vector;

public class Activity_a extends AppCompatActivity {
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;

    List<Event> events=new Vector<Event>();

    RecyclerView recyclerView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        textView=(TextView)findViewById(R.id.textView);

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

        SharedPrefData sharedPrefData = new SharedPrefData(Activity_a.this);
        sharedPrefData.clear();
        Intent intent = new Intent(Activity_a.this, FirebaseUploadService.class);
        stopService(intent);
        mAuth.signOut();
    }
}
