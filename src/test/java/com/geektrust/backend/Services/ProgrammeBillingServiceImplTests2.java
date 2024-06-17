package com.geektrust.backend.Services;

import com.geektrust.backend.Dto.ProgrammeBillPrintDto;
import com.geektrust.backend.Entities.ProgrammeStudent;
import com.geektrust.backend.Entities.ProgrammeDisCountCoupons;
import com.geektrust.backend.Repositories.ProgBillingRepositoryProgramme;
import com.geektrust.backend.Repositories.ProgIBillingRepository;
import com.geektrust.backend.Repositories.IProgStudentRepository;
import com.geektrust.backend.Util.ProgrammeGeekDmyConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("BillingServiceTest for ProMembers")
@ExtendWith(MockitoExtension.class)
class ProgrammeBillingServiceImplTests2 {

    private IProgBillingService billingService;

    private IProgStudentRepository studentRepository;

    
    private ProgIBillingRepository billingRepository;

    
    private IProgCartService cartService;

    
    private IProgDiscountService discountService;

    @BeforeEach
    public void setup() {
        ProgrammeStudent programmeStudent = new ProgrammeStudent();
        programmeStudent.addProMembershipPlan();
        studentRepository = Mockito.mock(IProgStudentRepository.class);
        billingRepository = new ProgBillingRepositoryProgramme(programmeStudent);
        cartService = new ProgProgCartService(studentRepository, billingRepository);
        discountService = new ProgProgDiscountService(studentRepository, billingRepository);
        billingService = new ProgProgBillingService(studentRepository, billingRepository, cartService, discountService);

    }

    
    @DisplayName("Test for B4G1 with ProMembership")
    @Test
    void calculateBillWithProMembership1() {
        ProgrammeStudent programmeStudent1 = new ProgrammeStudent();
        programmeStudent1.addProMembershipPlan();
        when(studentRepository.getStudent()).thenReturn(programmeStudent1);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_FEES * (1 - ProgrammeGeekDmyConstant.DEGREE_DISCOUNT));
        when(studentRepository.getDegreeProgrammeDiscountAmount()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_DISCOUNT * ProgrammeGeekDmyConstant.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(2);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES * (1 - ProgrammeGeekDmyConstant.CERTIFICATION_DISCOUNT));
        when(studentRepository.getCertificationProgrammeDiscountAmount()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES * ProgrammeGeekDmyConstant.CERTIFICATION_DISCOUNT);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES * (1 - ProgrammeGeekDmyConstant.DIPLOMA_DISCOUNT));
        when(studentRepository.getDiplomaProgrammeDiscountAmount()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES * ProgrammeGeekDmyConstant.DIPLOMA_DISCOUNT);

        Double totalProgrammeCost = 2 * studentRepository.getCertificationProgrammeCost() 
            + 2 * studentRepository.getDegreeProgrammeCost() + ProgrammeGeekDmyConstant.PRO_MEMBERSHIP_FEE;

        Double proMembershipDiscount = 2 * studentRepository.getCertificationProgrammeDiscountAmount()
        + 2 * studentRepository.getDegreeProgrammeDiscountAmount();

        ProgrammeBillPrintDto bill = billingService.calculateBill();

        Double discountAmount = ProgrammeGeekDmyConstant.CERTIFICATION_FEES * (1 - ProgrammeGeekDmyConstant.CERTIFICATION_DISCOUNT);

        Double totalAmount = totalProgrammeCost - discountAmount;

        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(proMembershipDiscount, bill.getProMembershipDiscount());
        assertEquals(ProgrammeDisCountCoupons.B4G1, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotal());
    }

    @DisplayName("Test for DEAL_G20 with ProMembership")
    @Test
    void calculateBillWithProMembership2() {
        ProgrammeStudent programmeStudent1 = new ProgrammeStudent();
        programmeStudent1.addProMembershipPlan();
        when(studentRepository.getStudent()).thenReturn(programmeStudent1);
        when(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G20) ).thenReturn(true);
        //when(studentRepository.containsDiscountCoupon(DiscountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_FEES * (1 - ProgrammeGeekDmyConstant.DEGREE_DISCOUNT));
        when(studentRepository.getDegreeProgrammeDiscountAmount()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_DISCOUNT * ProgrammeGeekDmyConstant.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES * (1 - ProgrammeGeekDmyConstant.CERTIFICATION_DISCOUNT));
        when(studentRepository.getCertificationProgrammeDiscountAmount()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES * ProgrammeGeekDmyConstant.CERTIFICATION_DISCOUNT);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES * (1 - ProgrammeGeekDmyConstant.DIPLOMA_DISCOUNT));
        when(studentRepository.getDiplomaProgrammeDiscountAmount()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES * ProgrammeGeekDmyConstant.DIPLOMA_DISCOUNT);


        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 2 * studentRepository.getDegreeProgrammeCost() + ProgrammeGeekDmyConstant.PRO_MEMBERSHIP_FEE;

        Double proMembershipDiscount = 1 * studentRepository.getCertificationProgrammeDiscountAmount() 
        + 2 * studentRepository.getDegreeProgrammeDiscountAmount();

        Double discountAmount = totalProgrammeCost * ProgrammeGeekDmyConstant.DEAL_G20_DISCOUNT;

        Double totalAmount = totalProgrammeCost - discountAmount;

        ProgrammeBillPrintDto bill = billingService.calculateBill();

        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(proMembershipDiscount, bill.getProMembershipDiscount());
        assertEquals(ProgrammeDisCountCoupons.DEAL_G20, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotal());

    }

    @DisplayName("Test for DEAL_G5 with ProMembership provided DEAL_G20 Coupon is not explicity provided")
    @Test
    void calculateBillWithProMembership3() {
        ProgrammeStudent programmeStudent1 = new ProgrammeStudent();
        programmeStudent1.addProMembershipPlan();
        when(studentRepository.getStudent()).thenReturn(programmeStudent1);
        when(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G20) ).thenReturn(false);
        when(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_FEES * (1 - ProgrammeGeekDmyConstant.DEGREE_DISCOUNT));
        when(studentRepository.getDegreeProgrammeDiscountAmount()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_FEES * ProgrammeGeekDmyConstant.DEGREE_DISCOUNT);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES * (1 - ProgrammeGeekDmyConstant.CERTIFICATION_DISCOUNT));
        when(studentRepository.getCertificationProgrammeDiscountAmount()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES * ProgrammeGeekDmyConstant.CERTIFICATION_DISCOUNT);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES * (1 - ProgrammeGeekDmyConstant.DIPLOMA_DISCOUNT));
        when(studentRepository.getDiplomaProgrammeDiscountAmount()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES * ProgrammeGeekDmyConstant.DIPLOMA_DISCOUNT);


        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost() 
            + 2 * studentRepository.getDegreeProgrammeCost() + ProgrammeGeekDmyConstant.PRO_MEMBERSHIP_FEE;

        Double proMembershipDiscount = 1 * studentRepository.getCertificationProgrammeDiscountAmount() 
        + 2 * studentRepository.getDegreeProgrammeDiscountAmount();

        Double discountAmount = totalProgrammeCost * ProgrammeGeekDmyConstant.DEAL_G5_DISCOUNT;

        ProgrammeBillPrintDto bill = billingService.calculateBill();

        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(proMembershipDiscount, bill.getProMembershipDiscount());
        assertEquals(ProgrammeDisCountCoupons.DEAL_G5, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
    }

    @DisplayName("Test for DEAL_G5 with ProMembership provided DEAL_G20 Coupon is explicitly provided")
    @Test
    void calculateBillWithProMembership4() {
        ProgrammeStudent programmeStudent1 = new ProgrammeStudent();
        programmeStudent1.addProMembershipPlan();
        when(studentRepository.getStudent()).thenReturn(programmeStudent1);
        when(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G20) ).thenReturn(true);
        when(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(0);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_FEES * (1 - ProgrammeGeekDmyConstant.DEGREE_DISCOUNT));
        when(studentRepository.getDegreeProgrammeDiscountAmount()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_FEES * ProgrammeGeekDmyConstant.DEGREE_DISCOUNT);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES * (1 - ProgrammeGeekDmyConstant.CERTIFICATION_DISCOUNT));
        when(studentRepository.getCertificationProgrammeDiscountAmount()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES * ProgrammeGeekDmyConstant.CERTIFICATION_DISCOUNT);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(1);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES * (1 - ProgrammeGeekDmyConstant.DIPLOMA_DISCOUNT));
        when(studentRepository.getDiplomaProgrammeDiscountAmount()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES * ProgrammeGeekDmyConstant.DIPLOMA_DISCOUNT);


        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 1 * studentRepository.getDiplomaProgrammeCost() + ProgrammeGeekDmyConstant.PRO_MEMBERSHIP_FEE;

        Double proMembershipDiscount = 1 * studentRepository.getCertificationProgrammeDiscountAmount()
        + 1 * studentRepository.getDiplomaProgrammeDiscountAmount();

        Double enrollmentFee = ProgrammeGeekDmyConstant.ENROLLMENT_FEE;

        Double discountAmount = totalProgrammeCost * ProgrammeGeekDmyConstant.DEAL_G5_DISCOUNT;

        Double totalAmount = totalProgrammeCost - discountAmount + enrollmentFee;

        ProgrammeBillPrintDto bill = billingService.calculateBill();

        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(proMembershipDiscount, bill.getProMembershipDiscount());
        assertEquals(ProgrammeDisCountCoupons.DEAL_G5, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotal());
    }
}