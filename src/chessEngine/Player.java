package chessEngine;

public class Player {
    private String name;
    private char color;

    public Player(String name, char color){
        this.name = name;
        this.color = color;
    }

    public String getPlayerName(){
        return this.name;
    }

    public char getPlayerColor(){
        return this.color;
    }

    public String repr(){
        String info = "";
        info += "{" + "Player name : " + this.name + ", color : " + this.color + "}";
        return info;
    }
}
