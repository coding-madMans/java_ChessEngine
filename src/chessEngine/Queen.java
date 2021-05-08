package chessEngine;

public class Queen extends ChessPiece{

    public Queen(int pieceColor, Position pos) {
        super(ChessPiece.QUEEN | pieceColor, pos);
    }
}
