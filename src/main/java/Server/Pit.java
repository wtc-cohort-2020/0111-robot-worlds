package Server;

public class Pit {
    private int bottomLeftX;
    private int bottomLeftY;

    private int size;


    public Pit(int bottomLeftX, int bottomLeftY, int size){
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

    public boolean blocksPosition(int positionX, int positionY){
        return (positionX >= bottomLeftX && positionX <= bottomLeftX + 4) &&
                (positionY >= bottomLeftY && positionY <= bottomLeftY + 4);
    }

    public int getSize() {
        return size;
    }
}
