package com.geektrust.backend.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.entities.Driver;

public class DriverRepository implements IDriverRepository{
    HashMap<String, Driver> driverMap; 

    public DriverRepository(){
        this.driverMap = new HashMap<>();
    }
    @Override
    public Driver save(Driver entity) {
        String id = entity.getDriverId();
        double xCoordinate = entity.getxCoordinate();
        double yCoordinate = entity.getyCoordinate();

        Driver newDriver = new Driver(id, xCoordinate, yCoordinate);
        driverMap.put(id, newDriver);

        return newDriver;
    }

    @Override
    public List<Driver> findAll() {
        List<Driver> allDrivers = new ArrayList<>();
        for(Map.Entry<String, Driver> entry: driverMap.entrySet()) allDrivers.add(entry.getValue());
        return allDrivers;
    }

    @Override
    public boolean existsById(String id) { return driverMap.containsKey(id);}

    @Override
    public Driver getDriverDetails(String driverId) {
        Driver currentDriver = driverMap.get(driverId);
        return currentDriver;
    }
    
}
