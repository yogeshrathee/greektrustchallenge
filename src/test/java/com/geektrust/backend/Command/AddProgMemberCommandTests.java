package com.geektrust.backend.Command;

import com.geektrust.backend.Services.IProgStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddProgMemberCommandTests {

    private IProgrammeCommand addProMembershipCommand;

    private IProgStudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = Mockito.mock(IProgStudentService.class);
        addProMembershipCommand = new AddProgrammeMemberProgrammeCommand(studentService);
    }

    @Test
    void execute() {
        addProMembershipCommand.execute(Collections.emptyList());
        verify(studentService, times(1)).setProMembershipPlan();
    }
}
