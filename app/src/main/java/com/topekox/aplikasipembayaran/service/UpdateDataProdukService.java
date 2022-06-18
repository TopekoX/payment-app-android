package com.topekox.aplikasipembayaran.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.topekox.aplikasipembayaran.PembayaranConstans;
import com.topekox.aplikasipembayaran.R;
import com.topekox.aplikasipembayaran.activity.DashboardActivity;
import com.topekox.aplikasipembayaran.activity.MainActivity;
import com.topekox.aplikasipembayaran.dao.ProdukDao;
import com.topekox.aplikasipembayaran.restclient.PembayaranClient;

public class UpdateDataProdukService extends Service {

    private static final String TAG = "UPDATE_PRODUK_SERVICE";
    private LocalBroadcastManager broadcastManager;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                broadcastManager = LocalBroadcastManager.getInstance(UpdateDataProdukService.this);
                // kita juga bisa hapus semua data produk di database sebelum diupdate
                updateDataProduk();
                tampilkanNotifikasi();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void updateDataProduk() {
        ProdukDao produkDao = new ProdukDao(getApplicationContext());
        PembayaranClient pembayaranClient = new PembayaranClient();
        Log.i(TAG, "Mengambil data produk");
        pembayaranClient.updateDataProduk(getApplicationContext());

        broadcastManager.sendBroadcast(
                new Intent(PembayaranConstans.INTENT_UPDATE_PRODUK));
    }

    private void tampilkanNotifikasi() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        int idNotifikasi = 10;
        String channelId = "My Channel ID";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_baseline_message_24)
                        .setContentTitle("Update Produk")
                        .setContentText("Ada update produk...")
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        Intent intentMain = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intentMain);
        PendingIntent pi = stackBuilder.getPendingIntent(idNotifikasi, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pi);
        notificationBuilder.setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(idNotifikasi, notificationBuilder.build());
    }
}
