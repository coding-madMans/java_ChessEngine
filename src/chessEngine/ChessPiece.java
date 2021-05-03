package chessEngine;

import java.util.List;

public abstract class ChessPiece{
    private int id;
    private String pieceType;
    private char pieceColor;
    private Position piecePosition;

    public ChessPiece(int id, String pieceType, char pieceColor, Position piecePosition){
        this.id = id;
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.piecePosition = piecePosition;
    }

    public int getId(){
        return this.id;
    }

    public String getPieceType(){
        return this.pieceType;
    }

    public char getPieceColor(){
        return this.pieceColor;
    }

    protected Position getPiecePosition(){
        return this.piecePosition;
    }

    public Position getPosition(){
        return this.piecePosition.clone();
    }

    public ChessPiece[] getAdjecentPieces(ChessBoard board){
        ChessPiece adjacentPiece[] = new ChessPiece[8];
        for (int i = 0; i < 8; i++){
            adjacentPiece[i] = null;
        }
        return adjacentPiece;
    }

    public void display() {}

    public String repr() {
        String info = "";
        info += "{" + "id : " + this.id;
        info += ", piece type : " + this.pieceType;
        info += ", piece color : " + this.pieceColor;
        info += ", piece Posotion : " + this.piecePosition.repr();
        info += "}";
        return info;
    }

    public List<Position> getPossibleMoves(ChessBoard board){
        return null;
    }

    public void capturePiece(ChessBoard board, ChessPiece capturedPiece) {}
}
