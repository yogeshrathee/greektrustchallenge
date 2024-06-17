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

@DisplayName("BillingServiceTest for Non Pro Members")
@ExtendWith(MockitoExtension.class)
class ProgrammeBillingServiceImplTests {

    private IProgBillingService billingService;

    private IProgStudentRepository studentRepository;

    
    private ProgIBillingRepository billingRepository;

    
    private IProgCartService cartService;

    
    private IProgDiscountService discountService;

    @BeforeEach
    public void setup() {
        ProgrammeStudent programmeStudent = new ProgrammeStudent();
        studentRepository = Mockito.mock(IProgStudentRepository.class);
        billingRepository = new ProgBillingRepositoryProgramme(programmeStudent);
        cartService = new ProgProgCartService(studentRepository, billingRepository);
        discountService = new ProgProgDiscountService(studentRepository, billingRepository);
        billingService = new ProgProgBillingService(studentRepository, billingRepository, cartService, discountService);

    }

    @DisplayName("Test for B4G1 without Pro Membership")
    @Test
    void calculateBillWithoutProMembership1() {
        ProgrammeStudent programmeStudent1 = new ProgrammeStudent();
        when(studentRepository.getStudent()).thenReturn(programmeStudent1);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(2);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(1);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES);

        ProgrammeBillPrintDto bill = billingService.calculateBill();

        Double totalProgrammeCost = 2 * studentRepository.getCertificationProgrammeCost()
            + 2 * studentRepository.getDegreeProgrammeCost()
            + 1 * studentRepository.getDiplomaProgrammeCost();

        Double discountAmount = ProgrammeGeekDmyConstant.DIPLOMA_FEES;

        Double totalAmount = totalProgrammeCost - discountAmount;

        
        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(0.0, bill.getProMembershipDiscount());
        assertEquals(ProgrammeDisCountCoupons.B4G1, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotal());
    }
    
    @DisplayName("Test for DEAL_G20 without Pro Membership")
    @Test
    void calculateBillWithoutProMembership2() {
        ProgrammeStudent programmeStudent1 = new ProgrammeStudent();
        when(studentRepository.getStudent()).thenReturn(programmeStudent1);
        when(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G20) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES);

        ProgrammeBillPrintDto bill = billingService.calculateBill();

        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 2 * studentRepository.getDegreeProgrammeCost()
            + 0 * studentRepository.getDiplomaProgrammeCost();

        Double discountAmount = totalProgrammeCost * ProgrammeGeekDmyConstant.DEAL_G20_DISCOUNT;

        Double totalAmount = totalProgrammeCost - discountAmount;

        
        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(0.0, bill.getProMembershipDiscount());
        assertEquals(ProgrammeDisCountCoupons.DEAL_G20, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotal());
    }

    @DisplayName("Test for No Discount if no discount coupons are provided without Pro Membership")
    @Test
    void calculateBillWithoutProMembership3() {
        ProgrammeStudent programmeStudent1 = new ProgrammeStudent();
        when(studentRepository.getStudent()).thenReturn(programmeStudent1);
        when(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G20) ).thenReturn(false);
        when(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G5) ).thenReturn(false);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(2);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES);

        ProgrammeBillPrintDto bill = billingService.calculateBill();

        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 2 * studentRepository.getDegreeProgrammeCost()
            + 0 * studentRepository.getDiplomaProgrammeCost();

        Double discountAmount = 0.0;

        Double totalAmount = totalProgrammeCost - discountAmount;

        
        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(0.0, bill.getProMembershipDiscount());
        assertEquals(ProgrammeDisCountCoupons.NONE, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotal());
    }

    @DisplayName("Test for No Discount even if discount coupons are provided without Pro Membership")
    @Test
    void calculateBillWithoutProMembership4() {
        ProgrammeStudent programmeStudent1 = new ProgrammeStudent();
        when(studentRepository.getStudent()).thenReturn(programmeStudent1);
        when(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G20) ).thenReturn(true);
        when(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(0);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(0);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES);

        ProgrammeBillPrintDto bill = billingService.calculateBill();

        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 0 * studentRepository.getDegreeProgrammeCost()
            + 0 * studentRepository.getDiplomaProgrammeCost();

        Double discountAmount = 0.0;

        Double enrollmentFee = ProgrammeGeekDmyConstant.ENROLLMENT_FEE;

        Double totalAmount = totalProgrammeCost - discountAmount + enrollmentFee;

        
        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(0.0, bill.getProMembershipDiscount());
        assertEquals(ProgrammeDisCountCoupons.NONE, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(totalAmount, bill.getTotal());
    }

    @DisplayName("Test for Enrollment Fee")
    @Test
    void calculateBillWithEnrollmentFee() {
        ProgrammeStudent programmeStudent1 = new ProgrammeStudent();
        when(studentRepository.getStudent()).thenReturn(programmeStudent1);
        when(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G20) ).thenReturn(true);
        when(studentRepository.containsDiscountCoupon(ProgrammeDisCountCoupons.DEAL_G5) ).thenReturn(true);
        when(studentRepository.getDegreeProgrammeCount()).thenReturn(0);
        when(studentRepository.getDegreeProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DEGREE_FEES);
        when(studentRepository.getCertificationProgrammeCount()).thenReturn(1);
        when(studentRepository.getCertificationProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.CERTIFICATION_FEES);
        when(studentRepository.getDiplomaProgrammeCount()).thenReturn(1);
        when(studentRepository.getDiplomaProgrammeCost()).thenReturn(ProgrammeGeekDmyConstant.DIPLOMA_FEES);

        ProgrammeBillPrintDto bill = billingService.calculateBill();

        Double totalProgrammeCost = 1 * studentRepository.getCertificationProgrammeCost()
            + 0 * studentRepository.getDegreeProgrammeCost()
            + 1 * studentRepository.getDiplomaProgrammeCost();

        Double discountAmount = totalProgrammeCost * ProgrammeGeekDmyConstant.DEAL_G5_DISCOUNT;

        Double enrollmentFee = ProgrammeGeekDmyConstant.ENROLLMENT_FEE;

        Double totalAmount = totalProgrammeCost - discountAmount + enrollmentFee;

        
        assertNotNull(bill);
        assertEquals(totalProgrammeCost, bill.getTotalProgrammeFee());
        assertEquals(0.0, bill.getProMembershipDiscount());
        assertEquals(ProgrammeDisCountCoupons.DEAL_G5, bill.getCouponDiscountType());
        assertEquals(discountAmount, bill.getCouponDiscountAmount());
        assertEquals(enrollmentFee, bill.getEnrollmentFee());
        assertEquals(totalAmount, bill.getTotal());
    }
}






