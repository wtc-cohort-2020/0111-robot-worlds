package Server;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Robot {
    private int x;
    private int y;
    private final String name;
    private RobotStatus status;
    private Direction currentDirection;
    private final World world;
    private final Integer shotDistance;
    private Integer numberShots;
    private int shields;
    private final Integer RELOAD_TIME;
    private final Integer REPAIR_TIME;
    private final Integer SET_MINE_TIME;
    private int distance;
    private Robot hitRobot;
    private int visibility;


    public Robot(String name, World world, Integer shield, Integer shots){
        RELOAD_TIME = world.getReload();
        REPAIR_TIME = world.getRepairShield();
        SET_MINE_TIME = world.getSteMineTime();
        visibility = world.getVisibility();

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
        if(shield > world.getMaxShield()){
            this.shields = world.getMaxShield();
        }
        else {
            this.shields = shield;
        }

        this.numberShots = shots;
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
            if(mineAtPosition(x, y)){
                this.steppedOnMine();
                return MovementStatus.Mine;
            }

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
            if(mineAtPosition(x, y)){
                this.steppedOnMine();
                return MovementStatus.Mine;
            }

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
            if(mineAtPosition(x, y)){
                this.steppedOnMine();
                return MovementStatus.Mine;
            }

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
            if(mineAtPosition(x, y)){
                this.steppedOnMine();
                return MovementStatus.Mine;
            }

            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
    }

    public MovementStatus moveBack(){
        if(currentDirection.equals(Direction.NORTH)){
            for(Robot robotInWorld: world.getRobots()){
                if(robotInWorld.getX() == x && robotInWorld.getY() == y-1){
                    return MovementStatus.Obstructed;
                }
            }
            if(obstacleAtPosition(x,y-1) || y-1 == -world.getWorldHeight()/2){
                return MovementStatus.Obstructed;
            }
            y = y - 1;
            if(mineAtPosition(x, y)){
                this.steppedOnMine();
                return MovementStatus.Mine;
            }
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else if(currentDirection.equals(Direction.SOUTH)){
            for(Robot robotInWorld: world.getRobots()){
                if(robotInWorld.getX() == x && robotInWorld.getY() == y+1){
                    return MovementStatus.Obstructed;
                }
            }
            if(obstacleAtPosition(x,y+1) || y+1 == world.getWorldHeight()){
                return MovementStatus.Obstructed;
            }
            y = y + 1;
            if(mineAtPosition(x, y)){
                this.steppedOnMine();
                return MovementStatus.Mine;
            }
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else if(currentDirection.equals(Direction.EAST)){
            for(Robot robotInWorld: world.getRobots()){
                if(robotInWorld.getX() == x-1 && robotInWorld.getY() == y){
                    return MovementStatus.Obstructed;
                }
            }
            if(obstacleAtPosition(x-1,y) || x-1 == -world.getWorldWidth()/2){
                return MovementStatus.Obstructed;
            }
            x = x - 1;
            if(mineAtPosition(x, y)){
                this.steppedOnMine();
                return MovementStatus.Mine;
            }
            if(pitAtPosition(x,y)){
                return MovementStatus.Fell;
            }
            return MovementStatus.Done;
        }
        else{
            for(Robot robotInWorld: world.getRobots()){
                if(robotInWorld.getX() == x+1 && robotInWorld.getY() == y){
                    return MovementStatus.Obstructed;
                }
            }
            if(obstacleAtPosition(x+1,y) || x+1 == world.getWorldWidth()/2){
                return MovementStatus.Obstructed;
            }
            x = x + 1;
            if(mineAtPosition(x, y)){
                this.steppedOnMine();
                return MovementStatus.Mine;
            }
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

    public boolean mineAtPosition(int x, int y){
        for (Mine mine: world.getMines()){
            if(mine.blocksPosition(x, y)){
                world.removeMine(mine);
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

    public void steppedOnMine(){
        shields -= 3;
        if (shields < 0){
            this.status = RobotStatus.DEAD;
            world.RemoveRobot(this);
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

    public void setMine(){
        int rememberShield = shields;
        shields = 0;
        try {
            TimeUnit.SECONDS.sleep(SET_MINE_TIME);
        }

        catch (InterruptedException e) {
            System.out.println("Timeout occurred.");
        }
        shields = rememberShield;
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

    public int getVisibility() {
        return visibility;
    }

    public Integer getRELOAD_TIME() {
        return RELOAD_TIME;
    }

    public Integer getSET_MINE_TIME() {
        return SET_MINE_TIME;
    }

    public Integer getShotDistance() {
        return shotDistance;
    }

    public Integer getREPAIR_TIME() {
        return REPAIR_TIME;
    }
}
