package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.constants.InvalidBillingStatus;
import com.geektrust.backend.entities.Trip;
import com.geektrust.backend.services.ITripService;

public class BillCommand implements ICommand{
    private ITripService tripService;

    public BillCommand(ITripService tripService){
        this.tripService = tripService;
    }
    @Override
    public void execute(List<String> tokens) {
        String command = tokens.get(Constants.ZERO);
        if(command.equals(Constants.BILL_COMMAND)){
            String tripId = tokens.get(Constants.ONE);
            Trip trip = tripService.calculateBill(tripId);

            double amount = Constants.ZERO;

            if(trip == null) amount = -Constants.ONE;
            else amount = trip.getTripAmount();

            //Error scenario when less than 0:
            if(amount < 0){
                InvalidBillingStatus billingStatus = InvalidBillingStatus.fromValue(amount);
                System.out.println(billingStatus);
            }else{
                String stringAmount = String.format("%.2f", amount);
                System.out.println("BILL " + trip.getTripId() + " " + trip.getDriver().getDriverId() + " " + stringAmount);
            }
        }
    }
    
}
