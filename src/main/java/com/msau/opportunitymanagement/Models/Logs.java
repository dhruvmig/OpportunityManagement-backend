package com.msau.opportunitymanagement.Models;


public class Logs {
    private int LogId;
    private String action;
    private String userId;
    private int opportunityId;
    private String dateTime;
    private String name;
    private String oldOpp;
    private String newOpp;

    public Logs(){}

    public Logs(int logId, String action, String userId, int opportunityId, String dateTime, String name, String oldOpp, String newOpp) {
        LogId = logId;
        this.action = action;
        this.userId = userId;
        this.opportunityId = opportunityId;
        this.dateTime = dateTime;
        this.name = name;
        this.oldOpp = oldOpp;
        this.newOpp = newOpp;
    }

    public String getOldOpp() {
        return oldOpp;
    }

    public void setOldOpp(String oldOpp) {
        this.oldOpp = oldOpp;
    }

    public String getNewOpp() {
        return newOpp;
    }

    public void setNewOpp(String newOpp) {
        this.newOpp = newOpp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("model name is "+name);
        this.name = name;
    }

    public int getLogId() {
        return LogId;
    }

    public void setLogId(int logId) {
        LogId = logId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(int opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "LogId=" + LogId +
                ", action='" + action + '\'' +
                ", userId='" + userId + '\'' +
                ", opportunityId=" + opportunityId +
                ", dateTime='" + dateTime + '\'' +
                ", name='" + name + '\'' +
                ", oldOpp=" + oldOpp +
                ", newOpp=" + newOpp +
                '}';
    }
}
