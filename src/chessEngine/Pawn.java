package chessEngine;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    private boolean moved;

    public Pawn(int id, char pieceColor, Position piecePosition) {
        super(id, "Pawn", pieceColor, piecePosition);
        this.moved = false;
    }

    @Override
    public List<Position> getPossibleMoves(ChessBoard board){
        List<Position> possibleMoves = new ArrayList<Position>();
        List<ChessPiece> pices = board.getChessPices();

        if(this.getPieceColor() == 'W'){
            possibleMoves.add(this.getPiecePosition().getPositionReletive(-1, 0));
            possibleMoves.add(this.getPiecePosition().getPositionReletive(-1, -1));
            possibleMoves.add(this.getPiecePosition().getPositionReletive(-1, 1));
        }else{
            possibleMoves.add(this.getPiecePosition().getPositionReletive(1, 0));
            possibleMoves.add(this.getPiecePosition().getPositionReletive(1, -1));
            possibleMoves.add(this.getPiecePosition().getPositionReletive(1, 1));
        }

        if(!this.isMoved()){
            if(this.getPieceColor() == 'W'){
                possibleMoves.add(this.getPiecePosition().getPositionReletive(-2, 0));
            }else{
                possibleMoves.add(this.getPiecePosition().getPositionReletive(2, 0));
            }
        }

        ChessPiece adjacentPiece[] = this.getAdjecentPieces(board);
        for (int i = 0; i < adjacentPiece.length; i++){
            if(adjacentPiece[i] != null){
                //
            }
        }
        return possibleMoves;
    }

    public boolean isMoved(){
        return this.moved;
    }
}
