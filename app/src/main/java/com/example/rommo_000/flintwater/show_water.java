package com.example.rommo_000.flintwater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class show_water extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    // get a reference to firebase database
    Firebase ref = new Firebase("https://incandescent-fire-5642.firebaseio.com/WaterAddress");

    //Query queryRef = ref.orderByChild("Name");
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
        mMap.addMarker(new MarkerOptions().position(flint).title(getString(R.string.flint_marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(flint));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(flint, 13));

        mMap.setOnInfoWindowClickListener(this);
        //Place markers on map
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    WaterAddress address = postSnapshot.getValue(WaterAddress.class);
                    mMap.addMarker((new MarkerOptions().position((new LatLng(address.getLatitude
                            (), address.getLongitude()))).title(address.getLocation())));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("Failure: ", firebaseError.getMessage());
            }
        });

    }
    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.d("Success: ", "Info windows was clicked");
        Intent intent = new Intent(this, address_info.class);
        intent.putExtra("EXTRA_LOCATION", marker.getTitle());
        startActivity(intent);
    }

}