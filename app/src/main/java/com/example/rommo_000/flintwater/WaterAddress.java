package com.example.rommo_000.flintwater;

import android.widget.Toast;

import com.firebase.client.Firebase;

// Class to hold address from firebase.
public class WaterAddress {
        private double latitude;
        private double longitude;
        private String location;
        private String city;
        private String state;
        private String address;
        private long zipCode;

        public WaterAddress(){
                //Defualt Constructor
        }

       public WaterAddress(String location_in, String street_in, String city_in, String state_in, int zipcode, double lat, double lon) {
                latitude = lat;
                longitude = lon;
                location = location_in;
                city = city_in;
                state = state_in;
                address = street_in;
                zipCode = zipcode;
        }

        public double getLongitude() {return longitude;}

        public double getLatitude() {return latitude;}

        public String getLocation() {return location;}

        public String getCity() {return city;}

        public String getState() {return state;}

        public double getZipCode() {return zipCode;}

        public String getAddress() {return address;}

        public void setLongitude(double lng) {longitude = lng;}

        public void setLatitude(double lat) {latitude = lat;}

        public void setLocation(String loc) {location = loc;}

        public void setCity(String c) {city = c;}

        public void setState(String s) {state = s;}

        public void setAddress(String a) {address = a;}

        public void setZipCode(long zc) {zipCode = zc;}
    }
