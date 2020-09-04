package com.example.project.Model;

public class Submit {
    private String firstName;
    private String lastName;
    private String email;
    private String projectUrl;

    public Submit(String pFirstName, String pLastName, String pEmail, String pProjectUrl) {
        firstName = pFirstName;
        lastName = pLastName;
        email = pEmail;
        projectUrl = pProjectUrl;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String pFirstName) {
        firstName = pFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String pLastName) {
        lastName = pLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String pEmail) {
        email = pEmail;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void projectUrl(String pProjectUrl) {
        projectUrl = pProjectUrl;
    }
}
