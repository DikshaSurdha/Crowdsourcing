package com.example.all.crowdsourcing;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


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
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if(mMap == null)
        {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        if(mMap != null){
            setUpMap();
        }
    }


    private void setUpMap() {
        //enable location
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
            mMap.setMyLocationEnabled(true);

            //Get locationmanager object from system service
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            //Create criteria object to retrieve provider
            Criteria criteria = new Criteria();

            //Get the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            //Get current location
            Location myLocation = locationManager.getLastKnownLocation(provider);

            //set map type
            mMap.setMapType(mMap.MAP_TYPE_HYBRID);

            //GET LATITUDE OF THE CURRENT LOCATION
            double latitude = myLocation.getLatitude();

            //Get longitude of the current location
            double longitude = myLocation.getLongitude();

            //Create a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);

            //Show the current location in Google Map
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            //Zoom in the map
            mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
            mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        }

    public void onSearch(View view) {
        //fetch the text value entered by the user
        EditText location_tf = (EditText) findViewById(R.id.TFaddress);
        //convert the value to string
        String location = location_tf.getText().toString();
        List<Address> addressList = null;
        //check if the user has entered anything:if location is not equal to null or if location is not equal to any string
        if (location != null || !location.equals("")) {
            //Convert the address into latitude and longitude
            Geocoder geocoder = new Geocoder(this);
            //Generate latitude and longitude
            try {
                // 1 is the maximum number of address
                //Must be stored in a variable that why we use a list
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }

            android.location.Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
            //zoom on the location using the camera
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }

    }

    public void changeType(View view) {
        //check if the map type is normal
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
            //if map type is equal to normal then change it to satellite
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID
            );
        } else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void onReport(View view) {
        Intent i = new Intent(this, Report.class);
        startActivity(i);
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
        public void onMapReady (GoogleMap googleMap){
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-34, 151);

            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        }
    }
