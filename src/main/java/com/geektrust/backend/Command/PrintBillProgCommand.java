package com.geektrust.backend.Command;

import java.util.List;
import com.geektrust.backend.Services.IProgBillingService;

public class PrintBillProgCommand implements IProgrammeCommand {

    private final IProgBillingService billingService;

    public PrintBillProgCommand(IProgBillingService billingService) {
        this.billingService = billingService;
    }

    @Override
    public void execute(List<String> tokens) {
        System.out.println(billingService.calculateBill());      
    }
    
}
