package chess;

import chessEngine.*;

public class Chess {
    public static void main(String[] args){
        Engine engine = new Engine();
        engine.display();
        engine.run();
        long startTime, endTime, finalTime;
        while (engine.isActive()){
            startTime = System.nanoTime();
            engine.update();
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            System.out.println("The total time (ms) : " + (finalTime / 1000000));
        }
    }
}
