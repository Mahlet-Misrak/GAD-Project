package com.example.project.Model;

public class Learners {
    private String  name;
    private int hours;
    private String country;
    private String badgeUrl;

    public Learners(String pName, int pHours, String pCountry, String pBadgeUrl) {
        name = pName;
        hours = pHours;
        country = pCountry;
        badgeUrl = pBadgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int pHours) {
        hours = pHours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String pCountry) {
        country = pCountry;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String pBadgeUrl) {
        badgeUrl = pBadgeUrl;
    }
}
