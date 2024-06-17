package com.geektrust.backend.Entities;

import com.geektrust.backend.Util.ProgrammeGeekDmyConstant;

public class DegProg implements Programme {

    private final ProgrammeCat programmeCat;
    private Double programFees;
    private final Double programDiscount;
    private Integer count;

    private Double programDiscountAmount;

    public DegProg() {
        programmeCat = ProgrammeCat.DEGREE;
        programFees = ProgrammeGeekDmyConstant.DEGREE_FEES;
        programDiscount = ProgrammeGeekDmyConstant.DEGREE_DISCOUNT;
        count = 0;
        programDiscountAmount = 0.0;
    }

    @Override
    public ProgrammeCat getProgram() {
        return programmeCat;
    }

    @Override
    public Double getProgramFee() {
        return programFees;
    }

    @Override
    public void addProgram(Integer quantity) {
        count = count + quantity;
        
    }

    @Override
    public Integer getProgramCount() {
        return count;
    }

    @Override
    public Double getProgramDiscount() {
        return programDiscount;
    }

    @Override
    public void addProMembershipDiscountCoupon() {
        programDiscountAmount = programFees * programDiscount;
        programFees = programFees * (1 - programDiscount);
    }

    @Override
    public Double getProgramDiscountAmount() {
        return programDiscountAmount;
    }
    
    

    
    
}
