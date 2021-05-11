package chessEngine;

public class Player {
    private final String name;
    private final int color;

    public Player(String name, int color){
        this.name = name;
        this.color = color;
    }

    public String getPlayerName(){
        return this.name;
    }

    public int getPlayerColor(){
        int playerColor;
        if((this.color & ChessPiece.COLOR_WHITE) == ChessPiece.COLOR_WHITE){
            playerColor = ChessPiece.COLOR_WHITE;
        }else if((this.color & ChessPiece.COLOR_BLACK) == ChessPiece.COLOR_BLACK){
            playerColor = ChessPiece.COLOR_BLACK;
        }else{
            playerColor = ChessPiece.ERROR;
        }
        return playerColor;
    }

    public String repr(){
        String info = "";
        info += "{" + "Player name : " + this.name + ", color : " + this.getPlayerColor() + "}";
        return info;
    }
}
