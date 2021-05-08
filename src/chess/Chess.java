package chess;

import java.util.List;

import chessEngine.*;

public class Chess {
    public static void main(String args[]){
        Engine engine = new Engine();
        engine.display();
        engine.run();
        while (engine.isActive()){
            engine.update();
        }
    }
}
