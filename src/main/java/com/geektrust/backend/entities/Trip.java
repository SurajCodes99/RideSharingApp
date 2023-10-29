package com.geektrust.backend.entities;

public class Trip {
    private String tripId;
    private double tripAmount;
    private double destinationXCoordinate;
    private double destinationYCoordinate;
    private double timeTaken;
    private Rider rider; 
    private Driver driver;
    private RideStatus rideStatus;

    public Trip(String tripId, Driver driver, Rider rider, RideStatus riderStatus){
        this.tripId = tripId;
        this.driver = driver;
        this.rider = rider;
        this.rideStatus = riderStatus;
    }

    public String getTripId() {
        return tripId;
    }

    public double getTripAmount() {
        return tripAmount;
    }

    public void setTripAmount(double tripAmount) {
        this.tripAmount = tripAmount;
    }

    public double getDestinationXCoordinate() {
        return destinationXCoordinate;
    }
    
    public Driver getDriver() {
        return driver;
    }

    public double getDestinationYCoordinate() {
        return destinationYCoordinate;
    }

    public double getTimeTaken() {
        return timeTaken;
    }

    public Rider getRider() {
        return rider;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideAsCompleted() {
        this.rideStatus = RideStatus.COMPLETED;
    }

    public void setDestinationCoordinates(double xCoordinate, double yCoordinate) {
        this.destinationXCoordinate = xCoordinate;
        this.destinationYCoordinate = yCoordinate;
    }

    public void setTimeTaken(double timeTaken) {
        if(timeTaken != 0) this.timeTaken = timeTaken;
    }
}
