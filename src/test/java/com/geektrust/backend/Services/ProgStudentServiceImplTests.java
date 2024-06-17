package com.geektrust.backend.Services;


import com.geektrust.backend.Entities.ProgrammeDisCountCoupons;
import com.geektrust.backend.Entities.ProgrammeCat;
import com.geektrust.backend.Exception.ProgInvalidDisCountCouponException;
import com.geektrust.backend.Exception.ProgrammeInvalidProgCatException;
import com.geektrust.backend.Repositories.IProgStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("StudentServiceTest")
@ExtendWith(MockitoExtension.class)
class ProgStudentServiceImplTests {


    private IProgStudentService studentService;

    private IProgStudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        studentRepository = Mockito.mock(IProgStudentRepository.class);
        studentService = new ProgStudentService(studentRepository);
    }
    


    @Test
    void addProgrammes() {
        studentService.addProgrammes("DEGREE", 2);
        verify(studentRepository, times(1)).addProgramsToCart(ProgrammeCat.DEGREE, 2);
    }

    @Test
    void addInvalidProgrammes() {
        assertThrows(ProgrammeInvalidProgCatException.class, () -> studentService.addProgrammes("INVALID_PROGRAM", 2));
    }

    @Test
    void setProMembershipPlan() {
        studentService.setProMembershipPlan();
        verify(studentRepository, times(1)).addProMembershipPlan();
    }

    @Test
    void addValidDiscountCoupon() {
        studentService.addDiscountCoupon("DEAL_G20");
        verify(studentRepository, times(1)).addDiscountCoupons(ProgrammeDisCountCoupons.DEAL_G20);
    }

    @Test
    void addInvalidDiscountCoupon() {
        assertThrows(ProgInvalidDisCountCouponException.class, () -> studentService.addDiscountCoupon("INVALID_COUPON"));
    }
}
