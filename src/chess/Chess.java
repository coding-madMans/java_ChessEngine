package chess;

import chessEngine.*;

public class Chess {
    public static void main(String args[]){
        Engine engine = new Engine();
        engine.run();
        engine.display();
        // long frameStartTime, frameEndTime, frameTime, averageFrameTime = 0;
        while(engine.isActive()){
            engine.update();
        }
    }
}
