package chessEngine;

import java.util.HashMap;
import java.util.Iterator;

public class ChessBoard {

    private HashMap<Integer, ChessPiece> chessPieces;
    private int kingPos[];
    private Player player1, player2;
    private int state;

    public ChessBoard(){
        this.chessPieces = new HashMap<>();
        this.player1 = new Player("player 1", ChessPiece.COLOR_WHITE);
        this.player2 = new Player("Player 2", ChessPiece.COLOR_BLACK);
        this.kingPos = new int[2];

        // placing the chess pieces
        // placing the black pieces
        chessPieces.put(1, new Rook(ChessPiece.COLOR_BLACK, new Position(1)));
        chessPieces.put(2, new Knight(ChessPiece.COLOR_BLACK, new Position(2)));
        chessPieces.put(3, new Bishop(ChessPiece.COLOR_BLACK, new Position(3)));
        chessPieces.put(4, new King(ChessPiece.COLOR_BLACK, new Position(4)));    this.kingPos[1] = 4;
        chessPieces.put(5, new Queen(ChessPiece.COLOR_BLACK, new Position(5)));
        chessPieces.put(6, new Bishop(ChessPiece.COLOR_BLACK, new Position(6)));
        chessPieces.put(7, new Knight(ChessPiece.COLOR_BLACK, new Position(7)));
        chessPieces.put(8, new Rook(ChessPiece.COLOR_BLACK, new Position(8)));

        chessPieces.put(9, new Pawn(ChessPiece.COLOR_BLACK, new Position(9)));
        chessPieces.put(10, new Pawn(ChessPiece.COLOR_BLACK, new Position(10)));
        chessPieces.put(11, new Pawn(ChessPiece.COLOR_BLACK, new Position(11)));
        chessPieces.put(12, new Pawn(ChessPiece.COLOR_BLACK, new Position(12)));
        chessPieces.put(13, new Pawn(ChessPiece.COLOR_BLACK, new Position(13)));
        chessPieces.put(14, new Pawn(ChessPiece.COLOR_BLACK, new Position(14)));
        chessPieces.put(15, new Pawn(ChessPiece.COLOR_BLACK, new Position(15)));
        chessPieces.put(16, new Pawn(ChessPiece.COLOR_BLACK, new Position(16)));

        // placing the white pieces
        chessPieces.put(57, new Rook(ChessPiece.COLOR_WHITE, new Position(57)));
        chessPieces.put(58, new Knight(ChessPiece.COLOR_WHITE, new Position(58)));
        chessPieces.put(59, new Bishop(ChessPiece.COLOR_WHITE, new Position(59)));
        chessPieces.put(60, new King(ChessPiece.COLOR_WHITE, new Position(60)));    this.kingPos[0] = 60;
        chessPieces.put(61, new Queen(ChessPiece.COLOR_WHITE, new Position(61)));
        chessPieces.put(62, new Bishop(ChessPiece.COLOR_WHITE, new Position(62)));
        chessPieces.put(63, new Knight(ChessPiece.COLOR_WHITE, new Position(63)));
        chessPieces.put(64, new Rook(ChessPiece.COLOR_WHITE, new Position(64)));

        chessPieces.put(49, new Pawn(ChessPiece.COLOR_WHITE, new Position(49)));
        chessPieces.put(50, new Pawn(ChessPiece.COLOR_WHITE, new Position(50)));
        chessPieces.put(51, new Pawn(ChessPiece.COLOR_WHITE, new Position(51)));
        chessPieces.put(52, new Pawn(ChessPiece.COLOR_WHITE, new Position(52)));
        chessPieces.put(53, new Pawn(ChessPiece.COLOR_WHITE, new Position(53)));
        chessPieces.put(54, new Pawn(ChessPiece.COLOR_WHITE, new Position(54)));
        chessPieces.put(55, new Pawn(ChessPiece.COLOR_WHITE, new Position(55)));
        chessPieces.put(56, new Pawn(ChessPiece.COLOR_WHITE, new Position(56)));
    }

    public HashMap<Integer, ChessPiece> getChessPieces(){
        return this.chessPieces;
    }
    public Player getWhitePlayer(){
        return this.player1;
    }
    public Player getBlackPlayer(){
        return this.player2;
    }

    public void update(){
        for (ChessPiece chessPiece : this.chessPieces.values()) {
            chessPiece.updatePossibleMoves(this);
        }
        ChessPiece.filterIlLegalMoves(this);
    }

    public int getKingPos(int index){
        return this.kingPos[index];
    }

    public String repr(){
        StringBuilder info = new StringBuilder();
        info.append("{\n player 1 : ").append(this.player1.repr()).append(", \n player 2 : ").append(this.player2.repr());
        info.append("\n total number of pieces : ").append(this.chessPieces.size());
        for (ChessPiece chessPiece : this.chessPieces.values()) {
            info.append("\n ").append(chessPiece.repr());
        }
        info.append("}");
        return info.toString();
    }
}
