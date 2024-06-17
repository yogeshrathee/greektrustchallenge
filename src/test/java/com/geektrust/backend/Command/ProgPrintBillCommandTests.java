package com.geektrust.backend.Command;

import com.geektrust.backend.Dto.ProgrammeBillPrintDto;
import com.geektrust.backend.Entities.ProgrammeDisCountCoupons;
import com.geektrust.backend.Services.IProgBillingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProgPrintBillCommandTests {

    
    private IProgrammeCommand printBillCommand;

    
    private IProgBillingService billingService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        billingService = Mockito.mock(IProgBillingService.class);
        printBillCommand = new PrintBillProgCommand(billingService);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void execute() {
        ProgrammeBillPrintDto programmeBillPrintDto = new ProgrammeBillPrintDto(1000, ProgrammeDisCountCoupons.NONE, 0, 100, 100, 500, 1600);
        when(billingService.calculateBill()).thenReturn(programmeBillPrintDto);

        printBillCommand.execute(Collections.emptyList());

        String expectedOutput = "SUB_TOTAL 1000.00\n" +
                "COUPON_DISCOUNT NONE 0.00\n" +
                "TOTAL_PRO_DISCOUNT 100.00\n" +
                "PRO_MEMBERSHIP_FEE 100.00\n" +
                "ENROLLMENT_FEE 500.00\n" +
                "TOTAL 1600.00\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void execute1() {
        ProgrammeBillPrintDto programmeBillPrintDto = new ProgrammeBillPrintDto(1000, ProgrammeDisCountCoupons.DEAL_G20, 20, 100, 100, 500, 1580);
        when(billingService.calculateBill()).thenReturn(programmeBillPrintDto);
        printBillCommand.execute(Collections.emptyList());

        String expectedOutput = "SUB_TOTAL 1000.00\n" +
                "COUPON_DISCOUNT DEAL_G20 20.00\n" +
                "TOTAL_PRO_DISCOUNT 100.00\n" +
                "PRO_MEMBERSHIP_FEE 100.00\n" +
                "ENROLLMENT_FEE 500.00\n" +
                "TOTAL 1580.00\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
