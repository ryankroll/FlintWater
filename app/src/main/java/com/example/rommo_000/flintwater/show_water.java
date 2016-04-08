package com.example.rommo_000.flintwater;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class show_water extends FragmentActivity implements OnMapReadyCallback
         {

    private GoogleMap mMap;

    // get a reference to firebase database
    Firebase ref = new Firebase("https://incandescent-fire-5642.firebaseio.com/WaterAddress");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_water);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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

        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng flint = new LatLng(43.0100, -83.6900);
        //mMap.addMarker(new MarkerOptions().position(flint).title(getString(R.string.flint_marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(flint));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            Log.d("Location: ", "User does not have permission");
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(flint, 12));

        //mMap.setOnInfoWindowClickListener(this);
        //Place markers on map
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    WaterAddress address = postSnapshot.getValue(WaterAddress.class);
                    int x = (int) address.getZipCode();
                    mMap.addMarker((new MarkerOptions().position((new LatLng(address.getLatitude
                            (), address.getLongitude()))).title(address.getLocation())).snippet
                            (address.getAddress()+ ", "  + address.getCity() +  " " +address
                                    .getState() + ", " + address.getZipCode()));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("Failure: ", firebaseError.getMessage());
            }
        });
    }
    /*@Override
    public void onInfoWindowClick(Marker marker) {
        Log.d("Success: ", "Info windows was clicked");
        Intent intent = new Intent(this, address_info.class);
        intent.putExtra("EXTRA_LOCATION", marker.getTitle());
        intent.putExtra("EXTRA_KEY", marker.getSnippet());
        startActivity(intent);
    }

    class customInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        WaterAddress address = new WaterAddress();

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View v = getLayoutInflater().inflate(R.layout.info_window_layout, null);
            LatLng latLng = marker.getPosition();

            TextView titleTextView = (TextView) v.findViewById(R.id.title);
            TextView addressTextView = (TextView) v.findViewById(R.id.address);

            titleTextView.setText(marker.getTitle());
            addressTextView.setText(address.getAddress());
            return v;
        }
    }
    */

}