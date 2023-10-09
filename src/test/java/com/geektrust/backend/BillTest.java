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

@DisplayName("To test bill function")
class BillTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void IncompleteRideTest() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/BillTestCases/BillTestCase1.txt"));
        String expectedOutput = "DRIVERS_MATCHED D1 D3\n" +
        "RIDE_STARTED RIDE-001\n" + 
        "RIDE_NOT_COMPLETED";

        // Act   
        App.runApplication(arguments);

        // Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
    }

    @Test
    public void InvalidRideTest() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/BillTestCases/BillTestCase2.txt"));
        String expectedOutput = "DRIVERS_MATCHED D1 D3\n" +
        "RIDE_STARTED RIDE-001\n" + 
        "RIDE_STOPPED RIDE-001\n" +
        "INVALID_RIDE";

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

