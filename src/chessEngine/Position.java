package chessEngine;

public class Position {
    private int row, col;

    public Position(){
        this.row = 0;
        this.col = 0;
    }

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public Position clone(){
        return new Position(this.row, this.col);
    }

    public void updatePositionRelative(int row, int col){
        this.row += row;
        this.col += col;
    }

    public Position getPositionReletive(int row, int col){
        Position pos = this.clone();
        pos.updatePositionRelative(row, col);
        return pos;
    }

    public boolean isValidPosition(){
        if(
            (this.row > 0) && (this.row < 8) &&
            (this.col > 0) && (this.col < 8)
        ){
            return true;
        }
        return false;
    }

    public String repr(){
        String info = "";
        info += "{" + "row : " + this.row + ", col : " + this.col + "}";
        return info;
    }
}
