package chessEngine;

public class Pawn extends ChessPiece{

    boolean moved;

    public Pawn( int pieceColor, Position pos) {
        super(ChessPiece.PAWN | pieceColor, pos);

        this.moved = false;
    }

    public void updatePossibleMoves(ChessBoard board){
        super.updatePossibleMoves(board);
        int directionIndex;
        if((this.pieceId & COLOR_BLACK) == COLOR_BLACK){
            directionIndex = 2;
        }else{
            directionIndex = 0;
        }
        Position tempPos = this.pos.getPositionToDirection(MOMENT_DIRECTIONS[directionIndex]);
        if(tempPos.isValid()){
            if(!board.getChessPieces().containsKey(tempPos.getPosition())){
                this.possibleMoves.add(tempPos.getPosition());
            }
        }
        for(int i = 0; i < 2; i++){
            tempPos = this.pos.getPositionToDirection(MOMENT_DIRECTIONS[directionIndex + 4 + i]);
            if(tempPos.isValid()){
                if(board.getChessPieces().containsKey(tempPos.getPosition())){
                    if(this.isEnemy(board.getChessPieces().get(tempPos.getPosition()))){
                        if(board.getChessPieces().get(tempPos.getPosition()).isKing()){
                            this.attackReport((King) board.getChessPieces().get(tempPos.getPosition()));
                        }
                        this.possibleMoves.add(tempPos.getPosition());
                    }
                }
            }
        }
        if(!this.moved){
            tempPos = tempPos.getPositionToDirection(MOMENT_DIRECTIONS[directionIndex]);
            if(tempPos.isValid()){
                if(!board.getChessPieces().containsKey(tempPos.getPosition())){
                    this.possibleMoves.add(tempPos.getPosition());
                }
            }
        }
    }
}
