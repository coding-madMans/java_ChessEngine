package chessEngine;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import java.awt.Point;
import java.awt.Color;

public class Engine extends JFrame implements Runnable{
    private List<ChessPiece> chessPieces;
    private ChessBoard chessBoard;

    private Point mousePos;

    public Engine(){
        super("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 650);
        this.chessPieces = new ArrayList<ChessPiece>();
        this.chessBoard = new ChessBoard();
    }

    public String repr(){
        String info = "";
        info += "{" + "total # of pieces : " + this.chessPieces.size();
        info += "}";
        return info;
    }

    public void display(){
        this.chessBoard.display();
    }

    public void update(){
        /*
        this.mousePos = this.getMousePosition();
        Color background;
        if(this.mousePos != null){
            background = new Color(200, 0, 200, 255);
        }else{
            background = new Color(0, 0, 0, 255);
        }
        System.out.println(background);
        this.setBackground(background);
        */
    }

    @Override
    public void run() {
        this.setVisible(true);
    }
}
