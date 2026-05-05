package com.tw.step.rover.rover;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;

public class Rover {
    private final String id;
    private RoverState roverState;
    private Coordinate nextCoordinate;
    private Coordinate coordinate;
    private Direction heading;

    public Rover(String id, Coordinate coordinate, Direction heading) {
        this.id = id;
        this.coordinate = coordinate;
        this.heading = heading;
        this.roverState = new LiveRoverState(this);
    }

    public Rover(Coordinate coordinate, Direction heading) {
        this("", coordinate, heading);
    }

    public void turnLeft(Navigator navigator, Boundary boundary) {
        this.roverState = this.roverState.turnLeft(navigator, boundary);
    }

    public void turnRight(Navigator navigator, Boundary boundary) {
        this.roverState = this.roverState.turnRight(navigator, boundary);
    }

    public void move(Navigator navigator, Boundary boundary) {
        this.roverState = this.roverState.move(navigator, boundary);
    }

    void turnLeftInternal(Navigator navigator) {
        this.heading = navigator.leftOf(this.heading);
    }

    void turnRightInternal(Navigator navigator) {
        this.heading = navigator.rightOf(this.heading);
    }

    Coordinate getNextCoordinateInternal(Navigator navigator) {
        this.nextCoordinate = navigator.nextCoordinate(this.coordinate, this.heading);
        return this.nextCoordinate;
    }

    void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    boolean isWithin(Boundary boundary) {
        return boundary.isWithin(this.nextCoordinate);
    }

    @Override
    public String toString() {
        String coordinate = this.coordinate.toString();
        String heading = this.heading.toString();

        String state = String.format("%s %s",coordinate, heading);
        if(this.roverState instanceof DeadRoverState) {
            return state.concat(" LOST");
        }
        return  state;
    }
}
