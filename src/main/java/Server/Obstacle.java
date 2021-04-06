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
        /*
        This function take one Position instance.
        It checks if this obstacle blocks the path.
        */
        if (getBottomLeftX() <= positionX && positionX <= (getBottomLeftX()+4) &&
                getBottomLeftY() <= positionY && positionY <= (getBottomLeftY()+4)) {

            return true;
        }
        return false;
    }

//    public boolean blocksPath(int newPositionX, int newPositionY, int positionX, int positionY){
//        return true;
//    }
}
