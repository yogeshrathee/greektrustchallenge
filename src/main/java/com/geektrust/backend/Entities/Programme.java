package com.geektrust.backend.Entities;

public interface Programme {

    ProgrammeCat getProgram();
    Double getProgramFee();
    void addProgram(Integer quantity);
    Integer getProgramCount();
    Double getProgramDiscount();
    void addProMembershipDiscountCoupon();
    Double getProgramDiscountAmount();
    
}
