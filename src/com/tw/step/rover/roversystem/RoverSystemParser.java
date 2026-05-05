package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.*;
import com.tw.step.rover.commands.*;
import com.tw.step.rover.position.*;
import com.tw.step.rover.rover.Rover;

public class RoverSystemParser {
    private final RoverSystemScanner scanner;
    private final Navigator navigator;
    private Boundary boundary = null;
    private final Boundary defaultBoundary;
    private final CommandCreator commandCreator;

    public RoverSystemParser(RoverSystemScanner scanner, Navigator navigator, Boundary defaultBoundary, CommandCreator commandCreator) {
        this.scanner = scanner;
        this.navigator = navigator;
        this.defaultBoundary = defaultBoundary;
        this.commandCreator = commandCreator;
    }

    private Rover parseRover() {
        Coordinate coordinate = scanner.scanCoordinate();
        Direction heading = scanner.scanDirection();
        return new Rover(coordinate, heading);
    }

    private Boundary parsePlateau() {
        Coordinate bottomRightCoord = new Coordinate(0,0);
        Coordinate topRightCoord = scanner.scanPlateauBoundary();

        if(topRightCoord == null) return this.defaultBoundary;

        return new Plateau(bottomRightCoord, topRightCoord);
    }

    public RoverSystem parse() {
        RoverSystem roverSystem = new RoverSystem();
        this.boundary = parsePlateau();
        Rover rover = parseRover();
        roverSystem.addRover(rover);
        RoverCommands roverCommands = parseRoverCommands();
        roverSystem.addCommands(roverCommands);
        return roverSystem;
    }

    private RoverCommands parseRoverCommands() {
        RoverCommands roverCommands = new RoverCommands();
        String instructions = scanner.consume();
        for (int i = 0; i < instructions.length(); i++) {
            RoverCommand roverCommand = commandCreator.create(instructions.charAt(i), navigator, boundary);
            roverCommands.add(roverCommand);
        }

        return roverCommands;
    }
}
