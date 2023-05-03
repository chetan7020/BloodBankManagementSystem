package com.safar.bloodbankmanagementsystem.model;

public class BloodBank {
    String bankID, bankName, bloodGroup;
    int availablePackets;

    public BloodBank() {
    }

    public BloodBank(String bankID, String bankName, String bloodGroup, int availablePackets) {
        this.bankID = bankID;
        this.bankName = bankName;
        this.bloodGroup = bloodGroup;
        this.availablePackets = availablePackets;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getAvailablePackets() {
        return availablePackets;
    }

    public void setAvailablePackets(int availablePackets) {
        this.availablePackets = availablePackets;
    }
}
