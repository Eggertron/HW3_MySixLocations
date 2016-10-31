package com.example.eggertron.hw3_mysixlocations;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by eggertron on 10/20/16.
 */
public class GPSManager implements LocationListener {

    MainActivity mainActivity;
    LocationManager locationManager;
    String LOCATIONPROVIDER = LocationManager.NETWORK_PROVIDER;

    public GPSManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        locationManager = (LocationManager)mainActivity.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        LOCATIONPROVIDER = locationManager.getBestProvider(criteria, true);
    }

    //register the location
    public void register() {

        if (ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LOCATIONPROVIDER, 5000, 0, this); //need to implement
        //get location based on last known
        mainActivity.updateGPSLocation(locationManager.getLastKnownLocation(LOCATIONPROVIDER));
    }

    public void unregister() {
        if (ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) { //pass location to main activity
        mainActivity.updateGPSLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) { //when user turns on

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
