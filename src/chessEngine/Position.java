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
        if((this.pos >= 1) && (this.pos <= 64)){
            return true;
        }
        return false;
    }

    public Position getPositionToDirection(int direction){
        Position Pos = new Position(this.pos + direction);
        return Pos;
    }
}
