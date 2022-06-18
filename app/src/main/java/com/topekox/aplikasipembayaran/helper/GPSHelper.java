package com.topekox.aplikasipembayaran.helper;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.util.List;

public class GPSHelper extends Service implements LocationListener {

    private static final String TAG = "GPS_SERVICE";
    private Context context;

    public GPSHelper(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Location getLocation() {
        LocationManager locationManager =
                (LocationManager) context.getSystemService(LOCATION_SERVICE);

        if (locationManager == null) {
            Log.w(TAG, "Location Manager Tidak Aktif");
        }

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 3 * 60 * 1000, 10, this
            );

            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } else {
            Log.d(TAG, "GPS tidak menyala");
            return null;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}
