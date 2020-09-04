package com.example.project.Model;

public class Skill {
    private String name;
    private int score;
    private String  country;
    private String badgeUrl;

    public Skill(String pName, int pScore, String pCountry, String pBadgeUrl) {
        name = pName;
        score = pScore;
        country = pCountry;
        badgeUrl = pBadgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int pScore) {
        score = pScore;
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
