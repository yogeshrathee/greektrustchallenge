package com.geektrust.backend.Repositories;

import com.geektrust.backend.Entities.ProgrammeDisCountCoupons;

public interface ProgIBillingRepository {

    void setTotalProgramFees(Double totalProgramFees);
    Double getProMembershipDiscount();
    void setProMembershipDiscount(Double proMembershipDiscount);
    Double getProMembershipFee();
    void addEnrollmentFee();
    Double getEnrollmentFee();
    void setTotalAmount(Double totalAmount);
    Double getTotalProgramFees();
    Double getTotalAmount();
    ProgrammeDisCountCoupons getCouponDiscount();
    void setCouponDiscount(ProgrammeDisCountCoupons programmeDisCountCoupons);
    Double getDiscountAmount();
    void setDiscountAmount(Double discountAmount);
    
}
