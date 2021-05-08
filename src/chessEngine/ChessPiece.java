package chessEngine;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {

    protected static final int PAWN = 1;
    protected static final int ROOK = 2;
    protected static final int KNIGHT = 4;
    protected static final int BISHOP = 8;
    protected static final int QUEEN = 16;
    protected static final int KING = 32;

    protected static final int COLOR_WHITE = 64;
    protected static final int COLOR_BLACK = 128;

    //                                                {N,  E, S, W,  NE, NW, SE, SW}
    protected static final  int[] MOMENT_DIRECTIONS = {-8, 1, 8, -1, -7, -6,  7, 6};

    protected int pieceId;
    protected Position pos;
    protected List<Integer> possibleMoves;

    public ChessPiece(int pieceId, Position pos){
        this.pieceId = pieceId;
        this.pos = pos;
        this.possibleMoves = new ArrayList<Integer>();
    }

    public char getPieceColor(){
        char pieceColor;
        if((this.pieceId & COLOR_WHITE) == COLOR_WHITE){
            pieceColor = 'W';
        }else if((this.pieceId & COLOR_BLACK) == COLOR_BLACK){
            pieceColor = 'B';
        }else{
            pieceColor = 'E';
        }
        return pieceColor;
    }

    public String getPieceType(){
        String pieceType;
        if((this.pieceId & PAWN) == PAWN){
            pieceType = "Pawn";
        }else if((this.pieceId & ROOK) == ROOK){
            pieceType = "Rook";
        }else if((this.pieceId & KNIGHT) == KNIGHT){
            pieceType = "Knight";
        }else if((this.pieceId & BISHOP) == BISHOP){
            pieceType = "Bishop";
        }else if((this.pieceId & QUEEN) == QUEEN){
            pieceType = "Queen";
        }else if((this.pieceId & KING) == KING) {
            pieceType = "King";
        }else{
            pieceType = "Error";
        }
        return pieceType;
    }

    public void updatePossibleMoves(ChessBoard board){
        this.possibleMoves.clear();
        String pieceType = this.getPieceType();
        if((pieceType == "Rook") || (pieceType == "Bishop") || (pieceType == "Queen")){
            this.slidingPieceMoves(board);
            return;
        }
    }

    public void slidingPieceMoves(ChessBoard board){
        int startingDirection, endingDirection;
        if((this.pieceId & ROOK) == ROOK){
            startingDirection = 0;
            endingDirection = 3;
        }else if((this.pieceId & BISHOP) == BISHOP){
            startingDirection = 4;
            endingDirection = 8;
        }else{
            startingDirection = 0;
            endingDirection = 8;
        }
        Position tempPos;
        for(int i = startingDirection; i <= endingDirection; i++){
            tempPos = this.pos.getPositionToDirection(MOMENT_DIRECTIONS[i]);
            while(tempPos.isValid()){
                if(board.getChessPieces().containsKey(tempPos.getPosition())){
                    ChessPiece piece = board.getChessPieces().get(tempPos.getPosition());
                    if(this.isEnemy(piece)){
                        if(piece.isKing()){
                            this.attackReport( (King) piece);
                        }
                        this.possibleMoves.add(tempPos.getPosition());
                    }
                    break;
                }else{
                    this.possibleMoves.add(tempPos.getPosition());
                }
                tempPos = tempPos.getPositionToDirection(MOMENT_DIRECTIONS[i]);
            }
        }
    }

    public boolean isEnemy(ChessPiece other){
        if(this.getPieceColor() != other.getPieceColor()){
            return false;
        }
        return true;
    }

    public boolean isKing(){
        if((this.pieceId & KING) == KING){
            return true;
        }
        return false;
    }

    public void attackReport(@NotNull King king){
        king.setInAttack();
    }

    public String repr(){
        String info = "";
        info += "{ piece type : " + this.getPieceType();
        info += ", piece color : " + this.getPieceColor();
        info += ", piece pos : " + this.pos.getPosition();
        info += ", piece id : " + this.pieceId + "}";
        return info;
    }

}
