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

    public Trip(String tripId, int tripAmount, double destinationXCoordinate,Driver driver,
                double destinationYCoordinate, double timeTaken, Rider rider, RideStatus rideStatus) {
        this.tripId = tripId;
        this.tripAmount = tripAmount;
        this.destinationXCoordinate = destinationXCoordinate;
        this.driver = driver;
        this.destinationYCoordinate = destinationYCoordinate;
        this.timeTaken = timeTaken;
        this.rider = rider;
        this.rideStatus = rideStatus;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
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

    public void setDestinationXCoordinate(double destinationXCoordinate) {
        this.destinationXCoordinate = destinationXCoordinate;
    }

    
    public Driver getDriver() {
        return driver;
    }

    public double getDestinationYCoordinate() {
        return destinationYCoordinate;
    }

    public void setDestinationYCoordinate(double destinationYCoordinate) {
        this.destinationYCoordinate = destinationYCoordinate;
    }

    public double getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(double timeTaken) {
        this.timeTaken = timeTaken;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }
}
