package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.RideStatus;
import com.geektrust.backend.entities.Rider;

public interface IRiderRepository extends CRUDRepository<Rider, String>{
    public Rider getRiderDetails(String riderId);
    public RideStatus getRiderStatus(String riderId);
}
