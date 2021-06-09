package com.apllication.myapplication;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();

                        markerOptions.position(latLng);

                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                        mMap.clear();
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

                        mMap.addMarker(markerOptions);
                    }
                });

            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng telkom = new LatLng(-6.9740733, 107.6296707);
        mMap.addMarker(new MarkerOptions().position(telkom).title("Marker in Telkom University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(telkom));



//        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
//        if (location!=null){
//            Toast.makeText(this, "Lokasi Ditemukan", Toast.LENGTH_SHORT).show();
//            LatLng MyLocation = new LatLng(location.getLatitude(), location.getLongitude());
//            mMap.addMarker(new MarkerOptions().position(MyLocation).title("Latitude: "+location.getLatitude()).snippet("Longitude : "+location.getLongitude()));
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MyLocation,13));
//        }else{
//            Toast.makeText(this, "Lokasi Tidak Di temukan", Toast.LENGTH_SHORT).show();
//        }
    }
}