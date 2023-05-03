package com.safar.bloodbankmanagementsystem.model;

import com.google.firebase.firestore.GeoPoint;

public class User {
    String id, name, mobileNumber, bloodGroup, email;
    long time;
    double lat, lang;

    GeoPoint geoPoint;

    public User() {
    }

    public User(String id, String name, String mobileNumber, String bloodGroup, String email, long time, double lat, double lang) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.bloodGroup = bloodGroup;
        this.email = email;
        this.time = time;
        this.lat = lat;
        this.lang = lang;
        this.geoPoint = new GeoPoint(lat, lang);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }
}
