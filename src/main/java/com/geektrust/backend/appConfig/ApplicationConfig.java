package com.geektrust.backend.appConfig;

import com.geektrust.backend.commands.AddDriverCommand;
import com.geektrust.backend.commands.AddRiderCommand;
import com.geektrust.backend.commands.BillCommand;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.commands.MatchRiderCommand;
import com.geektrust.backend.commands.StartRideCommand;
import com.geektrust.backend.commands.StopRideCommand;
import com.geektrust.backend.repositories.DriverRepository;
import com.geektrust.backend.repositories.IDriverRepository;
import com.geektrust.backend.repositories.IRiderRepository;
import com.geektrust.backend.repositories.ITripRepository;
import com.geektrust.backend.repositories.RiderRepository;
import com.geektrust.backend.repositories.TripRepository;
import com.geektrust.backend.services.DriverService;
import com.geektrust.backend.services.IDriverService;
import com.geektrust.backend.services.IRiderService;
import com.geektrust.backend.services.ITripService;
import com.geektrust.backend.services.RiderService;
import com.geektrust.backend.services.TripService;

public class ApplicationConfig{
    //Instantiate the repos: 
    private final IRiderRepository  riderRepository = new RiderRepository();
    private final IDriverRepository driverRepository = new DriverRepository();
    private final ITripRepository tripRepository = new TripRepository();

    //Instantiate the service classes: 
    private final IRiderService riderService = new RiderService(riderRepository);
    private final IDriverService driverService = new DriverService(driverRepository,riderRepository);
    private final ITripService tripService = new TripService(riderRepository, tripRepository, driverRepository);

    //Commands:
    private final AddDriverCommand addDriverCommand = new AddDriverCommand(driverService);
    private final AddRiderCommand  addRiderCommand = new AddRiderCommand(riderService);
    private final MatchRiderCommand matchRiderCommand = new MatchRiderCommand(driverService);
    private final StartRideCommand startRideCommand = new StartRideCommand(tripService, driverService);
    private final StopRideCommand stopRideCommand = new StopRideCommand(tripService);
    private final BillCommand billCommand = new BillCommand(tripService);

    private final CommandInvoker commandInvoker = new CommandInvoker();
    public CommandInvoker registerCommands() {
        commandInvoker.register("ADD_DRIVER", addDriverCommand);
        commandInvoker.register("ADD_RIDER", addRiderCommand);
        commandInvoker.register("MATCH", matchRiderCommand);
        commandInvoker.register("START_RIDE", startRideCommand);
        commandInvoker.register("STOP_RIDE", stopRideCommand);
        commandInvoker.register("BILL", billCommand);
        return commandInvoker;
    }
}