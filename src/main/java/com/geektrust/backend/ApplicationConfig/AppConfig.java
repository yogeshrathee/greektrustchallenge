package com.geektrust.backend.ApplicationConfig;

import com.geektrust.backend.Command.AddProgrammeMemberProgrammeCommand;
import com.geektrust.backend.Command.AddProgProgrammeCommand;
import com.geektrust.backend.Command.ApplyProgrammeCouponProgrammeCommand;
import com.geektrust.backend.Command.ProgrammeCommandInvoker;
import com.geektrust.backend.Command.PrintBillProgCommand;
import com.geektrust.backend.Entities.ProgrammeStudent;
import com.geektrust.backend.Repositories.ProgBillingRepositoryProgramme;
import com.geektrust.backend.Repositories.ProgIBillingRepository;
import com.geektrust.backend.Repositories.IProgStudentRepository;
import com.geektrust.backend.Repositories.ProgStudentRepository;
import com.geektrust.backend.Services.IProgBillingService;
import com.geektrust.backend.Services.IProgCartService;
import com.geektrust.backend.Services.IProgDiscountService;
import com.geektrust.backend.Services.ProgProgBillingService;
import com.geektrust.backend.Services.ProgProgCartService;
import com.geektrust.backend.Services.ProgProgDiscountService;
import com.geektrust.backend.Services.IProgStudentService;
import com.geektrust.backend.Services.ProgStudentService;


public class AppConfig {

    ProgrammeStudent programmeStudent = new ProgrammeStudent();
    private final IProgStudentRepository studentRepository = new ProgStudentRepository(programmeStudent);
    private final ProgIBillingRepository billingRepository = new ProgBillingRepositoryProgramme(programmeStudent);
    private final IProgCartService cartService = new ProgProgCartService(studentRepository, billingRepository);
    private final IProgDiscountService discountService = new ProgProgDiscountService(studentRepository, billingRepository);
    private final IProgStudentService studentService = new ProgStudentService(studentRepository);
    private final IProgBillingService billingService = new ProgProgBillingService(studentRepository, billingRepository, cartService, discountService);

    private final AddProgProgrammeCommand addProgCommand = new AddProgProgrammeCommand(studentService);
    private final ApplyProgrammeCouponProgrammeCommand applyProgrammeCouponCommand = new ApplyProgrammeCouponProgrammeCommand(studentService);
    private final AddProgrammeMemberProgrammeCommand addProgrammeMemberCommand = new AddProgrammeMemberProgrammeCommand(studentService);
    private final PrintBillProgCommand printBillCommand = new PrintBillProgCommand(billingService);

    private final ProgrammeCommandInvoker programmeCommandInvoker = new ProgrammeCommandInvoker();

    public ProgrammeCommandInvoker getCommandInvoker(){
        programmeCommandInvoker.register("ADD_PROGRAMME", addProgCommand);
        programmeCommandInvoker.register("APPLY_COUPON", applyProgrammeCouponCommand);
        programmeCommandInvoker.register("ADD_PRO_MEMBERSHIP", addProgrammeMemberCommand);
        programmeCommandInvoker.register("PRINT_BILL", printBillCommand);

        return programmeCommandInvoker;
    }

    
}
