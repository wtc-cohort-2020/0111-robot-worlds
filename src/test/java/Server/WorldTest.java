package Server;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Hashtable;

public class WorldTest {
    Obstacle obstacle = new Obstacle(5,10,5);
    Pit pit = new Pit(20,30,5);

    ArrayList<Obstacle> obstacles = new ArrayList<>();
    ArrayList<Pit> pits = new ArrayList<>();

    World randomObstacles;
    World customObstacles;

    WorldTest(){
        pits.add(pit);
        obstacles.add(obstacle);
        randomObstacles = new World();
        customObstacles = new World(pits,obstacles);
    }

    @Test
    void testGetObstacles(){
        assertEquals(17, randomObstacles.getObstacles().size());
        assertEquals(obstacles, customObstacles.getObstacles());
    }

    @Test
    void testGetPits(){
        assertEquals(14, randomObstacles.getPits().size());
        assertEquals(pits, customObstacles.getPits());
    }
}
