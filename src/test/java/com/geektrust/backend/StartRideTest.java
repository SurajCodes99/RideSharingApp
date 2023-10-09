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

@DisplayName("Start ride Test")
class StartRideTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void RideAlreadyExistsWithSameRider() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/StartRideTestCases/StartRideTest1.txt"));
        String expectedOutput = "DRIVERS_MATCHED D6 D5 D1 Ravi1 Suraj1\n" + 
        "RIDE_STARTED RIDE1\n"+
        "INVALID_RIDE";

        // Act   
        App.runApplication(arguments);

        // Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
    }

    @Test
    public void RideIdAlreadyExists() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/StartRideTestCases/StartRideTest2.txt"));
        String expectedOutput = "DRIVERS_MATCHED D2 D3 D1\n" + 
        "DRIVERS_MATCHED D1 D2 D3\n" +
        "RIDE_STARTED RIDE-101\n" +
        "INVALID_RIDE";

        // Act   
        App.runApplication(arguments);

        // Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
    }

    @Test
    public void driverSelectionGreaterThanAvailableDrivers() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/StartRideTestCases/StartRideTest3.txt"));
        String expectedOutput = "DRIVERS_MATCHED D1 D3\n" +
        "INVALID_RIDE";

        // Act   
        App.runApplication(arguments);

        // Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
    }

    @Test
    public void RideStartsCorrectly() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/StartRideTestCases/StartRideTest4.txt"));
        String expectedOutput = "DRIVERS_MATCHED D1 D3\n" +
        "RIDE_STARTED RIDE-101";

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
