package com.example.rommo_000.flintwater;

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
            // Defualt Constructor
        }

        public double getLongitude() {return longitude;}

        public double getLatitude() {return latitude;}

        public String getLocation() {return location;}

        public String getCity() {return city;}

        public String getState() {return state;}

        public double getZipCode() {return zipCode;}

        public String getAddress() {return address;}
    }
