package chessEngine;

public class King extends  ChessPiece{

    protected static final int IN_ATTACK = 1;

    boolean moved;
    private int status;

    public King(int pieceColor, Position pos) {
        super(ChessPiece.KING | pieceColor, pos);

        this.moved = false;
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

    public void postVerifyTheMoves(){}

    public void setInAttack(){
        this.status = this.status | IN_ATTACK;
    }
}
