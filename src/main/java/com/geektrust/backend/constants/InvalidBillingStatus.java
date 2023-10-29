package com.geektrust.backend.constants;

public enum InvalidBillingStatus {
    INVALID_RIDE(-(double)Constants.ONE), 
    RIDE_NOT_COMPLETED(-(double)Constants.TWO);

    private final double val; 
    
    InvalidBillingStatus(double val){
        this.val = val;
    }

    public double getVal() {return val;}

    public static InvalidBillingStatus fromValue(double val){
        for(InvalidBillingStatus billingStatus : InvalidBillingStatus.values()){
            if(billingStatus.getVal() == val) return billingStatus;
        }
        return null;
    }
}
