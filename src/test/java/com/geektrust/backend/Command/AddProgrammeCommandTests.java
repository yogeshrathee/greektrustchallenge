package com.geektrust.backend.Command;

import com.geektrust.backend.Services.IProgStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddProgrammeCommandTests {

    private IProgrammeCommand addProgrammeCommand;

    private IProgStudentService studentService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        studentService = Mockito.mock(IProgStudentService.class);
        addProgrammeCommand = new AddProgProgrammeCommand(studentService);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void execute() {
        addProgrammeCommand.execute(Arrays.asList("ADD_PROGRAMME", "DEGREE", "2"));
        verify(studentService, times(1)).addProgrammes("DEGREE", 2);
    }

   
}
