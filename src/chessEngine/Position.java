package chessEngine;

public class Position {
    private int pos;

    public Position(int pos){
        this.pos = pos;
    }

    public void setPosition(int pos){
        this.pos = pos;
    }

    public int getPosition() {
        return this.pos;
    }

    public boolean isValid(){
        return (this.pos >= 1) && (this.pos <= 64);
    }

    public Position getPositionToDirection(int direction){
        return new Position(this.pos + direction);
    }
}
