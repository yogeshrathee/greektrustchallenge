package com.geektrust.backend.Services;

import com.geektrust.backend.Dto.ProgrammeBillPrintDto;
import com.geektrust.backend.Entities.ProgrammeDisCountCoupons;
import com.geektrust.backend.Repositories.ProgIBillingRepository;
import com.geektrust.backend.Repositories.IProgStudentRepository;
import com.geektrust.backend.Util.ProgrammeGeekDmyConstant;

public class ProgProgBillingService implements IProgBillingService {


    private IProgStudentRepository studentRepository;
    private ProgIBillingRepository billingRepository;

    private IProgCartService cartService;
    private IProgDiscountService discountService;

    public ProgProgBillingService(IProgStudentRepository studentRepository, ProgIBillingRepository billingRepository, IProgCartService cartService, IProgDiscountService discountService) {
        this.studentRepository = studentRepository;
        this.billingRepository = billingRepository;
        this.cartService = cartService;
        this.discountService = discountService;
    }


    private void calculateTotalCartItemCost() {
        cartService.calculateTotalCost();
        if(studentRepository.getStudent().getProMembershipStatus() == true) {
            cartService.calculateTotalProMembershipDiscount();
        }
    }

    private void calculateDiscount() {
        Integer totalProgrammeCount = studentRepository.getCertificationProgrammeCount() + studentRepository.getDegreeProgrammeCount() + studentRepository.getDiplomaProgrammeCount();
        if(totalProgrammeCount >= 4) {
            discountService.applyB4G1Discount();
        } else if(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G20) && billingRepository.getTotalProgramFees() >= ProgrammeGeekDmyConstant.PROGRAMME_COST_FOR_G20_DISCOUNT) {
            discountService.applyDealG20Discount();
        } else if(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G5) && totalProgrammeCount >= ProgrammeGeekDmyConstant.PROGRAMME_COUNT_FOR_G5_DISCOUNT) {
            discountService.applyDealG5Discount();
        } else {
            discountService.applyNoDiscount();
        }

    }

    private void checkEnrollmentElligibility() {
        Double totalAmount = billingRepository.getTotalAmount();
        if(totalAmount < ProgrammeGeekDmyConstant.PROGRAMME_COST_TRESHOLD_FOR_ENROLLMENT_FEE) {
            billingRepository.addEnrollmentFee();
            billingRepository.setTotalAmount(billingRepository.getTotalAmount() + billingRepository.getEnrollmentFee());
        }
    }

    @Override
    public ProgrammeBillPrintDto calculateBill() {
        calculateTotalCartItemCost();
        calculateDiscount();
        checkEnrollmentElligibility();
        return new ProgrammeBillPrintDto(
            billingRepository.getTotalProgramFees(),
            billingRepository.getCouponDiscount(),
            billingRepository.getDiscountAmount(),
            billingRepository.getProMembershipDiscount(),
            billingRepository.getProMembershipFee(),
            billingRepository.getEnrollmentFee(),
            billingRepository.getTotalAmount());
        
    }

    
}
