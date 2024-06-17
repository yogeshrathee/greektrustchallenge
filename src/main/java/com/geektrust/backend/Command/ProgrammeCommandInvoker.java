package com.geektrust.backend.Command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.Exception.NoSuchProgCommandException;

public class ProgrammeCommandInvoker {

    private static final Map<String, IProgrammeCommand> commandMap = new HashMap<>();
    public void register(String commandName, IProgrammeCommand command){
        commandMap.put(commandName,command);
    }
    private IProgrammeCommand get(String commandName){
        return commandMap.get(commandName);
    }
    public void executeCommand(String commandName, List<String> tokens) throws NoSuchProgCommandException {
        IProgrammeCommand command = get(commandName);
        if(command == null){
            throw new NoSuchProgCommandException();
        }
        command.execute(tokens);
    }

    
}
