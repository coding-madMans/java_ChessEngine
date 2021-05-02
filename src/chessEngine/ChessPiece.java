package chessEngine;

public class ChessPiece implements ChesePieceLogic {
    private int id;
    private String pieceName;
    private char pieceColor;
    private Position piecePosition;

    public ChessPiece(int id, String pieceName, char pieceColor, Position piecePosition){
        this.id = id;
        this.pieceName = pieceName;
        this.pieceColor = pieceColor;
        this.piecePosition = piecePosition;
    }

    public int getId(){
        return this.id;
    }

    public String getPieceName(){
        return this.pieceName;
    }

    public char getPieceColor(){
        return this.pieceColor;
    }

    public Position getPiecePosition(){
        return this.piecePosition.clone();
    }

    @Override
    public void display() {}

    @Override
    public String repr() {
        String info = "";
        info += "{" + "id : " + this.id;
        info += ", piece name : " + this.pieceName;
        info += ", piece color : " + this.pieceColor;
        info += ", piece Posotion : " + this.piecePosition.repr();
        info += "}";
        return info;
    }

    @Override
    public int pieceMovementLogic() {
        return 0;
    }

    @Override
    public void capturePiece(ChessPiece capturedPiece) {}
}
