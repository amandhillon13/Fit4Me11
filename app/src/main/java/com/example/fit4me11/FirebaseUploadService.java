package com.example.fit4me11;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class FirebaseUploadService extends Service {

    public static final String TAG = "FirebaseUploadService";
    public static final int UPLOAD_DELAY = 10000;

    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    SharedPrefData sharedPrefData;
    UploaderThread uploaderThread;

    class UploaderThread extends Thread {
        boolean threadRunning = true;

        @Override
        public void run() {
            while (threadRunning) {


                synchronized (mDatabase) {
                    if (sharedPrefData.getUser() != null){
                        User user=sharedPrefData.getUser();
                        mDatabase.child("userData").setValue(user.userData);
                        mDatabase.child("userInfo").setValue(user.getUserInfo());
                        Log.d(TAG, sharedPrefData.getUser().toString());
                    }

                }
                try {
                    sleep(UPLOAD_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }



    @Override
    public void onCreate() {
        super.onCreate();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
        sharedPrefData = new SharedPrefData(FirebaseUploadService.this);
        uploaderThread = new UploaderThread();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uploaderThread.threadRunning = false;
        Log.d(TAG, "destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        uploaderThread.start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
