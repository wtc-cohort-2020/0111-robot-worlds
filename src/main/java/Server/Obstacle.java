package Server;

public class Obstacle {
    private int bottomLeftX;
    private int bottomLeftY;

    private int size;

    public Obstacle(int bottomLeftX, int bottomLeftY, int size){
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
        this.size = size;
    }

    public int getBottomLeftX(){
        return bottomLeftX;
    }

    public int getBottomLeftY(){
        return bottomLeftY;
    }

    public int getSize(){
        return size;
    }

    public boolean blocksPosition(int positionX, int positionY){

        return false;
    }

    public boolean blocksPath(int newPositionX, int newPositionY, int positionX, int positionY){
        return true;
    }
}
