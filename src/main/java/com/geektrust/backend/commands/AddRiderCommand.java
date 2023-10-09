package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.services.IRiderService;

public class AddRiderCommand implements ICommand{
    private IRiderService riderService;

    public AddRiderCommand(IRiderService riderService){
        this.riderService = riderService;
    }

    @Override
    public void execute(List<String> tokens) {
        if(tokens.get(Constants.ZERO).equals(Constants.ADD_RIDER_COMMAND)){
            String riderId = tokens.get(Constants.ONE);
            int xCoordinate = Integer.parseInt(tokens.get(Constants.TWO));
            int yCoordinate = Integer.parseInt(tokens.get(Constants.THREE));

            riderService.addRider(riderId, xCoordinate, yCoordinate);
        }
    }
}
