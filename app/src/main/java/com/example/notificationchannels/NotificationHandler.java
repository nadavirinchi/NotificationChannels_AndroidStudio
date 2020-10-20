package com.example.notificationchannels;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class NotificationHandler extends ContextWrapper {
    private NotificationManager notificationManager;
    public static final String WATCH_MOVIE_NOTIFICATIONCHANNEL_ID = "watchmovienotificationchannelID";
    public static final String WATCH_MOVIE_NOTIFICATIONCHANNEL_NAME = "watchmovienotificationchannelName";
    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationHandler(Context context){
        super(context);
        CreateNotificationChannel();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void CreateNotificationChannel(){
        NotificationChannel notificationChannel = new NotificationChannel(WATCH_MOVIE_NOTIFICATIONCHANNEL_ID,
                WATCH_MOVIE_NOTIFICATIONCHANNEL_NAME,notificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setShowBadge(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getNotificationManager().createNotificationChannel(notificationChannel);
    }
    private NotificationManager getNotificationManager(){
        if (notificationManager == null){
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder createAndReturnWatchMovieNotification(String titleText, String bodyText){
       return new Notification.Builder(getApplicationContext(),WATCH_MOVIE_NOTIFICATIONCHANNEL_ID).
               setContentTitle(titleText).setContentText(bodyText).setAutoCancel(true).setSmallIcon(R.drawable.watchmovie) ;
    }
    public void notifyTheUser(int notificationID, Notification.Builder notficationbuilder){
        getNotificationManager().notify(notificationID,notficationbuilder.build());
    }
}
