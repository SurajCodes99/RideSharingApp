package com.geektrust.backend.entities;

public class Driver {
    private final String driverId;
    private final double xCoordinate;
    private final double yCoordinate;
    private RideStatus driverStatus;

    public Driver(String driverId, double xCoordinate, double yCoordinate){
        this.driverId = driverId;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.driverStatus = RideStatus.WAITING;
    }

    public String getDriverId() {
        return driverId;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public RideStatus getDriverStatus() {
        return driverStatus;
    }

    public void startRide() {
        this.driverStatus = RideStatus.STARTED;
    }

    public void endRide() {
        this.driverStatus = RideStatus.WAITING;
    }
}
