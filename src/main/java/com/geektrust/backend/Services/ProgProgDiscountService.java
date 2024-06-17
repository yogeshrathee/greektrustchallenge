package com.geektrust.backend.Services;


import com.geektrust.backend.Entities.ProgrammeDisCountCoupons;
import com.geektrust.backend.Repositories.ProgIBillingRepository;
import com.geektrust.backend.Repositories.IProgStudentRepository;
import com.geektrust.backend.Util.ProgrammeGeekDmyConstant;

public class ProgProgDiscountService implements IProgDiscountService {

    private IProgStudentRepository studentRepository;
    private ProgIBillingRepository billingRepository;

    public ProgProgDiscountService(IProgStudentRepository studentRepository,
                                   ProgIBillingRepository billingRepository) {
        this.studentRepository = studentRepository;
        this.billingRepository = billingRepository;
    }

    public void applyB4G1Discount() {
        billingRepository.setCouponDiscount(ProgrammeDisCountCoupons.B4G1);
        Double discountAmount = 0.0;
        if(studentRepository.getDiplomaProgrammeCount() > 0) {
            discountAmount = studentRepository.getDiplomaProgrammeCost();
        } else if(studentRepository.getCertificationProgrammeCount() > 0) {
            discountAmount = studentRepository.getCertificationProgrammeCost();
        } else {
            discountAmount = studentRepository.getDegreeProgrammeCost();
        }
        billingRepository.setDiscountAmount(discountAmount);
        billingRepository.setTotalAmount(billingRepository.getTotalAmount() - discountAmount);
    }

    public void applyDealG20Discount() {
        billingRepository.setCouponDiscount(ProgrammeDisCountCoupons.DEAL_G20);
        billingRepository.setDiscountAmount(billingRepository.getTotalProgramFees() * ProgrammeGeekDmyConstant.DEAL_G20_DISCOUNT);
        billingRepository.setTotalAmount(billingRepository.getTotalAmount() - billingRepository.getDiscountAmount());
    }

    public void applyDealG5Discount() {
        billingRepository.setCouponDiscount(ProgrammeDisCountCoupons.DEAL_G5);
        billingRepository.setDiscountAmount(billingRepository.getTotalProgramFees() * ProgrammeGeekDmyConstant.DEAL_G5_DISCOUNT);
        billingRepository.setTotalAmount(billingRepository.getTotalAmount() - billingRepository.getDiscountAmount());
    }

    public void applyNoDiscount() {
        billingRepository.setCouponDiscount(ProgrammeDisCountCoupons.NONE);
        billingRepository.setDiscountAmount(0.0);
    }

    
}
