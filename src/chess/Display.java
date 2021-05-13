package chess;

import chessEngine.ChessPiece;
import chessEngine.Engine;
import org.jetbrains.annotations.Contract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Display extends JFrame implements Runnable {

    private final Engine engine;

    private JPanel panel;
    private JLabel[] labels;

    public Display(String title, Engine engine) throws Exception {
        super(title);
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100 , 30, 0));
        panel.setLayout(new GridLayout(8, 8));
        labels = new JLabel[64];
        int temp = 0, count = 0;
        for(int i = 0; i < labels.length; i++){
            labels[i] = new JLabel();
            labels[i].setOpaque(true);
            if(temp == 0){
                labels[i].setBackground(Color.WHITE);
                temp = 1;
            }else{
                labels[i].setBackground(Color.BLACK);
                temp = 0;
            }
            count += 1;
            if(count == 8){
                if(temp == 0){
                    temp = 1;
                }else{
                    temp = 0;
                }
                count = 0;
            }
            labels[i].setSize(panel.getWidth() / 8, panel.getHeight() / 8);
            panel.add(labels[i]);
        }
        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 800, 650);
        ImageIcon icon = new ImageIcon("res/chessIcon.png");
        this.setIconImage(icon.getImage());

        this.engine = engine;

        for(ChessPiece piece : engine.getBoard().getChessPieces().values()){
            ImageIcon pieceImage;
            JLabel label = labels[piece.getPosition().getPosition() - 1];
            switch (piece.getPieceType()){
                case ChessPiece.PAWN -> {
                    if(piece.getPieceColor() == ChessPiece.COLOR_WHITE){
                        pieceImage = new ImageIcon("res/whitePawn.png");
                    }else{
                        pieceImage = new ImageIcon("res/blackPawn.png");
                    }
                }
                case ChessPiece.ROOK -> {
                    if(piece.getPieceColor() == ChessPiece.COLOR_WHITE){
                        pieceImage = new ImageIcon("res/whiteRook.png");
                    }else{
                        pieceImage = new ImageIcon("res/blackRook.png");
                    }
                }
                case ChessPiece.KNIGHT -> {
                    if(piece.getPieceColor() == ChessPiece.COLOR_WHITE){
                        pieceImage = new ImageIcon("res/whiteKnight.png");
                    }else{
                        pieceImage = new ImageIcon("res/blackKnight.png");
                    }
                }
                case ChessPiece.BISHOP -> {
                    if(piece.getPieceColor() == ChessPiece.COLOR_WHITE){
                        pieceImage = new ImageIcon("res/whiteBishop.png");
                    }else{
                        pieceImage = new ImageIcon("res/blackBishop.png");
                    }
                }
                case ChessPiece.QUEEN -> {
                    if(piece.getPieceColor() == ChessPiece.COLOR_WHITE){
                        pieceImage = new ImageIcon("res/whiteQueen.png");
                    }else{
                        pieceImage = new ImageIcon("res/blackQueen.png");
                    }
                }
                case ChessPiece.KING -> {
                    if(piece.getPieceColor() == ChessPiece.COLOR_WHITE){
                        pieceImage = new ImageIcon("res/whiteKing.png");
                    }else{
                        pieceImage = new ImageIcon("res/blackKing.png");
                    }
                }
                default -> throw new Exception("could not the piece...");
            }
            pieceImage = new ImageIcon(pieceImage.getImage().getScaledInstance(pieceImage.getIconWidth() * 3, pieceImage.getIconHeight() * 3, Image.SCALE_SMOOTH));
            label.setIcon(pieceImage);
        }
    }

    @Override
    public void run() {
        this.setVisible(true);
    }

    public void update(){
        this.engine.update();
        PointerInfo mouse = MouseInfo.getPointerInfo();
        System.out.println("Mouse : " + mouse.getLocation().x + ", " + mouse.getLocation().y);
    }

    private void mouseRead(Point mouse){}
}
