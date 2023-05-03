package com.safar.bloodbankmanagementsystem.model;

public class Transactions {
    String userName, bloodGroup, status, type;

    public Transactions() {
    }

    public Transactions(String userName, String bloodGroup, String status, String type) {
        this.userName = userName;
        this.bloodGroup = bloodGroup;
        this.status = status;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
