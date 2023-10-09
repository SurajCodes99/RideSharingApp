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

@DisplayName("App Test")
class BaseCasesTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void sampleTest1() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/SampleTestCases/input1.txt"));
        String expectedOutput = "DRIVERS_MATCHED D1 D3\n" +
        "RIDE_STARTED RIDE-001\n" + 
        "RIDE_STOPPED RIDE-001\n" + 
        "BILL RIDE-001 D3 186.72";

        // Act   
        App.runApplication(arguments);

        // Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
    }

    @Test
    public void sampleTest2() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/SampleTestCases/input2.txt"));
        String expectedOutput = "DRIVERS_MATCHED D2 D3 D1\n" + 
        "DRIVERS_MATCHED D1 D2 D3\n" +
        "RIDE_STARTED RIDE-101\n" +
        "RIDE_STARTED RIDE-102\n" +
        "RIDE_STOPPED RIDE-101\n" +
        "RIDE_STOPPED RIDE-102\n" +
        "BILL RIDE-101 D2 234.64\n" + 
        "BILL RIDE-102 D1 258.00";

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
