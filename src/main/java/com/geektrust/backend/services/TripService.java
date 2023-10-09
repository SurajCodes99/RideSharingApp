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
        //First check if the same trip with rideId exists
        //Second check if the driver number selection is more than the driversInRadius.
        //Check if the riderId is valid also need to check if the rider is currently in another trip or not. 
        //Would also need to check if the driver is available. 

        //To check the number of drivers in the radius of 5km.
        int numberOfDrivers = driversInRadius.size();

        //To check the trip validity: 
        boolean tripValidity = tripRepository.existsById(rideId);

        //To perform checks on the rider:
        boolean riderValidity = riderIsValid(riderId);
        
        
        // Cases of Invalid Ride:
        if(tripValidity) return null;
        if(driverSelection > numberOfDrivers || driverSelection < Constants.ONE) return null;
        if(!riderValidity) return null;

        //Another case for invalid ride:
        Driver selectedDriver = driversInRadius.get(driverSelection - Constants.ONE);
        if(selectedDriver.getDriverStatus() != RideStatus.WAITING) return null;

        //Actual code to start the ride for both the driver and rider: 
        selectedDriver.setDriverStatus(RideStatus.STARTED);
        riderRepository.getRiderDetails(riderId).setRiderStatus(RideStatus.STARTED);

        //To save the trip to the repo: 
        Trip startedTrip = new Trip(rideId, Constants.ZERO, Constants.ZERO, selectedDriver, Constants.ZERO, Constants.ZERO, 
                                    riderRepository.getRiderDetails(riderId), RideStatus.STARTED);
        return tripRepository.save(startedTrip);
    }

    private boolean riderIsValid(String riderId) {
        boolean riderExistenceCheck = riderRepository.existsById(riderId);
        if(riderExistenceCheck){
            //Must check if the rider is currently in another ride: 
            Rider rider = riderRepository.getRiderDetails(riderId);
            if(rider.getRiderStatus() == RideStatus.WAITING) return true;
        }
        return false;
    }

    @Override
    public Trip stopRide(String rideId, double xCoordinate, double yCoordinate, double timeTaken) {
        //Need to check if the rideId is valid or not.
        //If the ride is already stopped then return null.
        //Then need to set the status of the trip to COMPLETED. Set the driver and rider to WAITING.
        //Also set the other fields of Trip class.

        if(!tripRepository.existsById(rideId)) return null;
        if(tripRepository.getTripDetails(rideId).getRideStatus() == RideStatus.COMPLETED) return null;

        //Set the trip status to completed:
        Trip tripToBeStopped = tripRepository.getTripDetails(rideId);
        tripToBeStopped.setRideStatus(RideStatus.COMPLETED);

        //Set the rider status to waiting for the given rider: 
        String riderOfStoppedTripId = tripToBeStopped.getRider().getRiderId();
        riderRepository.getRiderDetails(riderOfStoppedTripId).setRiderStatus(RideStatus.WAITING);

        //Set the driver status to waiting for the given driver, so that he/she can be booked again:
        String driverofStoppedTripId = tripToBeStopped.getDriver().getDriverId();
        driverRepository.getDriverDetails(driverofStoppedTripId).setDriverStatus(RideStatus.WAITING);

        tripToBeStopped.setDestinationXCoordinate(xCoordinate);
        tripToBeStopped.setDestinationYCoordinate(yCoordinate);

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

        //Rider distance
        double riderXCoordinate = currentTrip.getRider().getxCoordinate();
        double riderYCoordinate = currentTrip.getRider().getyCoordinate();

        //Driver distance: 
        double destinationXCoordinate = currentTrip.getDestinationXCoordinate();
        double destinationYCoordinate = currentTrip.getDestinationYCoordinate();

        double distance = calculateDistance(riderXCoordinate, riderYCoordinate, destinationXCoordinate, destinationYCoordinate);
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

    private double calculateDistance(double riderXCoordinate, double riderYCoordinate,
            double driverXCoordinate, double driverYCoordinate) {
        
            double xDistance = Math.pow(riderXCoordinate - driverXCoordinate, 2);
            double yDistance = Math.pow(riderYCoordinate - driverYCoordinate, 2);

            double distance = Math.sqrt(xDistance + yDistance);

            return distance;
    }

}

