package Server;

public class Robot {
    private int x;
    private int y;
    private String name;
    private RobotStatus status;
    private Direction currentDirection;

    public Robot(String name){
        this.x = 0;
        this.y = 0;
        this.name = name;
        this.currentDirection = Direction.NORTH;
        this.status = RobotStatus.NORMAL;
    }

    public void updatePosition(int steps){
        if(currentDirection.equals(Direction.NORTH)){
            y = y + steps;
        }
        if(currentDirection.equals(Direction.SOUTH)){
            y = y - steps;
        }
        if(currentDirection.equals(Direction.EAST)){
            x = x + steps;
        }
        if(currentDirection.equals(Direction.WEST)){
            x = x - steps;
        }
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
}
