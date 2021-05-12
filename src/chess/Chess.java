package chess;

import chessEngine.*;

public class Chess {
    public static void main(String[] args) throws Exception {
        // default fen "rnbkqbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBKQBNR/ w"
        Engine engine = new Engine("r2qk2r/pb4pp/1n2Pb2/2B2Q2/p1p5/2P5/2B2PPP/RN2R1K1 w");
        Display window = new Display("Chess", engine);
        engine.display();
        window.run();
        System.out.println(engine.getBoard().convertToFen());
        long startTime, endTime, finalTime;
        while (window.isActive()){
            startTime = System.nanoTime();
            window.update();
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            System.out.println("The total time (ms) : " + (finalTime / 1000000));
            Thread.sleep(1000);
        }
    }
}
