package com.msau.opportunitymanagement.Models;

import java.io.Serializable;

public class Opportunity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int id;
    private String ED;
    private String skills;
    private String date;
    private String location;
    private String description;
    private String createdBy;
    private String status;
    private String updatedAt;

    public Opportunity() { }

    public Opportunity(int id,String ED,String skills, String date,String location,String description,String createdBy) {
        this.id=id;
        this.ED=ED;
        this.skills = skills;
        this.date = date;
        this.location = location;
        this.description = description;
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
        return "Opportunity{" +
                "id=" + id +
                ", ED='" + ED + '\'' +
                ", skills='" + skills + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", status='" + status + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}