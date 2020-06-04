package com.example.grow;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channel1ID = "channel1ID";
    public static final String channel1name = "channel 1";

    private NotificationManager mManager;

    //Constructor that get the context
    public NotificationHelper(Context base) {
        super(base);
        //The notification on support API higher than Oreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannels();
        }
    }

    //Create the channel of notification
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {
        //Create with the name and id of the channel
        NotificationChannel channel1 =  new NotificationChannel(channel1ID, channel1name, NotificationManager.IMPORTANCE_DEFAULT);
        //Setting up some features of the notification in this channel
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.colorPrimary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);
    }

    //get the notification manager from the service of the device
    public NotificationManager getManager() {
        if(mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    //Build the notification
    public NotificationCompat.Builder getChannel1Notification(String title, String message) {
        return new NotificationCompat.Builder(getApplicationContext(), channel1ID)
                //Setting some features inside the notification
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_1);
    }
}

