package com.geektrust.backend.services;

import java.util.List;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Trip;

public interface ITripService{
    //To start the ride:
    public Trip startRide(String rideId, int driverSelection, String riderId, List<Driver> driversInRadius);

    //To stop the ride:
    public Trip stopRide(String rideId, double xCoordinate, double yCoordinate, double timeTaken);

    //To calculate the bill:
    public Trip calculateBill(String tripId);
}
