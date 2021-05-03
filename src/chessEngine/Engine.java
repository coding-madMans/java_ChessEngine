package chessEngine;

import javax.swing.JFrame;

public class Engine extends JFrame implements Runnable{
    private Player whitePlayer, blackPlayer;
    private ChessBoard chessBoard;

    public Engine(){
        super("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 650);
        this.chessBoard = new ChessBoard();
        this.whitePlayer = new Player("player 1", 'W');
        this.blackPlayer = new Player("player 2", 'B');
    }

    public void display(){
        this.chessBoard.display();
        System.out.println("players : " + this.whitePlayer.repr() + ", " + this.blackPlayer.repr());
    }

    public void update(){}

    @Override
    public void run() {
        this.setVisible(true);
    }
}
