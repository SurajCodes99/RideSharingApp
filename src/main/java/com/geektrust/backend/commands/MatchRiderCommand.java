package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.services.IDriverService;

public class MatchRiderCommand implements ICommand{
    private IDriverService driverService;

    public MatchRiderCommand(IDriverService driverService){
        this.driverService = driverService;
    }
    @Override
    public void execute(List<String> tokens) {
        if(tokens.get(Constants.ZERO).equals(Constants.MATCH_COMMAND)){
            String riderId = tokens.get(Constants.ONE);

            List<Driver> driversInRadius = driverService.driversInRadius(riderId);
            

            //When there are no drivers available in the 5k radius.
            if(driversInRadius.size() == Constants.ZERO){
                System.out.println("NO_DRIVERS_AVAILABLE");
                return ;
            }

            System.out.print("DRIVERS_MATCHED ");
            String output = "";
            for(Driver driver : driversInRadius) output += driver.getDriverId() + " ";
            output = output.substring(Constants.ZERO, output.length() - Constants.ONE);
            System.out.println(output);
        }
    }
    
}
