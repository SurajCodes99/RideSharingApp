package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.List;
import com.geektrust.backend.entities.RideStatus;
import com.geektrust.backend.entities.Rider;

public class RiderRepository implements IRiderRepository{
    HashMap<String, Rider> riderMap;

    public RiderRepository(){
        this.riderMap = new HashMap<>();
    }
    public RiderRepository(HashMap<String, Rider> newRiderMap){
        this.riderMap = newRiderMap;
    }
    
    @Override
    public Rider save(Rider entity) {

        String id = entity.getRiderId();
        double xCoordinate = entity.getxCoordinate();
        double yCoordinate = entity.getyCoordinate();

        Rider newRider = new Rider(id, xCoordinate, yCoordinate);
        riderMap.put(id, newRider);

        return newRider;
    }

    @Override
    public List<Rider> findAll() { return null;}

    @Override
    public boolean existsById(String id) {
        if(riderMap.containsKey(id)) return true;
        else return false;
    }

    @Override
    public Rider getRiderDetails(String riderId) {
        Rider currentRider = riderMap.get(riderId);
        return currentRider;
    }
    @Override
    public RideStatus getRiderStatus(String riderId) {
        RideStatus rideStatus;
        rideStatus = riderMap.get(riderId).getRiderStatus();
        return rideStatus;
    }
    
}
