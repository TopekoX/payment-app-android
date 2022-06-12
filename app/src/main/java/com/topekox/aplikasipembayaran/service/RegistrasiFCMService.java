package com.topekox.aplikasipembayaran.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.topekox.aplikasipembayaran.restclient.PembayaranClient;

public class RegistrasiFCMService extends Service {

    private final String TAG = "REGISTRASI_FCM_SERVICE";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Log.w(TAG, "Registrasi FCM Service Is Running...");
                        String email = intent.getStringExtra("EMAIL");
                        String token = intent.getStringExtra("TOKEN");
                        Log.w(TAG, "email: " + email +", token: " + token);

                        try {
                            if (email != null && token != null) {
                                PembayaranClient pembayaranClient = new PembayaranClient();
                                pembayaranClient.registrasiToken(email, token);
                                Log.w(TAG, "Mengirim Data Token...");
                            }
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            Log.w(TAG, e.getMessage());
                        }
                    }
                }
        ).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
