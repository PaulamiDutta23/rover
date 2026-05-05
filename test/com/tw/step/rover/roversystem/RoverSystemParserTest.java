package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.InfinitePlateau;
import com.tw.step.rover.boundary.Plateau;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.position.Navigator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverSystemParserTest {
    @Test
    void shouldParseAndExecuteRoverSystemWithInfinitePlateauBoundary() {
        RoverSystemScanner scanner = RoverSystemScanner.from("1 2 N\nRFF");
        RoverSystemParser parser = new RoverSystemParser(scanner, Navigator.create(), new InfinitePlateau(), new CommandCreator());

        RoverSystem roverSystem = parser.parse();
        roverSystem.execute();

        assertEquals("3 2 E", roverSystem.toString());
    }

    @Test
    void shouldParseAndExecuteRoverSystemWithFixedPlateauBoundary() {
        RoverSystemScanner scanner = RoverSystemScanner.from("2 2 \n1 2 N\nRFF");
        RoverSystemParser parser = new RoverSystemParser(scanner, Navigator.create(), Plateau.init(), new CommandCreator());

        RoverSystem roverSystem = parser.parse();
        roverSystem.execute();

        assertEquals("2 2 E LOST", roverSystem.toString());
    }
}
