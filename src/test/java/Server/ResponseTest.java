package Server;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseTest {
    Response response = new Response();

    @Test
    void testUnsupportedCommand(){
        assertEquals("{\"result\":\"ERROR\",\"data\":{\"message\":\"Unsupported command\"}}",
                response.UnsupportedCommand());
    }

    @Test
    void testInvalidArguments(){
        assertEquals("{\"result\":\"ERROR\",\"data\":{\"message\":\"Could not parse arguments\"}}",
                response.InvalidArguments());
    }

//    @Test
//    void testMovementSuccess(){
//        assertEquals("{\"result\":\"OK\",\"state\":{\"position\":\"[15, 30]\",\"direction\":\"EAST\",\"status\":\"NORMAL\"},\"data\":{\"message\":\"Done\"}}",
//                response.MovementSuccess(15,30,Direction.EAST));
//    }

//    @Test
//    void testMovementObstructed(){
//        assertEquals("{\"result\":\"OK\",\"state\":{\"position\":\"[-10, 5]\",\"direction\":\"NORTH\",\"status\":\"NORMAL\"},\"data\":{\"message\":\"Obstructed\"}}",
//                response.MovementObstructed(-10,5,Direction.NORTH));
//    }

    @Test
    void testMovementIntoPit(){
        assertEquals("{\"result\":\"OK\",\"state\":{\"status\":\"DEAD\"},\"data\":{\"message\":\"Fell\"}}",
                response.MovementIntoPit());
    }

//    @Test
//    void testTurn(){
//        assertEquals("{\"result\":\"OK\",\"state\":{\"position\":\"[0, 0]\",\"direction\":\"EAST\",\"status\":\"NORMAL\"},\"data\":{\"message\":\"DONE\"}}",
//                response.Turn(0,0,Direction.EAST));
//    }

//    @Test
//    void testState(){
//        assertEquals("{\"state\":{\"position\":\"[5, 10]\",\"direction\":\"SOUTH\",\"status\":\"NORMAL\"}}",
//                response.State(5,10,Direction.SOUTH));
//    }

//    @Test
//    void testLaunchSuccess(){
//        assertEquals("{\"result\":\"OK\",\"state\":{\"position\":\"[0, 0]\",\"direction\":\"NORTH\",\"status\":\"NORMAL\"},\"data\":{\"position\":\"[0, 0]\"}}",
//                response.LaunchSuccess(0,0,Direction.NORTH));
//    }

    @Test
    void testLaunchNoSpace(){
        assertEquals("{ \"result\": \"ERROR\", \"data\": { \"message\": \"No more space in this world\" } }",
                response.LaunchNoSpace());
    }

    @Test
    void testLaunchNameTaken(){
        assertEquals("{ \"result\": \"ERROR\", \"data\": { \"message\": \"Too many of you in this world\" } }",
                response.LaunchNameTaken());
    }
}
