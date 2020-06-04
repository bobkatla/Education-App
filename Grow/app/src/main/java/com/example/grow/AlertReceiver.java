package com.example.grow;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

//Extend BroadcastReceiver to push the notification
public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //This is a custom class build
        NotificationHelper notificationHelper = new NotificationHelper(context);
        //input the message wish to show on the notification
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification("From Grow with love", "time to do your work");
        notificationHelper.getManager().notify(1, nb.build());
    }
}
