package com.geektrust.backend.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.repositories.IDriverRepository;
import com.geektrust.backend.repositories.IRiderRepository;

public class DriverService implements IDriverService{
    private IDriverRepository driverRepository;
    private IRiderRepository riderRepository;

    public DriverService(IDriverRepository driverRepository, IRiderRepository riderRepository){
        this.driverRepository = driverRepository;
        this.riderRepository = riderRepository;
    }

    @Override
    public Driver addDriver(String driverId, double xCoordinate, double yCoordinate) {
        Driver newDriver = new Driver(driverId, xCoordinate, yCoordinate);
        return driverRepository.save(newDriver);
    }

    @Override
    public List<Driver> driversInRadius(String riderId) {
        List<Driver> driversInRadius = new ArrayList<>();
        Rider currentRider = riderRepository.getRiderDetails(riderId);

        List<Driver> allDrivers = driverRepository.findAll();

        for(Driver driver : allDrivers){
            double distance = distanceCalculator(driver, currentRider);
            if(distance <= Constants.MAX_RADIUS) driversInRadius.add(driver);
        }
        
        //To sort the drivers based on distance and name:
        sortByDistanceOrName(driversInRadius, currentRider);
        
        //A check is done to make sure that, only the available drivers are added to the list:
        currentRider.setMatchedDrivers(driversInRadius);

        return currentRider.getMatchedDrivers();
    }

    //A comparator to sort the drivers as per their distance. If they are equidistant from the customer, then sorted by name.
    private void sortByDistanceOrName(List<Driver> driversInRadius, Rider currentRider) {
        Collections.sort(driversInRadius,new Comparator<Driver>() {
            @Override
            public int compare(Driver driver1, Driver driver2) {
                double distance1 = distanceCalculator(driver1, currentRider);
                double distance2 = distanceCalculator(driver2, currentRider);

                if(distance1 != distance2) return Double.compare(distance1, distance2);
                else return driver1.getDriverId().compareTo(driver2.getDriverId());
            }
        });
    }

    public List<Driver> getDriversInRadius(String riderId){
        Rider currentRider = riderRepository.getRiderDetails(riderId);
        return currentRider.getMatchedDrivers();
    }

    public double distanceCalculator(Driver driver, Rider rider){
            double currentRiderXCoordinate = rider.getxCoordinate();
            double currentRiderYCoordinate = rider.getyCoordinate();
            double currentDriverXCoordinate = driver.getxCoordinate();
            double currentDriverYCoordinate = driver.getyCoordinate();

            double xDistance = Math.pow(currentRiderXCoordinate - currentDriverXCoordinate, Constants.TWO);
            double yDistance = Math.pow(currentRiderYCoordinate - currentDriverYCoordinate, Constants.TWO);

            double distance = Math.sqrt(xDistance + yDistance);
            return distance;
    }
}
