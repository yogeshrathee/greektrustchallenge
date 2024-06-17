package com.geektrust.backend.Command;

import java.util.List;
import com.geektrust.backend.Services.IProgStudentService;

public class AddProgrammeMemberProgrammeCommand implements IProgrammeCommand {

    private final IProgStudentService studentService;

    public AddProgrammeMemberProgrammeCommand(IProgStudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute(List<String> tokens) {
        studentService.setProMembershipPlan(); 
    }
    
}
