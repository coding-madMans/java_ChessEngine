package chessEngine;

public class ChessBoard {
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
    }
}
