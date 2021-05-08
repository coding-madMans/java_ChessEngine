package chessEngine;

public class Player {
    private String name;
    private int color;

    public Player(String name, int color){
        this.name = name;
        this.color = color;
    }

    public String getPlayerName(){
        return this.name;
    }

    public char getPlayerColor(){
        char playerColor;
        if((this.color & ChessPiece.COLOR_WHITE) == ChessPiece.COLOR_WHITE){
            playerColor = 'W';
        }else if((this.color & ChessPiece.COLOR_BLACK) == ChessPiece.COLOR_BLACK){
            playerColor = 'B';
        }else{
            playerColor = 'E';
        }
        return playerColor;
    }

    public String repr(){
        String info = "";
        info += "{" + "Player name : " + this.name + ", color : " + this.getPlayerColor() + "}";
        return info;
    }
}
