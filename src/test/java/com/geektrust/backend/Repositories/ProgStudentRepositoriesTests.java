package com.geektrust.backend.Repositories;

import com.geektrust.backend.Entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProgStudentRepositoriesTests {

    private ProgStudentRepository studentRepository;
    private ProgrammeStudent programmeStudent;
    private CertProg certProg;
    private DegProg degProg;
    private DipProg dipProg;

    @BeforeEach
    public void setup() {
        programmeStudent = Mockito.mock(ProgrammeStudent.class);
        certProg = Mockito.mock(CertProg.class);
        degProg = Mockito.mock(DegProg.class);
        dipProg = Mockito.mock(DipProg.class);

        studentRepository = new ProgStudentRepository(programmeStudent, certProg, degProg, dipProg);
    }

    @Test
    public void testAddProgramsToCart() {
        studentRepository.addProgramsToCart(ProgrammeCat.CERTIFICATION, 2);
        Mockito.verify(certProg, Mockito.times(1)).addProgram(2);

        studentRepository.addProgramsToCart(ProgrammeCat.DEGREE, 3);
        Mockito.verify(degProg, Mockito.times(1)).addProgram(3);

        studentRepository.addProgramsToCart(ProgrammeCat.DIPLOMA, 4);
        Mockito.verify(dipProg, Mockito.times(1)).addProgram(4);
    }

    @Test
    public void testGetTotalProgrammeCount() {
        when(certProg.getProgramCount()).thenReturn(2);
        when(degProg.getProgramCount()).thenReturn(3);
        when(dipProg.getProgramCount()).thenReturn(4);

        Integer totalProgrammeCount = studentRepository.getTotalProgrammeCount();

        assertEquals(9, totalProgrammeCount);
    }
}
