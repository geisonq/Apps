package br.edu.ifsul.meuwebapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;


public class LocationGPS {

    LocationManager locationManager;

    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS status
    boolean canGetLocation = false;

    double latitude; // latitude
    double longitude; // longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    private Context context;

    public LocationGPS(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.context = context;
    }

    public boolean checkLocation() {
        if (!isLocationEnabled()) {
            showAlert();
            Log.i("GPS", "OFF");
        } else {
            Log.i("GPS", "ON");
        }
        return isLocationEnabled();
    }


    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Ativar Localização")
                .setMessage("Sua configuração de localização esta 'Desativada'.\nAtive a Localização " +
                        "para usar o app.")
                .setPositiveButton("Ativar Localização", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    public double getLatitude() {
        latitude = 0.0;
        try {
            if (locationManager != null) {
                Location location = locationManager
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return latitude;
    }

    public double getLongitude() {
        longitude = 0.0;
        try {
            if (locationManager != null) {
                Location location = locationManager
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    longitude = location.getLongitude();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return longitude;
    }

    private final LocationListener locationListenerGPS = new LocationListener() {

        public void onLocationChanged(Location location) {
            Log.d("GPS", "Long" + location.getLongitude());
            double longitudeGPS = location.getLongitude();

           /* longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    longitudeValueGPS.setText(longitudeGPS + "");
                    latitudeValueGPS.setText(latitudeGPS + "");
                    Toast.makeText(MainActivity.this, "GPS Provider update", Toast.LENGTH_SHORT).show();
                }
            });*/
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


}
