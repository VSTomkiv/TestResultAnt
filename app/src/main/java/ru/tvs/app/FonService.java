package ru.tvs.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import ru.tvs.app.ui.AWork;


/**
 * Created with IntelliJ IDEA.
 * User: Vlad
 */
public class FonService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initNotificationIntent();

        return START_STICKY;
    }



    private void initNotificationIntent() {

        Intent notificationIntent = new Intent(this, AWork.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent contentIntent = PendingIntent.getActivity(this
                , 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        boolean whiteIcon = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(contentIntent)
                .build();
        notification.flags |= Notification.FLAG_NO_CLEAR;

        startForeground(1, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}