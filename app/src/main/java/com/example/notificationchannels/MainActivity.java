package com.example.notificationchannels;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edtwatchMovie;
    private NotificationHandler notificationHandler;
    private static final int WATCH_MOVIE_NOTIFICATION_ID = 1000;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationHandler = new NotificationHandler(MainActivity.this);


        edtwatchMovie = findViewById(R.id.edtwatchmovie);
        findViewById(R.id.btnwatchmovie).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                postNotificationToUserDevice(WATCH_MOVIE_NOTIFICATION_ID,edtwatchMovie.getText().toString());
            }
        });
        findViewById(R.id.btnmoviesettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationettimngs(NotificationHandler.WATCH_MOVIE_NOTIFICATIONCHANNEL_ID);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void postNotificationToUserDevice(int notificationID, String titleTExt){
        Notification.Builder notificationbuilder = null;
        switch (notificationID) {
            case WATCH_MOVIE_NOTIFICATION_ID:
            notificationbuilder = notificationHandler.createAndReturnWatchMovieNotification(titleTExt,
                    "this is a good movie");
            break;
        }
        if (notificationbuilder!= null){
            notificationHandler.notifyTheUser(WATCH_MOVIE_NOTIFICATION_ID,notificationbuilder);
        }
    }
    private void notificationettimngs(String watchmoviechannel){
        Intent settingsintent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        settingsintent.putExtra(Settings.EXTRA_APP_PACKAGE,getPackageName());
        settingsintent.putExtra(Settings.EXTRA_CHANNEL_ID,watchmoviechannel);
        startActivity(settingsintent);
    }
}