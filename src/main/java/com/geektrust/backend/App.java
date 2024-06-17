package com.geektrust.backend;

    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.Arrays;
    import java.util.LinkedList;
    import java.util.List;

    import com.geektrust.backend.ApplicationConfig.AppConfig;
    import com.geektrust.backend.Command.ProgrammeCommandInvoker;
    import com.geektrust.backend.Exception.NoSuchProgCommandException;

    public class App {

    	public static void main(String[] args) {
    		List<String> commandLineArguments = new LinkedList<>(Arrays.asList(args));
		    run(commandLineArguments);
    	}
    	public static void run(List<String> commandLineArgs) {
            AppConfig appConfig = new AppConfig();
            ProgrammeCommandInvoker programmeCommandInvoker = appConfig.getCommandInvoker();
            BufferedReader reader;
            String inputFile = commandLineArgs.get(0);
            try {
                reader = new BufferedReader(new FileReader(inputFile));
                String line = reader.readLine();
                while (line != null) {
                    List<String> tokens = Arrays.asList(line.split(" "));
                    programmeCommandInvoker.executeCommand(tokens.get(0),tokens);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException | NoSuchProgCommandException e) {
                e.printStackTrace();
            }

       }

    }