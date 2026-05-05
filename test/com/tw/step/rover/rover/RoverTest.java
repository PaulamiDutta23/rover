package com.tw.step.rover.rover;

import com.tw.step.rover.boundary.*;
import com.tw.step.rover.position.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverTest {
    @Test
    void shouldTurnAndMove() {
        Rover rover = new Rover(new Coordinate(0, 0), Direction.N);
        Navigator navigator = Navigator.create();
        InfinitePlateau boundary = new InfinitePlateau();

        rover.turnRight(navigator, boundary);
        rover.move(navigator, boundary);

        assertEquals("1 0 E", rover.toString());
    }

    @Test
    void shouldMoveAndTurnAndBecomeLost() {
        Rover rover = new Rover(new Coordinate(5, 4), Direction.N);
        Navigator navigator = Navigator.create();
        Boundary boundary = new Plateau(new Coordinate(0,0),new Coordinate(5,6));

        rover.move(navigator, boundary);
        rover.move(navigator, boundary);
        rover.turnRight(navigator, boundary);
        rover.move(navigator, boundary);
        rover.turnLeft(navigator, boundary);

        assertEquals("5 6 E LOST", rover.toString());
    }
}
