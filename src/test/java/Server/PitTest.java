package Server;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PitTest {
    private final Pit pit = new Pit(5,10,5);

    @Test
    void testGetBottomLeftX(){
        assertEquals(5, pit.getBottomLeftX());
    }

    @Test
    void testGetBottomLeftY(){
        assertEquals(10, pit.getBottomLeftY());
    }

    @Test
    void testGetSize(){
        assertEquals(5, pit.getSize());
    }

    @Test
    void testBlocksPositionTrue(){
        assertTrue(pit.blocksPosition(5,10));
        assertTrue(pit.blocksPosition(9,14));
        assertTrue(pit.blocksPosition(5,14));
        assertTrue(pit.blocksPosition(9,10));
    }

    @Test
    void testBlocksPositionFalse(){
        assertFalse(pit.blocksPosition(4, 10));
        assertFalse(pit.blocksPosition(5, 9));

        assertFalse(pit.blocksPosition(9, 15));
        assertFalse(pit.blocksPosition(10, 14));

        assertFalse(pit.blocksPosition(4, 14));
        assertFalse(pit.blocksPosition(5, 15));

        assertFalse(pit.blocksPosition(10, 10));
        assertFalse(pit.blocksPosition(9, 9));
    }
}
