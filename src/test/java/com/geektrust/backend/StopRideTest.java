package com.geektrust.backend;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Stop ride Test")
class StopRideTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void RideDoesntExist() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/StopRideTestCases/StopRideTest1.txt"));
        String expectedOutput = "DRIVERS_MATCHED D6 D5 D1 Ravi1 Suraj1\n" + 
        "RIDE_STARTED RIDE1\n"+
        "INVALID_RIDE";

        // Act   
        App.runApplication(arguments);

        // Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
    }

    @Test
    public void RideIsAlreadyStopped() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/StopRideTestCases/StopRideTest2.txt"));
        String expectedOutput = "DRIVERS_MATCHED D6 D5 D1 Ravi1 Suraj1\n" + 
        "RIDE_STARTED RIDE1\n" +
        "RIDE_STOPPED RIDE1\n" + 
        "INVALID_RIDE";

        // Act   
        App.runApplication(arguments);

        // Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
    }

    @Test
    public void RideStopsCorrectly() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/StopRideTestCases/StopRideTest3.txt"));
        String expectedOutput = "DRIVERS_MATCHED D6 D5 D1 Ravi1 Suraj1\n" + 
        "RIDE_STARTED RIDE1\n" +
        "RIDE_STOPPED RIDE1";

        // Act   
        App.runApplication(arguments);

        // Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
