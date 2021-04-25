package com.msau.opportunitymanagement.Models;

public class Logs {
    private int LogId;
    private String action;
    private String userId;
    private int opportunityId;
    private String dateTime;

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
                ", opportunityId='" + opportunityId + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
