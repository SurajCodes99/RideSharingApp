package com.geektrust.backend.entities;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import com.geektrust.backend.constants.Constants;

public class Rider {
    private final String riderId;
    private final double xCoordinate;
    private final double yCoordinate;
    private RideStatus riderStatus;
    private List<Driver> matchedDrivers;

    public Rider(String riderId, double xCoordinate, double yCoordinate) {
        this.riderId = riderId;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.matchedDrivers = new ArrayList<>();
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

    public List<Driver> getMatchedDrivers() {
        return matchedDrivers;
    }

    public void setMatchedDrivers(List<Driver> driversInRadius) {
        Set<Driver> matchedDriversSet = new LinkedHashSet<>();
        for(int i = Constants.ZERO; i <= Constants.MAX_DRIVER_COUNT && i < driversInRadius.size() ; i++) {
            if(driversInRadius.get(i).getDriverStatus() == RideStatus.WAITING) 
                matchedDriversSet.add(driversInRadius.get(i));
        }
        this.matchedDrivers = List.copyOf(matchedDriversSet);
    }

    public void setRiderStatusAsStarted() {
        this.riderStatus = RideStatus.STARTED;
    }

    public void  riderStatusWaiting(){
        this.riderStatus = RideStatus.WAITING;
    }
}
