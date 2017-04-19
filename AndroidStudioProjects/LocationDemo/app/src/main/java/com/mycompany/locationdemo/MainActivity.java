package com.mycompany.locationdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Location API Demo";
    private TextView Longitude;
    private TextView Latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Longitude = (TextView) findViewById(R.id.longitude);
        Latitude = (TextView) findViewById(R.id.latitude);

        /*
        GPS Check
         */

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }

        LocationListener locationListener = new MyLocationer();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
        }catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    private class MyLocationer implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            //pb.setVisibility(View.INVISIBLE);
            /*Toast.makeText(getBaseContext(), "Location Changed : Lat" + loc.getLatitude() + "lng:"
                    + loc.getLongitude(), Toast.LENGTH_SHORT).show();*/
            String longitude = "longtitude:" + loc.getLongitude();
            Log.d(TAG, longitude);
            Longitude.setText(longitude);
            String latitude = "Latitude:" + loc.getLatitude();
            Log.d(TAG, latitude);
            Latitude.setText(latitude);

            String cityName = null;
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0) {
                    System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String s = longitude + "\n" + latitude + "\n\nMy Current City is: " + cityName;
            Log.v(TAG, s);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d(TAG, provider + " status changed.");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d(TAG, provider + " enabled.");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d(TAG, provider + " disabled.");
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("GPS设置未开启，你想启用他吗?")
                .setCancelable(false)
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();

    }
}
