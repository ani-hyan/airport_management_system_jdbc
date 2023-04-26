package org.example.entity;

import java.sql.Timestamp;

public class Trip {
    private int tripId;
    private int companyId;
    private String airplane;
    private String townFrom;
    private String townTo;
    private Timestamp timeOut;
    private Timestamp timeIn;

    public Trip(int tripId, int companyId, String airplane, String townFrom, String townTo, Timestamp timeOut, Timestamp timeIn) {
        this.tripId = tripId;
        this.companyId = companyId;
        this.airplane = airplane;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
    }

    public Trip() {
    }

    public Trip(int triId) {
        this.tripId = triId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(String airplane) {
        this.airplane = airplane;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Timestamp timeOut) {
        this.timeOut = timeOut;
    }

    public Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Timestamp timeIn) {
        this.timeIn = timeIn;
    }

    @Override
    public String toString() {
        return  tripId + " " + companyId + " " + airplane + " " + townFrom + " " + townTo + " " + timeOut + " " + timeIn;
    }
}
