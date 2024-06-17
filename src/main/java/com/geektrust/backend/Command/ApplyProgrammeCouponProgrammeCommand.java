package com.geektrust.backend.Command;

import java.util.List;
import com.geektrust.backend.Services.IProgStudentService;

public class ApplyProgrammeCouponProgrammeCommand implements IProgrammeCommand {

    private final IProgStudentService studentService;

    public ApplyProgrammeCouponProgrammeCommand(IProgStudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute(List<String> tokens) {
        String discountCoupon = tokens.get(1);
        studentService.addDiscountCoupon(discountCoupon);
    }
    
}
