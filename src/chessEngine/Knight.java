package chessEngine;

public class Knight extends ChessPiece{

    public Knight(int pieceColor, Position pos) {
        super(ChessPiece.KNIGHT | pieceColor, pos);
    }

    public void updatePossibleMoves(ChessBoard board){
        super.updatePossibleMoves(board);
        Position tempPos1, tempPos2;
        for (int i = 0; i <= 2; i+=2){
            tempPos1 = this.pos.getPositionToDirection(MOMENT_DIRECTIONS[i]);
            for(int j = 0; j < 2; j++){
                tempPos2 = tempPos1.getPositionToDirection(MOMENT_DIRECTIONS[i + 4 + j]);
                if(tempPos2.isValid()){
                    if(board.getChessPieces().containsKey(tempPos2.getPosition())){
                        if(this.isEnemy(board.getChessPieces().get(tempPos2.getPosition()))){
                            if(board.getChessPieces().get(tempPos2.getPosition()).isKing()){
                                this.attackReport((King) board.getChessPieces().get(tempPos2.getPosition()));
                            }
                            this.possibleMoves.add(tempPos2.getPosition());
                        }
                    }else{
                        this.possibleMoves.add(tempPos2.getPosition());
                    }
                }
            }
        }
        for (int i = 1; i <= 3; i+=2){
            tempPos1 = this.pos.getPositionToDirection(MOMENT_DIRECTIONS[i]);
            for (int j = 0; j <= 2; j+=2){
                if(i == 1){
                    tempPos2 = tempPos1.getPositionToDirection(MOMENT_DIRECTIONS[i + 3 + j]);
                }else{
                    tempPos2 = tempPos1.getPositionToDirection(MOMENT_DIRECTIONS[i + 2 + j]);
                }
                if(tempPos2.isValid()){
                    if(board.getChessPieces().containsKey(tempPos2.getPosition())){
                        if(this.isEnemy(board.getChessPieces().get(tempPos2.getPosition()))){
                            if(board.getChessPieces().get(tempPos2.getPosition()).isKing()){
                                this.attackReport((King) board.getChessPieces().get(tempPos2.getPosition()));
                            }
                            this.possibleMoves.add(tempPos2.getPosition());
                        }
                    }else{
                        this.possibleMoves.add(tempPos2.getPosition());
                    }
                }
            }
        }
    }
}
