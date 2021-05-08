package chessEngine;

import javax.swing.JFrame;

public class Engine extends JFrame implements Runnable{

    private ChessBoard board;

    public Engine(){
        super("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 650);

        this.board = new ChessBoard();
    }

    public void display(){
        System.out.println(board.repr());
    }
    public void update(){}

    @Override
    public void run() {
        this.setVisible(true);
    }
}
