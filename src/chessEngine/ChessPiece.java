package chessEngine;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class ChessPiece {

    // pieceID flags
    protected static final int PAWN = 1;
    protected static final int ROOK = 2;
    protected static final int KNIGHT = 4;
    protected static final int BISHOP = 8;
    protected static final int QUEEN = 16;
    protected static final int KING = 32;

    protected static final int COLOR_WHITE = 64;
    protected static final int COLOR_BLACK = 128;

    // piece status flags
    protected static final int NO_FLAGS = 0;
    protected static final int BLOCKING_ENEMY_ATTACK = 1;
    protected static final int COLUMN_LOCKED = 2;
    protected static final int ROW_LOCKED = 4;
    protected static final int DIAGONAL_LOCKED = 8;
    protected static final int NE_BOUND_LOCK = 16;
    protected static final int ATTACK_MODE = 32;
    protected static final int BLOCK_MODE = 64;
    protected static final int KING_ATTACK_MODE = 128;

    //                                                {N,  E, S, W,  NE, NW, SE, SW}
    protected static final  int[] MOMENT_DIRECTIONS = {-8, 1, 8, -1, -7, -6,  7, 6};

    protected int pieceId;
    protected Position pos;
    protected List<Integer> possibleMoves;
    protected int pieceStatusFlags;

    public ChessPiece(int pieceId, Position pos){
        this.pieceId = pieceId;
        this.pos = pos;
        this.possibleMoves = new ArrayList<>();
        this.pieceStatusFlags = NO_FLAGS;
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
        if((pieceType.equals("Rook")) || (pieceType.equals("Bishop")) || (pieceType.equals("Queen"))){
            this.slidingPieceMoves(board);
        }
    }

    public void slidingPieceMoves(ChessBoard board){
        int startingDirection, endingDirection;
        if((this.pieceId & ROOK) == ROOK){
            startingDirection = 0;
            endingDirection = 3;
        }else if((this.pieceId & BISHOP) == BISHOP){
            startingDirection = 4;
            endingDirection = 7;
        }else{
            startingDirection = 0;
            endingDirection = 7;
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

    public static void filterIlLegalMoves(ChessBoard board){
        for(int i = 0; i < 2; i++){
            King kingPiece = (King)board.getChessPieces().get(board.getKingPos(i));
            kingPiece.caslingMove(board);
            HashMap<String, List<ChessPiece>> pieces = kingPiece.postVerifyTheMoves(board);
            if(!kingPiece.isCheckMate()){
                List<ChessPiece> enemyPieces = pieces.get("enemyPieces");
                for(ChessPiece piece : pieces.get("piecesToModify")){
                    piece.removeIllegalMoves(enemyPieces);
                }
            }
        }
    }

    public void removeIllegalMoves(List<ChessPiece> enemyPieces){
        String pieceType = this.getPieceType();
        if((pieceType.equals("Rook")) || (pieceType.equals("Bishop")) || (pieceType.equals("Queen"))){
            if((pieceType.equals("Rook")) && ((this.pieceStatusFlags & DIAGONAL_LOCKED) == DIAGONAL_LOCKED)){
                this.possibleMoves.clear();
                return;
            }
            if((pieceType.equals("Bishop")) && (((this.pieceStatusFlags & COLUMN_LOCKED) == COLUMN_LOCKED) || ((this.pieceStatusFlags & ROW_LOCKED) == ROW_LOCKED))){
                this.possibleMoves.clear();
                return;
            }
        }
        List<Integer> legalMoves = new ArrayList<>();
        if((this.pieceStatusFlags & ATTACK_MODE) == ATTACK_MODE){
            for(ChessPiece enemyPiece : enemyPieces){
                if(this.possibleMoves.contains(enemyPiece.pos.getPosition())){
                    legalMoves.add(enemyPiece.pos.getPosition());
                }
            }
        }
        if((this.pieceStatusFlags & BLOCK_MODE) == BLOCK_MODE){
            for(ChessPiece enemyPiece : enemyPieces){
                for(Integer enemyMove : enemyPiece.possibleMoves){
                    if(this.possibleMoves.contains(enemyMove)){
                        legalMoves.add(enemyMove);
                    }
                }
            }
        }
        this.possibleMoves.clear();
        this.possibleMoves = legalMoves;
    }

    public boolean isEnemy(ChessPiece other){
        return this.getPieceColor() == other.getPieceColor();
    }

    public boolean isKing(){
        return (this.pieceId & KING) == KING;
    }

    public void attackReport(@NotNull King king){
        king.setInCheck();
        this.pieceStatusFlags |= KING_ATTACK_MODE;
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
