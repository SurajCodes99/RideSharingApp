package com.geektrust.backend.services;

import java.util.List;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.RideStatus;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.entities.Trip;
import com.geektrust.backend.repositories.IDriverRepository;
import com.geektrust.backend.repositories.IRiderRepository;
import com.geektrust.backend.repositories.ITripRepository;
import com.geektrust.backend.constants.Constants;

public class TripService implements ITripService{
    private IRiderRepository riderRepository; 
    private ITripRepository tripRepository;
    private IDriverRepository driverRepository;

    public TripService(IRiderRepository riderRepository, ITripRepository tripRepository, IDriverRepository driverRepository){
        this.riderRepository = riderRepository; 
        this.tripRepository = tripRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public Trip startRide(String rideId, int driverSelection, String riderId, List<Driver> driversInRadius) {
        //To check the number of drivers in the radius of 5km.
        int numberOfDrivers = driversInRadius.size();

        //To check the trip validity: 
        boolean tripValidity = tripRepository.existsById(rideId);

        //To perform checks on the rider:
        boolean riderValidity = riderIsValid(riderId);
        
        //Cases of Invalid Ride and Driver related checks:
        if((tripValidity || !riderValidity)||driverSelection > numberOfDrivers || driverSelection < Constants.ONE) return null;

        //Another case for invalid ride:
        Driver selectedDriver = driversInRadius.get(driverSelection - Constants.ONE);
        if(selectedDriver.getDriverStatus() != RideStatus.WAITING) return null;

        //Actual code to start the ride for both the driver and rider: 
        selectedDriver.startRide();
        riderRepository.getRiderDetails(riderId).setRiderStatusAsStarted();

        //To save the trip to the repo:
        Rider currentRider = riderRepository.getRiderDetails(riderId);
        Trip startedTrip = new Trip(rideId, selectedDriver,currentRider,RideStatus.STARTED);
        
        return tripRepository.save(startedTrip);
    }

    private boolean riderIsValid(String riderId) {
        //Checks if the rider exists and checks if the rider is waiting.
        boolean riderExistenceCheck = riderRepository.existsById(riderId);
        Rider rider = riderRepository.getRiderDetails(riderId);
        boolean waitCheck = rider.getRiderStatus() == RideStatus.WAITING;

        return (riderExistenceCheck && (rider != null) && waitCheck);
    }

    @Override
    public Trip stopRide(String rideId, double xCoordinate, double yCoordinate, double timeTaken) {
        if((!tripRepository.existsById(rideId)) || (tripRepository.getTripDetails(rideId).getRideStatus() == RideStatus.COMPLETED)) return null;

        //Set the trip status to completed:
        Trip tripToBeStopped = tripRepository.getTripDetails(rideId);
        tripToBeStopped.setRideAsCompleted();

        //Set the rider status to waiting for the given rider: 
        String riderOfStoppedTripId = tripToBeStopped.getRider().getRiderId();
        Rider currentRider = riderRepository.getRiderDetails(riderOfStoppedTripId);
        currentRider.riderStatusWaiting();

        //Set the driver status to waiting for the given driver, so that he/she can be booked again:
        String driverofStoppedTripId = tripToBeStopped.getDriver().getDriverId();
        Driver currentDriver = driverRepository.getDriverDetails(driverofStoppedTripId);
        currentDriver.endRide();

        tripToBeStopped.setDestinationCoordinates(xCoordinate, yCoordinate);
        tripToBeStopped.setTimeTaken(timeTaken);

        return tripToBeStopped;
    }

    @Override
    public Trip calculateBill(String tripId) {
        // -1 to indicate that the tripId doesn't exist: 
        if(!tripRepository.existsById(tripId)) return null;

        Trip currentTrip = tripRepository.getTripDetails(tripId);

        //-2 to indicate that trip isn't completed.
        if(currentTrip.getRideStatus() != RideStatus.COMPLETED) {
            currentTrip.setTripAmount(-Constants.TWO);
            return currentTrip;
        }

        double distance = calculateDistance(currentTrip);
        distance = Math.round(distance * Constants.HUNDRED)/ Constants.HUNDRED;
        
        double distanceCharge = distance * Constants.DISTANCEFARE + Constants.BASEFARE;
        distanceCharge = Math.round(distanceCharge * Constants.HUNDRED)/ Constants.HUNDRED;

        double timeCharge = Constants.TIMEFARE * currentTrip.getTimeTaken();
        timeCharge = Math.round(timeCharge * Constants.HUNDRED)/ Constants.HUNDRED;

        double totalAmount = Constants.SERVICETAX * (distanceCharge + timeCharge);
        totalAmount = Math.round(totalAmount * Constants.HUNDRED)/ Constants.HUNDRED;
        
        currentTrip.setTripAmount(totalAmount);
        return currentTrip;
    }

    private double calculateDistance(Trip currentTrip) {
            //Rider distance
            double riderXCoordinate = currentTrip.getRider().getxCoordinate();
            double riderYCoordinate = currentTrip.getRider().getyCoordinate();

            //Destination distance: 
            double destinationXCoordinate = currentTrip.getDestinationXCoordinate();
            double destinationYCoordinate = currentTrip.getDestinationYCoordinate();
        
            double xDistance = Math.pow(riderXCoordinate - destinationXCoordinate, Constants.TWO);
            double yDistance = Math.pow(riderYCoordinate - destinationYCoordinate, Constants.TWO);

            double distance = Math.sqrt(xDistance + yDistance);

            return distance;
    }

}

