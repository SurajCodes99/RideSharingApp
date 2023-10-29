package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.services.IDriverService;

public class AddDriverCommand implements ICommand{
    private IDriverService driverService;

    public AddDriverCommand(IDriverService driverService){
        this.driverService = driverService;
    }

    @Override
    public void execute(List<String> tokens) {
            String driverId = tokens.get(Constants.ONE);
            int xCoordinate = Integer.parseInt(tokens.get(Constants.TWO));
            int yCoordinate = Integer.parseInt(tokens.get(Constants.THREE));
            driverService.addDriver(driverId, xCoordinate, yCoordinate);
    }
    
}
