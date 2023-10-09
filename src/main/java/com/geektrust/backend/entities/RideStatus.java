package com.geektrust.backend.entities;

public enum RideStatus{
    WAITING,  // Indicates that both the driver and the rider are waiting.
    STARTED,  // Indicates that the ride has started for both the driver and the rider.
    COMPLETED // Indicates that the ride has completed for both the driver and the rider.(Only applicable to trips).
}