/*
package Server;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RobotTest {
    Obstacle obstacle = new Obstacle(0,2,5);
    Pit pit = new Pit(0,-6,5);

    ArrayList<Obstacle> obstacles = new ArrayList<>();
    ArrayList<Pit> pits = new ArrayList<>();

    World world;
    Robot robot;

    RobotTest(){
        pits.add(pit);
        obstacles.add(obstacle);
        world = new World(pits,obstacles);
    }

    @BeforeEach
    void setUp(){
        robot = new Robot("Gert", world);
    }

    @Test
    void testTurn(){
        robot.turn(true);
        assertEquals(Direction.EAST, robot.getCurrentDirection());
        robot.turn(false);
        robot.turn(false);
        assertEquals(Direction.WEST, robot.getCurrentDirection());
    }

    @Test
    void testForwardSuccess(){
        assertEquals(MovementStatus.Done, robot.moveForward());
        assertEquals(0, robot.getX());
        assertEquals(1, robot.getY());
        robot.turn(true);
        assertEquals(MovementStatus.Done, robot.moveForward());
        assertEquals(1, robot.getX());
        assertEquals(1, robot.getY());
    }

    @Test
    void testForwardObstructed(){
        assertEquals(MovementStatus.Done, robot.moveForward());
        assertEquals(0, robot.getX());
        assertEquals(1, robot.getY());
        assertEquals(MovementStatus.Obstructed, robot.moveForward());
    }

    @Test
    void testForwardFell(){
        robot.turn(true);
        robot.turn(true);
        assertEquals(MovementStatus.Done, robot.moveForward());
        assertEquals(MovementStatus.Fell, robot.moveForward());
    }

    @Test
    void testBackSuccess(){
        assertEquals(MovementStatus.Done, robot.moveBack());
        assertEquals(0, robot.getX());
        assertEquals(-1, robot.getY());
        robot.turn(false);
        assertEquals(MovementStatus.Done, robot.moveBack());
        assertEquals(1, robot.getX());
        assertEquals(-1, robot.getY());
    }

    @Test
    void testBackObstructed(){
        robot.turn(true);
        robot.turn(true);
        assertEquals(MovementStatus.Done, robot.moveBack());
        assertEquals(MovementStatus.Obstructed, robot.moveBack());
    }

    @Test
    void testBackFell(){
        assertEquals(MovementStatus.Done, robot.moveBack());
        assertEquals(0, robot.getX());
        assertEquals(-1, robot.getY());
        assertEquals(MovementStatus.Fell, robot.moveBack());
    }
}

 */
