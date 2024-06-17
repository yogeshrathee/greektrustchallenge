package com.geektrust.backend.Services;

import com.geektrust.backend.Entities.ProgrammeDisCountCoupons;
import com.geektrust.backend.Entities.ProgrammeCat;
import com.geektrust.backend.Exception.ProgInvalidDisCountCouponException;
import com.geektrust.backend.Exception.ProgrammeInvalidProgCatException;
import com.geektrust.backend.Repositories.IProgStudentRepository;

public class ProgStudentService implements IProgStudentService {

    private IProgStudentRepository studentRepository;

    public ProgStudentService(IProgStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addProgrammes(String programCategoryStr, Integer quantity) {
        try{
            ProgrammeCat programmeCat = ProgrammeCat.valueOf(programCategoryStr);
            studentRepository.addProgramsToCart(programmeCat, quantity);
        } catch(IllegalArgumentException e) {
            throw new ProgrammeInvalidProgCatException("Invalid program category: " + programCategoryStr);
        }

    }

    @Override
    public void setProMembershipPlan() {
        studentRepository.addProMembershipPlan();
    }

    @Override
    public void addDiscountCoupon(String discountCouponStr) {
        try{
            ProgrammeDisCountCoupons discountCoupon = ProgrammeDisCountCoupons.valueOf(discountCouponStr);
            studentRepository.addDiscountCoupons(discountCoupon);
        } catch(IllegalArgumentException e) {
            throw new ProgInvalidDisCountCouponException("Invalid discount coupon: " + discountCouponStr);
        }
    }
    
}
