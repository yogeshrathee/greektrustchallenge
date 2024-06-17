package com.geektrust.backend.Command;

import java.util.List;

public interface IProgrammeCommand {
    void execute(List<String> tokens);
}
