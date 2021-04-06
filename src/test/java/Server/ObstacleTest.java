package Server;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ObstacleTest {
    private final Obstacle obstacle = new Obstacle(5,10,5);

    @Test
    void testGetBottomLeftX(){
        assertEquals(5, obstacle.getBottomLeftX());
    }

    @Test
    void testGetBottomLeftY(){
        assertEquals(10, obstacle.getBottomLeftY());
    }

    @Test
    void testGetSize(){
        assertEquals(5,obstacle.getSize());
    }

    @Test
    void testBlocksPositionTrue(){
        assertTrue(obstacle.blocksPosition(5,10));
        assertTrue(obstacle.blocksPosition(9,14));
        assertTrue(obstacle.blocksPosition(5,14));
        assertTrue(obstacle.blocksPosition(9,10));
    }

    @Test
    void testBlocksPositionFalse(){
        assertFalse(obstacle.blocksPosition(4, 10));
        assertFalse(obstacle.blocksPosition(5, 9));

        assertFalse(obstacle.blocksPosition(9, 15));
        assertFalse(obstacle.blocksPosition(10, 14));

        assertFalse(obstacle.blocksPosition(4, 14));
        assertFalse(obstacle.blocksPosition(5, 15));

        assertFalse(obstacle.blocksPosition(10, 10));
        assertFalse(obstacle.blocksPosition(9, 9));
    }
}
