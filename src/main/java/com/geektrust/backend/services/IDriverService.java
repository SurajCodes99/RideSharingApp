package com.geektrust.backend.services;

import java.util.List;
import com.geektrust.backend.entities.Driver;

public interface IDriverService {
    //To add the driver
    public Driver addDriver(String driverId, double xCoordinate, double yCoordinate);

    //To return a list of drivers who are in the 5km radius.
    public List<Driver> driversInRadius(String riderId);

    //To get the drivers that were matched with a specific driver.
    public List<Driver> getDriversInRadius(String riderId);
}
