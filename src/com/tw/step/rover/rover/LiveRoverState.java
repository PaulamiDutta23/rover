package com.tw.step.rover.rover;

import com.tw.step.rover.boundary.*;
import com.tw.step.rover.position.*;

public class LiveRoverState implements RoverState {
    private final Rover rover;

    public LiveRoverState(Rover rover) {
        this.rover = rover;
    }

    @Override
    public RoverState turnLeft(Navigator navigator, Boundary boundary) {
        this.rover.turnLeftInternal(navigator);
        return this;
    }

    @Override
    public RoverState turnRight(Navigator navigator, Boundary boundary) {
        this.rover.turnRightInternal(navigator);
        return this;
    }

    @Override
    public RoverState move(Navigator navigator, Boundary boundary) {
        Coordinate nextCoordinate = rover.getNextCoordinateInternal(navigator);
        if(!rover.isWithin(boundary)) {
            DeadRoverState deadRoverState = new DeadRoverState(rover);
            return deadRoverState;
        }
        rover.setCoordinate(nextCoordinate);
        return this;
    }

}
