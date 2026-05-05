package com.tw.step.rover.boundary;

import com.tw.step.rover.position.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {
    @Test
    void shouldAllowCoordinateWithinInclusiveBounds() {
        Plateau plateau = new Plateau(new Coordinate(0, 0), new Coordinate(2, 2));

        assertTrue(plateau.isWithin(new Coordinate(2, 2)));
        assertFalse(plateau.isWithin(new Coordinate(3, 2)));
    }
}
