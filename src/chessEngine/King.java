package chessEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class King extends  ChessPiece{

    protected static final int NORMAL = 0;
    protected static final int IN_CHECK = 1;
    protected static final int CHECK_MATE = 2;

    private boolean moved;
    private int status;

    public King(int pieceColor, Position pos, boolean moved) {
        super(ChessPiece.KING | pieceColor, pos);

        this.moved = moved;
        this.status = NORMAL;
    }

    public void updatePossibleMoves(ChessBoard board){
        super.updatePossibleMoves(board);
        Position tempPos;
        for(int i = 0; i < 8; i++){
            tempPos = this.pos.getPositionToDirection(MOMENT_DIRECTIONS[i]);
            if(tempPos.isValid()){
                if(board.getChessPieces().containsKey(tempPos.getPosition())){
                    if(this.isEnemy(board.getChessPieces().get(tempPos.getPosition()))){
                        this.possibleMoves.add(tempPos.getPosition());
                    }
                }else{
                    this.possibleMoves.add(tempPos.getPosition());
                }
            }
        }
    }
    
    public void caslingMove(ChessBoard board){
        if((this.moved) || ((this.status & IN_CHECK) == IN_CHECK)){return;}
        int direction, numberOfTilesToCheck;
        boolean flag;
        Position tempPos = new Position(this.pos.getPosition());
        for(int i = 0; i < 2; i++){
            if(i == 0){
                direction = MOMENT_DIRECTIONS[1];
                numberOfTilesToCheck = 3;
            }else{
                direction = MOMENT_DIRECTIONS[3];
                numberOfTilesToCheck = 2;
            }
            flag = true;
            for (int j = 1; j <= numberOfTilesToCheck; j++){
                tempPos = tempPos.getPositionToDirection(direction);
                if(tempPos.isValid()){
                    if(board.getChessPieces().containsKey(tempPos.getPosition())){
                       flag = false;
                        break;
                    }
                }
            }
            if(flag){
                tempPos = tempPos.getPositionToDirection(direction);
                if(tempPos.isValid()){
                    if(board.getChessPieces().containsKey(tempPos.getPosition())){
                        ChessPiece piece = board.getChessPieces().get(tempPos.getPosition());
                        if(!this.isEnemy(piece)){
                            if((piece.pieceId & ROOK) == ROOK){
                                Rook rook = (Rook) piece;
                                if(!rook.isMoved()){
                                    tempPos = new Position(this.pos.getPosition());
                                    for(int k = 0; k < 2; k++){
                                        tempPos = tempPos.getPositionToDirection(direction);
                                    }
                                    this.possibleMoves.add(tempPos.getPosition());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public HashMap<String, List<ChessPiece>> postVerifyTheMoves(ChessBoard board){
        // checking if the moving of the king causes it to be in check
        List<Integer> unSafe = new ArrayList<>();
        for (Integer pos : this.possibleMoves){
            if(!this.isSafe(board, pos)){
                unSafe.add(pos);
            }
        }
        this.possibleMoves.removeAll(unSafe);

        // listing out the enemyPieces
        List<ChessPiece> enemyPiecesThreathToKing = new ArrayList<>();
        for(ChessPiece piece : board.getChessPieces().values()){
            if((this.isEnemy(piece)) && ((piece.pieceStatusFlags & KING_ATTACK_MODE) == KING_ATTACK_MODE)){
                enemyPiecesThreathToKing.add(piece);
            }
        }
        if(enemyPiecesThreathToKing.size() > 1){
            for(ChessPiece piece : board.getChessPieces().values()){
                if(!this.isEnemy(piece)){
                    if(!piece.isKing()){
                        piece.possibleMoves.clear();
                    }
                }
            }
            if(this.possibleMoves.size() == 0){
                this.status |= CHECK_MATE;
            }
            return null;
        }

        // checking if the moving of the piece in the board causes the king to be in check
        Position tempPos, myBlockingPiece = null;
        boolean isFlagTrue = false;
        for (int direction = 0; direction < 8; direction++){
            tempPos = this.pos.getPositionToDirection(MOMENT_DIRECTIONS[direction]);
            while(tempPos.isValid()){
                if(board.getChessPieces().containsKey(tempPos.getPosition())){
                    ChessPiece piece = board.getChessPieces().get(tempPos.getPosition());
                    if(this.isEnemy(piece)){
                        if(myBlockingPiece != null){
                            isFlagTrue = true;
                            if((direction == 0) || (direction == 2)){
                                piece.pieceStatusFlags |= COLUMN_LOCKED;
                            }else if((direction == 1) || (direction == 3)){
                                piece.pieceStatusFlags |= ROW_LOCKED;
                            }else{
                                piece.pieceStatusFlags |= DIAGONAL_LOCKED;
                                if((direction == 4) || (direction == 7)){
                                    piece.pieceStatusFlags |= NE_BOUND_LOCK;
                                }
                            }
                        }
                    }else{
                        piece.pieceStatusFlags |= BLOCKING_ENEMY_ATTACK;
                        myBlockingPiece = new Position(tempPos.getPosition());
                    }
                    if((!isFlagTrue) && (myBlockingPiece != null)){
                        board.getChessPieces().get(myBlockingPiece.getPosition()).pieceStatusFlags |= NO_FLAGS;
                    }
                }else{
                    this.possibleMoves.add(tempPos.getPosition());
                }
                tempPos = tempPos.getPositionToDirection(MOMENT_DIRECTIONS[direction]);
            }
        }
        // if in check checking if it possible to take out the piece
        // checking if the king is in check and if it can be blocked by the pieces
        // both has to be solved together

        List<ChessPiece> flaggedPieces = new ArrayList<>();
        if((this.status & IN_CHECK) != IN_CHECK){
            return null;
        }
        for(ChessPiece piece : board.getChessPieces().values()){
            if(!this.isEnemy(piece)){
                for(ChessPiece enemyPiece : enemyPiecesThreathToKing){
                    if(piece.possibleMoves.contains(enemyPiece.pos.getPosition())){
                        piece.pieceStatusFlags |= ATTACK_MODE;
                        flaggedPieces.add(piece);
                    }
                    for(Integer enemyMoves : enemyPiece.possibleMoves){
                        if(piece.possibleMoves.contains(enemyMoves)){
                            piece.pieceStatusFlags |= BLOCK_MODE;
                            flaggedPieces.add(piece);
                            break;
                        }
                    }
                }
            }
        }
        HashMap<String, List<ChessPiece>> pieces = new HashMap<>();
        pieces.put("enemyPieces", enemyPiecesThreathToKing);
        pieces.put("piecesToModify", flaggedPieces);
        return pieces;
    }

    public boolean isSafe(ChessBoard board, int pos){
        boolean safe = true;
        for(ChessPiece piece : board.getChessPieces().values()){
            if(this.isEnemy(piece)){
                if(piece.possibleMoves.contains(pos)){
                    safe = false;
                }
            }
        }
        return safe;
    }

    public void setInCheck(){
        this.status |= IN_CHECK;
    }

    public boolean isInCheck(){
        return (this.status & IN_CHECK) == IN_CHECK;
    }

    public boolean isCheckMate(){
        return this.isInCheck() && ((this.status & CHECK_MATE) == CHECK_MATE);
    }

    public boolean isMoved(){
        return this.moved;
    }

    public void setMoved(boolean moved){
        this.moved = moved;
    }
}
