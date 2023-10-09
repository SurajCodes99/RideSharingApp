package com.geektrust.backend.constants;

public enum InvalidBillingStatus {
    INVALID_RIDE(-1.0), 
    RIDE_NOT_COMPLETED(-2.0);

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
