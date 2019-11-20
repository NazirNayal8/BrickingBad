package utils;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position incrementX(int dx){
        this.x += dx;
        return this;
    }

    public Position incrementX(double dx){
        this.x += dx;
        return this;
    }

    public Position incrementY(double dy){
        this.y += dy;
        return this;
    }
}
