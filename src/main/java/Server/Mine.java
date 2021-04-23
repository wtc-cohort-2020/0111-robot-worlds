package Server;

public class Mine {
    private int x;
    private int y;

    public Mine(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean blocksPosition(int positionX, int positionY){
        return positionX == x && positionY == y;
    }
}
