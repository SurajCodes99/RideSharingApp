package com.geektrust.backend.entities;

import java.util.List;

public class Rider {
    private final String riderId;
    private final double xCoordinate;
    private final double yCoordinate;
    private RideStatus riderStatus;
    private List<Driver> matchedDrivers;

    public Rider(Rider rider) {
        this(rider.riderId, rider.xCoordinate, rider.yCoordinate);
        this.riderStatus = RideStatus.WAITING;
    }

    public Rider(String riderId, double xCoordinate, double yCoordinate) {
        this.riderId = riderId;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.riderStatus = RideStatus.WAITING;
    }

    public String getRiderId() {
        return riderId;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public RideStatus getRiderStatus() {
        return riderStatus;
    }

    public void setRiderStatus(RideStatus riderStatus) {
        this.riderStatus = riderStatus;
    }

    public List<Driver> getMatchedDrivers() {
        return matchedDrivers;
    }

    public void setMatchedDrivers(List<Driver> matchedDrivers) {
        this.matchedDrivers = matchedDrivers;
    }
}
