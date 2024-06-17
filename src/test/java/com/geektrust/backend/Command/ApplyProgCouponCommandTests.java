package com.geektrust.backend.Command;

import com.geektrust.backend.Services.IProgStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApplyProgCouponCommandTests {

    
    private IProgrammeCommand applyCouponCommand;

    
    private IProgStudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = Mockito.mock(IProgStudentService.class);
        applyCouponCommand = new ApplyProgrammeCouponProgrammeCommand(studentService);
    }

    @Test
    void execute() {
        applyCouponCommand.execute(Arrays.asList("APPLY_COUPON", "DEAL_G20"));
        verify(studentService, times(1)).addDiscountCoupon("DEAL_G20");
    }
}

