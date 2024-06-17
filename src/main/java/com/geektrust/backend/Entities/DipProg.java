package com.geektrust.backend.Entities;

import com.geektrust.backend.Util.ProgrammeGeekDmyConstant;

public class DipProg implements Programme {

    private final ProgrammeCat programmeCat;
    private Double programFees;
    private Integer count;
    private final Double programDiscount;

    private Double programDiscountAmount;

    public DipProg() {
        programmeCat = ProgrammeCat.DIPLOMA;
        programFees = ProgrammeGeekDmyConstant.DIPLOMA_FEES;
        count = 0;
        programDiscount = ProgrammeGeekDmyConstant.DIPLOMA_DISCOUNT;
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
        count += quantity;
        
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
