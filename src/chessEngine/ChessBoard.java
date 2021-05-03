package chessEngine;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private List<ChessPiece> chessPieces;
    private char[][] board;

    public ChessBoard(){
        this.board = new char[8][8];
        int row, col, i = 0;
        for (row = 0; row < 8; row++){
            for (col = 0; col < 8; col++){
                if(i == 0){
                    this.board[row][col] = 'W';
                    i = 1;
                }else{
                    this.board[row][col] = 'B';
                    i = 0;
                }
            }
            i = ((i + 1) % 2);
        }
        this.chessPieces = new ArrayList<ChessPiece>();

        // placing the chess pieces on the board
        // white pieces
        this.chessPieces.add(new Rook(0, 'W', new Position(0, 0)));
        this.chessPieces.add(new Knight(1, 'W', new Position(0, 1)));
        this.chessPieces.add(new Bishop(2, 'W', new Position(0, 2)));
        this.chessPieces.add(new King(3, 'W', new Position(0, 3)));
        this.chessPieces.add(new Queen(4, 'W', new Position(0, 4)));
        this.chessPieces.add(new Bishop(5, 'W', new Position(0, 5)));
        this.chessPieces.add(new Knight(6, 'W', new Position(0, 6)));
        this.chessPieces.add(new Rook(7, 'W', new Position(0, 7)));

        this.chessPieces.add(new Pawn(8, 'W', new Position(1, 0)));
        this.chessPieces.add(new Pawn(9, 'W', new Position(1, 1)));
        this.chessPieces.add(new Pawn(10, 'W', new Position(1, 2)));
        this.chessPieces.add(new Pawn(11, 'W', new Position(1, 3)));
        this.chessPieces.add(new Pawn(12, 'W', new Position(1, 4)));
        this.chessPieces.add(new Pawn(13, 'W', new Position(1, 5)));
        this.chessPieces.add(new Pawn(14, 'W', new Position(1, 6)));
        this.chessPieces.add(new Pawn(15, 'W', new Position(1, 7)));

        // black pieces
        this.chessPieces.add(new Rook(16, 'B', new Position(7, 0)));
        this.chessPieces.add(new Knight(17, 'B', new Position(7, 1)));
        this.chessPieces.add(new Bishop(18, 'B', new Position(7, 2)));
        this.chessPieces.add(new King(19, 'B', new Position(7, 3)));
        this.chessPieces.add(new Queen(20, 'B', new Position(7, 4)));
        this.chessPieces.add(new Bishop(21, 'B', new Position(7, 5)));
        this.chessPieces.add(new Knight(22, 'B', new Position(7, 6)));
        this.chessPieces.add(new Rook(23, 'B', new Position(7, 7)));

        this.chessPieces.add(new Pawn(24, 'B', new Position(6, 0)));
        this.chessPieces.add(new Pawn(25, 'B', new Position(6, 1)));
        this.chessPieces.add(new Pawn(26, 'B', new Position(6, 2)));
        this.chessPieces.add(new Pawn(27, 'B', new Position(6, 3)));
        this.chessPieces.add(new Pawn(28, 'B', new Position(6, 4)));
        this.chessPieces.add(new Pawn(28, 'B', new Position(6, 5)));
        this.chessPieces.add(new Pawn(30, 'B', new Position(6, 6)));
        this.chessPieces.add(new Pawn(31, 'B', new Position(6, 7)));
    }

    public char[][] getBoard(){
        return this.board;
    }

    public void display(){
        String col;
        for (int row = 0; row < 8; row++){
            col = new String(this.board[row]);
            System.out.println(col);
        }
        System.out.println("");
        System.out.println("The total # of pieces : " + this.chessPieces.size());
        for (int i = 0; i < this.chessPieces.size(); i++){
            System.out.println(this.chessPieces.get(i).repr());
        }
    }

    public List<ChessPiece> getChessPices(){
        return this.chessPieces;
    }
}
