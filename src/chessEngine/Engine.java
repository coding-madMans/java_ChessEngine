package chessEngine;

public class Engine{

    private final ChessBoard board;

    public Engine(String fen) throws Exception {
        Player player1 = new Player("player1", ChessPiece.COLOR_WHITE);
        Player player2 = new Player("player2", ChessPiece.COLOR_BLACK);
        this.board = new ChessBoard(player1, player2, fen);
    }

    public void display(){
        System.out.println(board.repr());
    }
    public void update(){
        board.update();
    }

    public ChessBoard getBoard(){
        return this.board;
    }
}
