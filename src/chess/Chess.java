package chess;

import chessEngine.*;

public class Chess {
    public static void main(String[] args) throws Exception {
        // default fen "rnbkqbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBKQBNR/ w"
        // "r2qk2r/pb4pp/1n2Pb2/2B2Q2/p1p5/2P5/2B2PPP/RN2R1K1 w"
        Display window = new Display("Chess", new Engine("rnbkqbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBKQBNR/ w"));
        window.run();
        while (window.isActive()){
            window.update();
            Thread.sleep(100);
        }
    }
}
