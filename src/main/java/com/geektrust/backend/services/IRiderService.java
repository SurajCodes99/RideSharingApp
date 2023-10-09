package com.geektrust.backend.services;

import com.geektrust.backend.entities.Rider;

public interface IRiderService {
    //To add a new rider.
    public Rider addRider(String riderId, double xCoordinate, double yCoordinate);
}
