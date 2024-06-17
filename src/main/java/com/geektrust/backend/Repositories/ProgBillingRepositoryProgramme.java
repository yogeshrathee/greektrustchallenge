package com.geektrust.backend.Repositories;

import com.geektrust.backend.Entities.ProgrammeDisCountCoupons;
import com.geektrust.backend.Entities.ProgrammeStudent;

public class ProgBillingRepositoryProgramme implements ProgIBillingRepository {

    private ProgrammeStudent programmeStudent;
    private Double totalProgramFees;
    private ProgrammeDisCountCoupons programmeDisCountCoupons;
    private Double discountAmount;
    private Double proMembershipDiscount;
    private Double totalAmount;


    public ProgBillingRepositoryProgramme(ProgrammeStudent programmeStudent) {
        this.programmeStudent = programmeStudent;
        totalProgramFees = 0.00;
        proMembershipDiscount = 0.00;
        totalAmount = 0.00;
    }


    @Override
    public void setTotalProgramFees(Double totalProgramFees) {
        // TODO Auto-generated method stub
        this.totalProgramFees = totalProgramFees;
        
    }

    @Override
    public void setProMembershipDiscount(Double proMembershipDiscount) {
        // TODO Auto-generated method stub
        this.proMembershipDiscount = proMembershipDiscount;
    }

    @Override
    public void addEnrollmentFee() {
        // TODO Auto-generated method stub
        programmeStudent.addEnrollmentFee();
        
    }

    @Override
    public void setTotalAmount(Double totalAmount) {
        // TODO Auto-generated method stub
        this.totalAmount = totalAmount;
        
    }


    @Override
    public Double getTotalProgramFees() {
        // TODO Auto-generated method stub
        return totalProgramFees;
    }


    // @Override
    // public Discount getDiscount() {
    //     // TODO Auto-generated method stub
    //     return discount;
    // }


    @Override
    public Double getProMembershipDiscount() {
        // TODO Auto-generated method stub
        return proMembershipDiscount;
    }


    @Override
    public Double getProMembershipFee() {
        // TODO Auto-generated method stub
        return programmeStudent.getProMembershipFee();
    }


    @Override
    public Double getEnrollmentFee() {
        // TODO Auto-generated method stub
        return programmeStudent.getEnrollmentFee();
    }


    @Override
    public Double getTotalAmount() {
        // TODO Auto-generated method stub
        return totalAmount;
    }


    @Override
    public Double getDiscountAmount() {
        // TODO Auto-generated method stub
        return discountAmount;

    }


    @Override
    public void setDiscountAmount(Double discountAmount) {
        // TODO Auto-generated method stub
        this.discountAmount = discountAmount;
        
    }


    @Override
    public ProgrammeDisCountCoupons getCouponDiscount() {
        // TODO Auto-generated method stub
        return programmeDisCountCoupons;
    }


    @Override
    public void setCouponDiscount(ProgrammeDisCountCoupons programmeDisCountCoupons) {
        // TODO Auto-generated method stub
        this.programmeDisCountCoupons = programmeDisCountCoupons;
        
    }

    
    
}
