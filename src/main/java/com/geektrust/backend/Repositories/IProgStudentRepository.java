package com.geektrust.backend.Repositories;

import com.geektrust.backend.Entities.ProgrammeDisCountCoupons;
import com.geektrust.backend.Entities.ProgrammeCat;
import com.geektrust.backend.Entities.ProgrammeStudent;

public interface IProgStudentRepository {

    Integer getTotalProgrammeCount();
    ProgrammeStudent getStudent();
    Integer getDegreeProgrammeCount();
    Double getDegreeProgrammeCost();
    Double getDegreeProgrammeDiscount();
    Integer getCertificationProgrammeCount();
    Double getCertificationProgrammeCost();
    Double getCertificationProgrammeDiscount();
    Integer getDiplomaProgrammeCount();
    Double getDiplomaProgrammeCost();
    Double getDiplomaProgrammeDiscount();
    void addProgramsToCart(ProgrammeCat programmes, Integer quantity);
    void addProMembershipPlan();
    void addDiscountCoupons(ProgrammeDisCountCoupons programmeDisCountCoupons);
    Boolean containsDiscountCoupon(ProgrammeDisCountCoupons discountCoupon);
    Double getCertificationProgrammeDiscountAmount();
    Double getDegreeProgrammeDiscountAmount();
    Double getDiplomaProgrammeDiscountAmount();
}
