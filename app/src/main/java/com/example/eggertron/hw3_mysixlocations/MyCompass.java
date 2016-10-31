package com.example.eggertron.hw3_mysixlocations;

import android.location.Location;

/**
 * Created by eggertron on 10/29/16.
 */
public class MyCompass {

    double angle;
    int lastLocation;
    Location currentLocation;
    Locations locationData;

    // this six locations
    Location[] locations;

    public void setAngle(double angle) {
        //if (Math.abs(this.angle - angle) > 15) {
            this.angle = angle;
            lastLocation = getClosestLocation();
        //}
    }

    public void setCurrentLocation(Location location) {
        currentLocation = location;
    }

    public MyCompass() {
        locationData = new Locations();
        // initialize all the locations.
        locations = new Location[locationData.names.length];
        for (int i = 0; i < locations.length; i++) {
            locations[i] = new Location("");
            locations[i].setLatitude(locationData.latitudes[i]);
            locations[i].setLongitude(locationData.longitudes[i]);
        }
    }

    public double getDistance(Location location) {
        if (currentLocation != null) {
            return currentLocation.distanceTo(location); // in meters
        }
        else {
            return 0;
        }
    }

    public String getDescription() {
        return locationData.descriptions[lastLocation];
    }

    public String getDistance() {
        return "Distance: " + getDistance(locations[lastLocation]) +
                "\nangle: " + angle + "\nLocation angle: " +
                getAngle(locations[lastLocation]) +
                "\nIndex: " + lastLocation;
    }

    public String getMp3() {
        return locationData.mp3s[lastLocation];
    }

    public int getMap() {
        return locationData.maps[lastLocation];
    }

    public int getImg() {
        return locationData.images[lastLocation];
    }

    public int getClosestLocation() {
        int i, result = 0;
        double dAngle, angle = getAngle(locations[0]);
        for (i = 1; i < locations.length; i++) {
            dAngle = getAngle(locations[i]);
            if (dAngle > 180) {
                dAngle = 360 - dAngle;
            }
            if (angle > dAngle) {
                angle = dAngle;
                result = i;
            }
        }
        return result;
    }

    public double getAngle(Location location) {
        if (currentLocation != null) {
            double mapAngle = currentLocation.bearingTo(location); //calculates bearing angle
            if (mapAngle < 0 ) {
                mapAngle += 360;
            }
            return Math.abs(mapAngle - angle); //subtract bearing from phone angle
        }
        else {
            return 0;
        }
    }
}
