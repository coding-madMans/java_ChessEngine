package chessEngine;

public class Rook extends ChessPiece{

    private boolean moved;

    public Rook(int pieceColor, Position pos, boolean moved) {
        super(ChessPiece.ROOK | pieceColor, pos);

        this.moved = moved;
    }

    public boolean isMoved(){
        return this.moved;
    }

    public void setMoved(boolean moved){
        this.moved = moved;
    }
}
