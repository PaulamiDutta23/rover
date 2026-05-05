package com.tw.step.rover.roversystem;

import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RoverSystemScannerTest {
    @Test
    void shouldScanValuesFromInput() {
        RoverSystemScanner scanner = RoverSystemScanner.from("1 2 N");
        RoverSystemScanner coordinateScanner = RoverSystemScanner.from("2 0");
        RoverSystemScanner plateauScanner = RoverSystemScanner.from("5 5 \n1 2 N");
        RoverSystemScanner directionScanner = RoverSystemScanner.from("N");
        RoverSystemScanner consumeScanner = RoverSystemScanner.from("X");

        assertEquals("1", scanner.peek());
        assertEquals(1, scanner.scanNumber());
        assertEquals(new Coordinate(2, 0), coordinateScanner.scanCoordinate());
        assertEquals(new Coordinate(5, 5), plateauScanner.scanPlateauBoundary());
        assertEquals(Direction.N, directionScanner.scanDirection());
        assertEquals("X", consumeScanner.consume());
        assertNull(consumeScanner.consume());
    }
}
