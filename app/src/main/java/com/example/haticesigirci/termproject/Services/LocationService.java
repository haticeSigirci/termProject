package com.example.haticesigirci.termproject.Services;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.content.ContextCompat;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by haticesigirci on 06/12/15.
 */
public class LocationService {

    private final LocationManager mLocationManager;

    private  Context context;

    public LocationService(LocationManager locationManager, Context context) {
        mLocationManager = locationManager;
        this.context = context;
    }

    public Observable<Location> getLocation() {
        return Observable.create(new Observable.OnSubscribe<Location>() {
            @Override
            public void call(final Subscriber<? super Location> subscriber) {

                final LocationListener locationListener = new LocationListener() {
                    public void onLocationChanged(final Location location) {
                        subscriber.onNext(location);
                        subscriber.onCompleted();

                        Looper.myLooper().quit();
                    }

                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    public void onProviderEnabled(String provider) {
                    }

                    public void onProviderDisabled(String provider) {
                    }
                };

                final Criteria locationCriteria = new Criteria();
                locationCriteria.setAccuracy(Criteria.ACCURACY_COARSE);
                locationCriteria.setPowerRequirement(Criteria.POWER_LOW);
                final String locationProvider = mLocationManager
                        .getBestProvider(locationCriteria, true);

                Looper.prepare();

                if (ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                mLocationManager.requestSingleUpdate(locationProvider,
                        locationListener, Looper.myLooper());

                Looper.loop();
            }
        });
    }

}
