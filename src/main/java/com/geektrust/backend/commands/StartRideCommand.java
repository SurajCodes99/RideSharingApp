package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Trip;
import com.geektrust.backend.services.IDriverService;
import com.geektrust.backend.services.ITripService;

public class StartRideCommand implements ICommand{
    private ITripService tripService;
    private IDriverService driverService;

    public StartRideCommand(ITripService tripService, IDriverService driverService){
        this.tripService = tripService;
        this.driverService = driverService;
    }

    @Override
    public void execute(List<String> tokens) {
        String command = tokens.get(Constants.ZERO);
        if(command.equals(Constants.START_RIDE_COMMAND)){

            String rideId = tokens.get(Constants.ONE);
            int driverSelection = Integer.parseInt(tokens.get(Constants.TWO));
            String riderId = tokens.get(Constants.THREE);

            //Fix this: Store the drivers that were previously matched.
            List<Driver> driversInRadius = driverService.getDriversInRadius(riderId);
            
            Trip startedTrip = tripService.startRide(rideId, driverSelection, riderId, driversInRadius);
            if(startedTrip == null) System.out.println("INVALID_RIDE");
            else{
                System.out.println("RIDE_STARTED " + startedTrip.getTripId());
            }
        }
    }
    
}
