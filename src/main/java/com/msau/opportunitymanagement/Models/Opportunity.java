package com.msau.opportunitymanagement.Models;

import java.io.Serializable;

public class Opportunity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String ED;
    private String skills;
    private String date;
    private String location;

    public Opportunity() { }

    public Opportunity(Long id,String ED,String skills, String date,String location) {
        this.id=id;
        this.ED=ED;
        this.skills = skills;
        this.date = date;
        this.location = location;
    }

    public String getED() {
        return ED;
    }
    public void setED(String eD) {
        ED = eD;
    }
    public String getSkills() {
        return skills;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }
    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", ED='" + ED + '\'' +
                ", skills='" + skills + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                '}';

    }
}