package chessEngine;

public class Rook extends ChessPiece{

    boolean moved;

    public Rook(int pieceColor, Position pos) {
        super(ChessPiece.ROOK | pieceColor, pos);

        this.moved = false;
    }
}
