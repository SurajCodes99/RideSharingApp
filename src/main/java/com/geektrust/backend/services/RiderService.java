package com.geektrust.backend.services;

import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.repositories.IRiderRepository;

public class RiderService implements IRiderService{
    private IRiderRepository riderRepository;

    public RiderService(IRiderRepository riderRepository){
        this.riderRepository = riderRepository;
    }

    @Override
    public Rider addRider(String riderID, double xCoordinate, double yCoordinate) {
        Rider rider = new Rider(riderID, xCoordinate, yCoordinate);
        return riderRepository.save(rider);
    }
    
}
