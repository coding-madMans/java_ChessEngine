package chess;

import chessEngine.Engine;

import javax.swing.JFrame;

public class Display extends JFrame implements Runnable {

    private final Engine engine;

    public Display(String title, Engine engine){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 800, 650);

        this.engine = engine;
    }

    @Override
    public void run() {
        this.setVisible(true);
    }

    public void update(){
        this.engine.update();
    }
}
