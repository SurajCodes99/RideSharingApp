package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.appConfig.*;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.constants.Constants;

public class App {

	// To run the application use: ./gradlew run --args="sample_input/testCaseFile/input1.txt"
	public static void main(String[] args) {
		//Storing the arguments passed into a list: 
		List<String> commandLineArguments = new ArrayList<>();
		commandLineArguments = Arrays.asList(args);
		runApplication(commandLineArguments);
	}

	public static void runApplication(List<String> commandLineArguments) {
		//The filename is stored in fileName.
		//String[] inputAsArray = commandLineArguments.get(0).split("=");
		String fileName = commandLineArguments.get(Constants.ZERO);
		BufferedReader reader;
		ApplicationConfig applicationConfig = new ApplicationConfig();
		//Registered all the commands in commandInvoker.
		CommandInvoker commandInvoker = applicationConfig.registerCommands();
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			while(line != null){
				List<String> tokens = Arrays.asList(line.split(" "));
				commandInvoker.executeCommand(tokens.get(Constants.ZERO), tokens);
				line = reader.readLine();
			}
			reader.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
