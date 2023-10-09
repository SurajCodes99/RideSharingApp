Project based on: 
* Language used: Java 1.8/1.11/1.15
* Build tool: Gradle 6

#Problem Statement:
 *Context
  Once upon a time, there was a group of entrepreneurs who were busy building a new ride-sharing service. They were working tirelessly to make sure that their service was the best it could be for their riders (i.e passengers) and drivers. However, they knew that there    was one important feature missing, the ability to match riders with drivers within a 5km range (inclusive).
  One day, they realized that they needed to implement this feature as soon as possible, and they needed someone to take on the challenge of creating it. Can you help them to complete this?
 
 *Euclidean Distance Formula
 For ease of calculation, this service assumes a Cartesian coordinate system to represent locations, where the location of drivers and riders are represented as a Point(x, y). The Euclidean distance is utilized to calculate the distance between any two points, and 1    
 unit is equivalent to 1 km.
 
 The Euclidean distance formula says:
 
 d = √[ (x2–x1)2 + (y2–y1)2]
 where,
 (x1, y1) are the coordinates of one point. 
 (x2, y2) are the coordinates of the other point. 
 d is the distance between (x1, y1) and (x2, y2).
 
*Goal
 Your task is to build a solution that will help to match riders with drivers based on their location and generate a bill for the ride. 
 
*Assumptions
 It is guaranteed that no two drivers or riders will have the same id. 
 Ride can only be started once the match is completed. 
 Every start ride request will happen after the match request. 
 Every start ride request will have a valid rider id. 
 One rider can make multiple match requests. 
 Bill for the ride will be calculated based on the distance between the rider's location and the destination. 
 Bill can only be generated after the ride is completed. 
 The driver will not be available to accept another rider's request after the ride has started. 
 Time taken for a ride cannot be negative. 
 All floating point numerical values must be rounded to two decimal places.

# How to run the code
Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows.

Internally both the scripts run the following commands

* `gradle clean build -x test --no-daemon` - This will create a jar file `geektrust.jar` in the `build/libs` folder.
* `java -jar build/libs/geektrust.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument

# How to execute the unit tests and understanding the purpose of each test.

 `gradle clean test --no-daemon` will execute the unit test cases.
  Various test cases can be found under the RideSharingApp/src/test/java/com/geektrust/backend/ folder.
  The path to the test cases can be found in RideSharingApp/sample_input.
  
  * The testcase BaseCasesTest.java corresponds with the files present in RideSharingApp/sample_input/SampleTestCases
  * The testcase BillTest.java corresponds with the files present in RideSharingApp/sample_input/BillTestCases. This tests the billing functionality of the application. Checks for edge cases. (ex: "If the ride hasn't stopped yet").
  * The testcase MatchDriverTest.java corresponds with the files present in RideSharingApp/sample_input/MatchDriverTestCases. Checks various edge conditions for which the application might fail.(ex: "If there aren't any drivers around").
  * The testcase StartRideTest.java corresponds with the files present in RideSharingApp/sample_input/StartRideTestCases. Test the start ride functionality, also checks for edge cases. (ex: "Starting a ride when a ride is already ongoing").
  * The testcase StopRideTest.java corresponds with the files present in RideSharingApp/sample_input/StopRideTestCases. Test the stop ride functionality, also checks for edge cases. (ex: "Stoppinng a ride that has already stopped.").

# Points to be noted: 
  * This application only adheres to the basic requirements of a RideSharingApp mimicking Uber, Ola, etc. It doesn't handle exceptions gracefully. Hence has a lot of room for improvement. 
