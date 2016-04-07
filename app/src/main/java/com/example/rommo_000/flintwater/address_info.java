package com.example.rommo_000.flintwater;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.ChildEventListener;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

public class address_info extends AppCompatActivity {

    Firebase ref = new Firebase("https://incandescent-fire-5642.firebaseio.com/WaterAddress");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String address = intent.getStringExtra("EXTRA_KEY");

        Query queryRef = ref.equalTo(address);
        queryRef.addListenerForSingleValueEvent(new addressValueEventListener());
    }

    class addressValueEventListener implements ValueEventListener {


        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    }


}
