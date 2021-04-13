package Server;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Robot {
    private int x;
    private int y;
    private String name;
    private RobotStatus status;
    private Direction currentDirection;
    private final World world;
    private final Integer shotDistance;
    private Integer numberShots;
    private Random random = new Random();
    private int shieldStrength;
    private final Integer RELOAD_TIME = 5;
    private final Integer REPAIR_TIME = 4;


    public Robot(String name, World world){
        this.x = 0;
        this.y = 0;
        this.name = name;
        this.currentDirection = Direction.NORTH;
        this.status = RobotStatus.NORMAL;
        this.world = world;
        world.AddRobot(this);

        this.shieldStrength = world.getSniper().get("shield-strength");
        this.numberShots = world.getSniper().get("shots");
        this.shotDistance =  world.getSniper().get("shot-distance");
    }

    public MovementStatus moveForward(){
        if(currentDirection.equals(Direction.NORTH)){
            if(obstacleAtPosition(x,y+1)){
                return MovementStatus.Obstructed;
            }
            y = y + 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else if(currentDirection.equals(Direction.SOUTH)){
            if(obstacleAtPosition(x,y-1)){
                return MovementStatus.Obstructed;
            }
            y = y - 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else if(currentDirection.equals(Direction.EAST)){
            if(obstacleAtPosition(x+1,y)){
                return MovementStatus.Obstructed;
            }
            x = x + 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else{
            if(obstacleAtPosition(x-1,y)){
                return MovementStatus.Obstructed;
            }
            x = x - 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
    }

    public MovementStatus moveBack(){
        if(currentDirection.equals(Direction.NORTH)){
            if(obstacleAtPosition(x,y-1)){
                return MovementStatus.Obstructed;
            }
            y = y - 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else if(currentDirection.equals(Direction.SOUTH)){
            if(obstacleAtPosition(x,y+1)){
                return MovementStatus.Obstructed;
            }
            y = y + 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else if(currentDirection.equals(Direction.EAST)){
            if(obstacleAtPosition(x-1,y)){
                return MovementStatus.Obstructed;
            }
            x = x - 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else{
            if(obstacleAtPosition(x+1,y)){
                return MovementStatus.Obstructed;
            }
            x = x + 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
    }

    private boolean obstacleAtPosition(int x, int y){
        for(Obstacle obstacle: world.getObstacles()){
            if(obstacle.blocksPosition(x,y)){
                return true;
            }
        }
        return false;
    }

    private boolean pitAtPosition(int x, int y){
        for (Pit pit: world.getPits()){
            if(pit.blocksPosition(x,y)){
                return true;
            }
        }
        return false;
    }

    public void turn(boolean turnRight){
        if(turnRight){
            if(currentDirection == Direction.NORTH){
                currentDirection = Direction.EAST;
            }
            else if(currentDirection == Direction.EAST){
                currentDirection = Direction.SOUTH;
            }
            else if(currentDirection == Direction.SOUTH){
                currentDirection = Direction.WEST;
            }
            else if(currentDirection == Direction.WEST){
                currentDirection = Direction.NORTH;
            }
        }
        else {
            if(currentDirection == Direction.NORTH){
                currentDirection = Direction.WEST;
            }
            else if(currentDirection == Direction.WEST){
                currentDirection = Direction.SOUTH;
            }
            else if(currentDirection == Direction.SOUTH){
                currentDirection = Direction.EAST;
            }
            else if(currentDirection == Direction.EAST){
                currentDirection = Direction.NORTH;
            }
        }
    }

    // nmeintje code
    public void fire() {

        if (numberShots == 0) {
            System.out.println("You need to reload!");
            return;
        }
        /*
        Within current direction, check if another robot in current x/y +/-
        shotDistance.
         */
        if (currentDirection == Direction.NORTH) {
            // if another robot in current y + shotDistance
        }
        if (currentDirection == Direction.EAST) {
            // if another robot in current x + shotDistance
        }
        if (currentDirection == Direction.SOUTH) {
            // if another robot in current y - shotDistance
        }
        if (currentDirection == Direction.WEST) {
            // if another robot in current x - shotDistance
        }

        //Decrease number shots by 1.
        numberShots -= 1;

    }

    public void reload() {
        this.status = RobotStatus.RELOADING;
        try {
            //wait reload time then reload.
            TimeUnit.SECONDS.sleep(RELOAD_TIME);
            numberShots = 6 - shotDistance;
        }


        catch (InterruptedException e) {
            System.out.println("Timeout occurred.");
        }
    }

    public void beenHit () {
        // if robot in line of another robot's fire
        //look at all robots in list
        if (this.currentDirection == Direction.NORTH) {
            for(Robot robot: Robot_list) {
                if (this.x == robot.x && robot.y > this.y &&
                        (robot.y <= this.y + shotDistance)) {
                    //decrease shield by 1 point
                    shieldStrength--;
                }
            }
        }


        if (this.currentDirection == Direction.EAST) {
            for(Robot robot: Robot_list) {
                if (this.y == robot.y && robot.x > this.x &&
                        robot.x <= this.x + shotDistance) {
                    //decrease shield by 1 point
                    robot.shieldStrength--;
                };
            }
        }


        if (this.currentDirection == Direction.SOUTH) {
            for(Robot robot: Robot_list) {
                if (this.x == robot.x && robot.y > this.y &&
                        robot.y <= this.y + shotDistance) {
                    //decrease shield by 1 point
                    robot.shieldStrength--;
                }
            }
        }


        if (this.currentDirection == Direction.WEST) {
            for(Robot robot: Robot_list) {
                if (this.y == robot.y && robot.x < this.x &&
                        robot.x >= this.x + shotDistance) {
                    //decrease shield by 1 point
                    robot.shieldStrength--;
                }
            }
        }



    }

    public void repair () {
        this.status = RobotStatus.REPAIRING;
        try {
            //wait repair-time
            TimeUnit.SECONDS.sleep(REPAIR_TIME);
            // increase shield to max.
            shieldStrength = 3;
        }


        catch (InterruptedException e) {
            System.out.println("Timeout occurred.");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public RobotStatus getStatus(){
        return status;
    }

    public void setStatus(RobotStatus status) {
        this.status = status;
    }
}
