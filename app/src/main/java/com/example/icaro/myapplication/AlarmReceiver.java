package com.example.icaro.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID =0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm running", Toast.LENGTH_SHORT).show();
        int id=0;
         id=id++;
            addNotification(context,intent,id);


    }
    private void addNotification(Context context,Intent intent,int id) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle(intent.getStringExtra("tipo"))
                        .setContentText(intent.getStringExtra("nome"+" "+intent.getStringExtra("hora")));

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
