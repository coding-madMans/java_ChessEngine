package chessEngine;

public class Bishop extends ChessPiece {

    public Bishop(int pieceColor, Position pos) {
        super(ChessPiece.BISHOP | pieceColor, pos);
    }
}
