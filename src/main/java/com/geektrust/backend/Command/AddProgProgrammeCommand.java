package com.geektrust.backend.Command;

import java.util.List;
import com.geektrust.backend.Services.IProgStudentService;

public class AddProgProgrammeCommand implements IProgrammeCommand {

    private final IProgStudentService studentService;

    public AddProgProgrammeCommand(IProgStudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute(List<String> tokens) {
        String programmeName = tokens.get(1);
        Integer quantity = Integer.parseInt(tokens.get(2));
        try {
            studentService.addProgrammes(programmeName, quantity);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
