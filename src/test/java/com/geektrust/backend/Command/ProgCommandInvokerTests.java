package com.geektrust.backend.Command;

import com.geektrust.backend.Exception.NoSuchProgCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProgCommandInvokerTests {

    private ProgrammeCommandInvoker programmeCommandInvoker;

    private AddProgProgrammeCommand addProgCommand;
    
    private ApplyProgrammeCouponProgrammeCommand applyProgrammeCouponCommand;

    private AddProgrammeMemberProgrammeCommand addProgrammeMemberCommand;

    private PrintBillProgCommand printBillCommand;

    @BeforeEach
    void setUp() {
        
        addProgCommand = Mockito.mock(AddProgProgrammeCommand.class);
        applyProgrammeCouponCommand = Mockito.mock(ApplyProgrammeCouponProgrammeCommand.class);
        addProgrammeMemberCommand = Mockito.mock(AddProgrammeMemberProgrammeCommand.class);
        printBillCommand = Mockito.mock(PrintBillProgCommand.class);

        programmeCommandInvoker = new ProgrammeCommandInvoker();
        programmeCommandInvoker.register("ADD_PROGRAMME", addProgCommand);
        programmeCommandInvoker.register("APPLY_COUPON", applyProgrammeCouponCommand);
        programmeCommandInvoker.register("ADD_PRO_MEMBERSHIP", addProgrammeMemberCommand);
        programmeCommandInvoker.register("PRINT_BILL", printBillCommand);
    }

    @DisplayName("addProgrammeCommand method Should Execute Command")
    @Test
    void executeAddProgrammeCommand() throws NoSuchProgCommandException {
        programmeCommandInvoker.executeCommand("ADD_PROGRAMME", new ArrayList<String>());
        verify(addProgCommand, times(1)).execute(new ArrayList<String>());

    }

    @DisplayName("applyCouponCommand method Should Execute Command")
    @Test
    void executeApplyCouponCommand() throws NoSuchProgCommandException {
        programmeCommandInvoker.executeCommand("APPLY_COUPON", new ArrayList<String>());
        verify(applyProgrammeCouponCommand, times(1)).execute(new ArrayList<String>());

    }

    @DisplayName("addProMembershipCommand method Should Execute Command")
    @Test
    void executeAddProMembershipCommand() throws NoSuchProgCommandException {
        programmeCommandInvoker.executeCommand("ADD_PRO_MEMBERSHIP", new ArrayList<String>());
        verify(addProgrammeMemberCommand, times(1)).execute(new ArrayList<String>());

    }

    @DisplayName("printBillCommand method Should Execute Command")
    @Test
    void executePrintBillCommand() throws NoSuchProgCommandException {
        programmeCommandInvoker.executeCommand("PRINT_BILL", new ArrayList<String>());
        verify(printBillCommand, times(1)).execute(new ArrayList<String>());

    }



    @DisplayName("executeCommand method Should Throw Exception Given Incorrect CommandName and List of tokens")
    @Test
    void executeCommandWithException() {
        assertThrows(NoSuchProgCommandException.class, () -> programmeCommandInvoker.executeCommand("INVALID_COMMAND", new ArrayList<String>()));
    }
}

