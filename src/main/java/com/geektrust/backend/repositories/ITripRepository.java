package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.Trip;

public interface ITripRepository extends CRUDRepository<Trip, String>{
    public Trip getTripDetails(String tripId);
}
