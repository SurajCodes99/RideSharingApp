package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.List;
import com.geektrust.backend.entities.Trip;

public class TripRepository implements ITripRepository{
    private final HashMap<String, Trip> tripMap;

    public TripRepository(){
        this.tripMap = new HashMap<>();
    }

    @Override
    public Trip save(Trip entity) {
        tripMap.put(entity.getTripId(), entity);
        return entity;
    }

    @Override
    public List<Trip> findAll() { return null; }

    @Override
    public boolean existsById(String id) {
        if(tripMap.containsKey(id)) return true;
        else return false;
    }

    @Override
    public Trip getTripDetails(String tripId) {
        Trip currentTrip = tripMap.get(tripId);
        return currentTrip;
    }
}
