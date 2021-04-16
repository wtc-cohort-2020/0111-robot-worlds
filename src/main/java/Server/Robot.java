package Server;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
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
    private int shields = 3;
    private final Integer RELOAD_TIME = 5;
    private final Integer REPAIR_TIME = 4;
    private int distance;
    private Robot hitRobot;


    public Robot(String name, World world, String type){
        this.world = world;
        boolean positionClear = false;
        while (!positionClear){
            positionClear = true;
            this.x = ThreadLocalRandom.current().nextInt(-(world.getWorldWidth()/2)+1,
                    (world.getWorldWidth()/2));
            this.y = ThreadLocalRandom.current().nextInt(-(world.getWorldHeight()/2)+1,
                    (world.getWorldHeight()/2));
            for(Robot robotInWorld: world.getRobots()){
                if (robotInWorld.getX() == x && robotInWorld.getY() == y) {
                    positionClear = false;
                    break;
                }
            }
            if(obstacleAtPosition(x,y)){
                positionClear = false;
            }

            if(pitAtPosition(x,y)){
                positionClear = false;
            }

        }
        this.name = name;
        this.currentDirection = Direction.NORTH;
        this.status = RobotStatus.NORMAL;

        world.AddRobot(this);

        this.shields = world.getSniper().get("shield-strength");
        this.numberShots = world.getSniper().get("shots");
        shotDistance= 6 - numberShots;


    }

    public Robot(String name, World world){
        this.y = 0;
        this.x = 0;
        this.name = name;
        this.currentDirection = Direction.NORTH;
        this.status = RobotStatus.NORMAL;
        this.world = world;
        world.AddRobot(this);

        this.shields = 3;
        this.numberShots = 3;
        shotDistance= 6 - numberShots;


    }

    public MovementStatus moveForward(){
        if(currentDirection.equals(Direction.NORTH)){
            for(Robot robotInWorld: world.getRobots()){
                if(robotInWorld.getX() == x && robotInWorld.getY() == y+1){
                    return MovementStatus.Obstructed;
                }
            }
            if(obstacleAtPosition(x,y+1) || y+1 == world.getWorldHeight()/2){
                return MovementStatus.Obstructed;
            }
            y = y + 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else if(currentDirection.equals(Direction.SOUTH)){
            for(Robot robotInWorld: world.getRobots()){
                if(robotInWorld.getX() == x && robotInWorld.getY() == y-1){
                    return MovementStatus.Obstructed;
                }
            }
            if(obstacleAtPosition(x,y-1) || y-1 == -world.getWorldHeight()/2){
                return MovementStatus.Obstructed;
            }
            y = y - 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else if(currentDirection.equals(Direction.EAST)){
            for(Robot robotInWorld: world.getRobots()){
                if(robotInWorld.getX() == x+1 && robotInWorld.getY() == y){
                    return MovementStatus.Obstructed;
                }
            }
            if(obstacleAtPosition(x+1,y) || x+1 == world.getWorldWidth()/2){
                return MovementStatus.Obstructed;
            }
            x = x + 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else{
            for(Robot robotInWorld: world.getRobots()){
                if(robotInWorld.getX() == x-1 && robotInWorld.getY() == y){
                    return MovementStatus.Obstructed;
                }
            }
            if(obstacleAtPosition(x-1,y) || x-1 == -world.getWorldWidth()/2){
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
            if(obstacleAtPosition(x,y-1) || y-1 == -world.getWorldHeight()/2){
                return MovementStatus.Obstructed;
            }
            y = y - 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else if(currentDirection.equals(Direction.SOUTH)){
            if(obstacleAtPosition(x,y+1) || y+1 == world.getWorldHeight()){
                return MovementStatus.Obstructed;
            }
            y = y + 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else if(currentDirection.equals(Direction.EAST)){
            if(obstacleAtPosition(x-1,y) || x-1 == -world.getWorldWidth()/2){
                return MovementStatus.Obstructed;
            }
            x = x - 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else{
            if(obstacleAtPosition(x+1,y) || x+1 == world.getWorldWidth()/2){
                return MovementStatus.Obstructed;
            }
            x = x + 1;
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
    }

    public boolean obstacleAtPosition(int x, int y){
        for(Obstacle obstacle: world.getObstacles()){
            if(obstacle.blocksPosition(x,y)){
                return true;
            }
        }
        return false;
    }

    public boolean pitAtPosition(int x, int y){
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
        this.status = RobotStatus.NORMAL;
    }

    public boolean beenHit () {
        //Decrease shots by 1
        numberShots -= 1;

        // For all robots
        //Look for the closest robot in the line of fire
        if (this.currentDirection == Direction.NORTH) {
            for(Robot robot: world.getRobots()) {
                for (int i = 1; i <= shotDistance; i++) {
                    if (this.x == robot.x && robot.y > this.y &&
                            (robot.y <= this.y + i)) {
                        //decrease shield of wounded robot by 1 point
                        robot.decreaseShields();
                        if (robot.getShields() == -1) {
                            robot.setStatus(RobotStatus.DEAD);
                            world.RemoveRobot(robot);
                        }
                        distance = i;
                        hitRobot = robot;
                        return true;
                    }
                }

            }

        }


        if (this.currentDirection == Direction.EAST) {
            for(Robot robot: world.getRobots()) {
                for (int i = 1; i <= shotDistance; i++) {
                    if (this.y == robot.y && robot.x > this.x &&
                            robot.x == (this.x + i)) {
                        //decrease shield of wounded robot by 1 point
                        robot.decreaseShields();
                        if (robot.getShields() == -1) {
                            robot.setStatus(RobotStatus.DEAD);
                            world.RemoveRobot(robot);
                        }
                        distance = i;
                        hitRobot = robot;
                        return true;
                    };
                }

            }
        }


        if (this.currentDirection == Direction.SOUTH) {
            for(Robot robot: world.getRobots()) {
                for (int i = 1; i <= shotDistance; i++) {
                    if (this.x == robot.x && robot.y < this.y &&
                            (robot.y + i) == this.y) {
                        //decrease shield of wounded robot by 1 point
                        robot.decreaseShields();
                        if (robot.getShields() == -1) {
                            robot.setStatus(RobotStatus.DEAD);
                            world.RemoveRobot(robot);
                        }
                        distance = i;
                        hitRobot= robot;
                        return true;
                    }
                }

            }
        }


        if (this.currentDirection == Direction.WEST) {
            for(Robot robot: world.getRobots()) {
                for (int i = 1; i <= shotDistance; i++) {
                    if (this.y == robot.y && robot.x < this.x &&
                            (robot.x+i) == this.x) {
                        //decrease shield of wounded robot by 1 point
                        robot.decreaseShields();
                        if (robot.getShields() == -1) {
                            robot.setStatus(RobotStatus.DEAD);
                            world.RemoveRobot(robot);
                        }
                        distance = i;
                        hitRobot = robot;
                        return true;
                    }
                }

            }
        }

        return false;
        // if shield-strength equals 0, robot is dead.

    }

    public void repair () {
        this.status = RobotStatus.REPAIRING;
        try {
            //wait repair-time
            TimeUnit.SECONDS.sleep(REPAIR_TIME);
            // increase shield to max.
            shields = 3;
        }

        catch (InterruptedException e) {
            System.out.println("Timeout occurred.");
        }
        this.status = RobotStatus.NORMAL;
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

    public String getName () {
        return name;
    }

    public int getNumberShots() {
        return numberShots;
    }

    public int getShields() {
        return shields;
    }

    public int getDistance() {
        return distance;
    }

    public Robot returnHitRobot() {
        return hitRobot;
    }

    public void decreaseShields() {
        shields--;
    }

    public void increaseShields() {
        shields++;
    }

}
