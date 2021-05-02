package chessEngine;

public interface ChesePieceLogic {
    public void display();
    public String repr();
    public int pieceMovementLogic();
    public void capturePiece(ChessPiece capturedPiece);
}
