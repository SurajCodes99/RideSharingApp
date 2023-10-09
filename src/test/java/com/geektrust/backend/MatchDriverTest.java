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

@DisplayName("Match Driver Test")
class MatchDriverTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void OrderingOfDriversInList() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/MatchDriverTestCases/MatchDriverTest1.txt"));
        String expectedOutput = "DRIVERS_MATCHED D6 D5 D1 Ravi1 Suraj1";

        // Act   
        App.runApplication(arguments);

        // Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
    }

    @Test
    public void NoDriversAvailableTest() throws Exception{
        // Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/MatchDriverTestCases/MatchDriverTest2.txt"));
        String expectedOutput = "DRIVERS_MATCHED D6\n" + 
        "RIDE_STARTED RIDE-001\n"+
        "NO_DRIVERS_AVAILABLE";

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

