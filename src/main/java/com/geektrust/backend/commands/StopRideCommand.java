package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.entities.Trip;
import com.geektrust.backend.services.ITripService;

public class StopRideCommand implements ICommand{
    private ITripService tripService;

    public StopRideCommand(ITripService tripService){
        this.tripService = tripService;
    }
    @Override
    public void execute(List<String> tokens) {
        String command = tokens.get(Constants.ZERO);
        if(command.equals(Constants.STOP_RIDE_COMMAND)){
            String rideId = tokens.get(Constants.ONE);
            double xCoordinate = Double.parseDouble(tokens.get(Constants.TWO));
            double yCoordinate = Double.parseDouble(tokens.get(Constants.THREE));
            double timeTaken = Double.parseDouble(tokens.get(Constants.FOUR));

            Trip stoppedTrip = tripService.stopRide(rideId, xCoordinate, yCoordinate, timeTaken);
            if(stoppedTrip == null) System.out.println("INVALID_RIDE");
            else System.out.println("RIDE_STOPPED " + stoppedTrip.getTripId());
        }
    }
    
}
