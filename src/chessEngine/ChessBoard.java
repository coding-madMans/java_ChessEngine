package chessEngine;

import java.util.HashMap;
import java.util.Iterator;

public class ChessBoard {

    private static final int WHITE_TO_PLAY = 0;
    private static final int BLACK_TO_PLAY = 1;

    private HashMap<Integer, ChessPiece> chessPieces;
    private int kingPos[];
    private final Player player1, player2;
    private int state;

    public ChessBoard(){
        this.chessPieces = new HashMap<>();
        this.player1 = new Player("player 1", ChessPiece.COLOR_WHITE);
        this.player2 = new Player("Player 2", ChessPiece.COLOR_BLACK);
        this.kingPos = new int[2];

        // placing the chess pieces
        // placing the black pieces
        chessPieces.put(1, new Rook(ChessPiece.COLOR_BLACK, new Position(1), false));
        chessPieces.put(2, new Knight(ChessPiece.COLOR_BLACK, new Position(2)));
        chessPieces.put(3, new Bishop(ChessPiece.COLOR_BLACK, new Position(3)));
        chessPieces.put(4, new King(ChessPiece.COLOR_BLACK, new Position(4), false));    this.kingPos[1] = 4;
        chessPieces.put(5, new Queen(ChessPiece.COLOR_BLACK, new Position(5)));
        chessPieces.put(6, new Bishop(ChessPiece.COLOR_BLACK, new Position(6)));
        chessPieces.put(7, new Knight(ChessPiece.COLOR_BLACK, new Position(7)));
        chessPieces.put(8, new Rook(ChessPiece.COLOR_BLACK, new Position(8), false));

        chessPieces.put(9, new Pawn(ChessPiece.COLOR_BLACK, new Position(9), false));
        chessPieces.put(10, new Pawn(ChessPiece.COLOR_BLACK, new Position(10), false));
        chessPieces.put(11, new Pawn(ChessPiece.COLOR_BLACK, new Position(11), false));
        chessPieces.put(12, new Pawn(ChessPiece.COLOR_BLACK, new Position(12), false));
        chessPieces.put(13, new Pawn(ChessPiece.COLOR_BLACK, new Position(13), false));
        chessPieces.put(14, new Pawn(ChessPiece.COLOR_BLACK, new Position(14), false));
        chessPieces.put(15, new Pawn(ChessPiece.COLOR_BLACK, new Position(15), false));
        chessPieces.put(16, new Pawn(ChessPiece.COLOR_BLACK, new Position(16), false));

        // placing the white pieces
        chessPieces.put(57, new Rook(ChessPiece.COLOR_WHITE, new Position(57), false));
        chessPieces.put(58, new Knight(ChessPiece.COLOR_WHITE, new Position(58)));
        chessPieces.put(59, new Bishop(ChessPiece.COLOR_WHITE, new Position(59)));
        chessPieces.put(60, new King(ChessPiece.COLOR_WHITE, new Position(60), false));    this.kingPos[0] = 60;
        chessPieces.put(61, new Queen(ChessPiece.COLOR_WHITE, new Position(61)));
        chessPieces.put(62, new Bishop(ChessPiece.COLOR_WHITE, new Position(62)));
        chessPieces.put(63, new Knight(ChessPiece.COLOR_WHITE, new Position(63)));
        chessPieces.put(64, new Rook(ChessPiece.COLOR_WHITE, new Position(64), false));

        chessPieces.put(49, new Pawn(ChessPiece.COLOR_WHITE, new Position(49), false));
        chessPieces.put(50, new Pawn(ChessPiece.COLOR_WHITE, new Position(50), false));
        chessPieces.put(51, new Pawn(ChessPiece.COLOR_WHITE, new Position(51), false));
        chessPieces.put(52, new Pawn(ChessPiece.COLOR_WHITE, new Position(52), false));
        chessPieces.put(53, new Pawn(ChessPiece.COLOR_WHITE, new Position(53), false));
        chessPieces.put(54, new Pawn(ChessPiece.COLOR_WHITE, new Position(54), false));
        chessPieces.put(55, new Pawn(ChessPiece.COLOR_WHITE, new Position(55), false));
        chessPieces.put(56, new Pawn(ChessPiece.COLOR_WHITE, new Position(56), false));

        this.state |= WHITE_TO_PLAY;
    }

    public ChessBoard(Player player1, Player player2, String fen) throws Exception {
        this.player1 = player1;
        this.player2 = player2;
        this.readFen(fen);
    }

    private void readFen(String fen) throws Exception {
        String[] fenBlocks = fen.split(" ");
        if(fenBlocks.length < 1){
            throw new Exception("fen is in correct");
        }
        int pos = 1;
        for(char fenChar : fenBlocks[0].toCharArray()){
            switch (fenChar) {
                case '/' -> {
                    continue;
                }
                case 'p' -> this.chessPieces.put(pos, new Pawn(ChessPiece.COLOR_BLACK, new Position(pos), true));
                case 'r' -> this.chessPieces.put(pos, new Rook(ChessPiece.COLOR_BLACK, new Position(pos), true));
                case 'n' -> this.chessPieces.put(pos, new Knight(ChessPiece.COLOR_BLACK, new Position(pos)));
                case 'b' -> this.chessPieces.put(pos, new Bishop(ChessPiece.COLOR_BLACK, new Position(pos)));
                case 'q' -> this.chessPieces.put(pos, new Queen(ChessPiece.COLOR_BLACK, new Position(pos)));
                case 'k' -> {
                    this.chessPieces.put(pos, new King(ChessPiece.COLOR_BLACK, new Position(pos), true));
                    this.kingPos[1] = pos;
                }
                case 'P' -> this.chessPieces.put(pos, new Pawn(ChessPiece.COLOR_WHITE, new Position(pos), true));
                case 'R' -> this.chessPieces.put(pos, new Rook(ChessPiece.COLOR_WHITE, new Position(pos), true));
                case 'N' -> this.chessPieces.put(pos, new Knight(ChessPiece.COLOR_WHITE, new Position(pos)));
                case 'B' -> this.chessPieces.put(pos, new Bishop(ChessPiece.COLOR_WHITE, new Position(pos)));
                case 'Q' -> this.chessPieces.put(pos, new Queen(ChessPiece.COLOR_WHITE, new Position(pos)));
                case 'K' -> {
                    this.chessPieces.put(pos, new King(ChessPiece.COLOR_WHITE, new Position(pos), true));
                    this.kingPos[0] = pos;
                }
                default -> {
                    for(int i = 0; i < (int)fenChar - 1; i++){
                        pos += 1;
                    }
                }
            }
            pos += 1;
        }
        if(fenBlocks.length < 2){
            this.state |= WHITE_TO_PLAY;
            return;
        }
        if(fenBlocks[1].equals("w")){
            this.state |= WHITE_TO_PLAY;
        }else{
            this.state |= BLACK_TO_PLAY;
        }
        if(fenBlocks.length < 3){
            return;
        }
        Position kingPos, tempPos;
        Rook rook;
        King king;
        for(char castleRights : fenBlocks[2].toCharArray()){
            switch (castleRights){
                case 'Q' -> {
                    kingPos = new Position(this.getWhiteKingPos());
                    tempPos = new Position(kingPos.getPosition());
                    for(int i = 0; i < 4; i++){
                        tempPos = tempPos.getPositionToDirection(ChessPiece.MOMENT_DIRECTIONS[3]);
                    }
                    if(this.chessPieces.containsKey(tempPos.getPosition())) {
                        if(this.chessPieces.get(tempPos.getPosition()).getPieceType() == ChessPiece.ROOK) {
                            rook = (Rook) this.chessPieces.get(tempPos.getPosition());
                            rook.setMoved(false);
                            king = (King) this.chessPieces.get(kingPos.getPosition());
                            king.setMoved(false);
                        } else {
                            throw new Exception("error");
                        }
                    }
                }
                case 'K' -> {
                    kingPos = new Position(this.getWhiteKingPos());
                    tempPos = new Position(kingPos.getPosition());
                    for(int i = 0; i < 3; i++){
                        tempPos = tempPos.getPositionToDirection(ChessPiece.MOMENT_DIRECTIONS[2]);
                    }
                    if(this.chessPieces.containsKey(tempPos.getPosition())) {
                        if(this.chessPieces.get(tempPos.getPosition()).getPieceType() == ChessPiece.ROOK) {
                            rook = (Rook) this.chessPieces.get(tempPos.getPosition());
                            rook.setMoved(false);
                            king = (King) this.chessPieces.get(kingPos.getPosition());
                            king.setMoved(false);
                        } else {
                            throw new Exception("error");
                        }
                    }
                }
                case 'q' -> {
                    kingPos = new Position(this.getBlackKingPos());
                    tempPos = new Position(kingPos.getPosition());
                    for(int i = 0; i < 4; i++){
                        tempPos = tempPos.getPositionToDirection(ChessPiece.MOMENT_DIRECTIONS[3]);
                    }
                    if(this.chessPieces.containsKey(tempPos.getPosition())){
                        if(this.chessPieces.get(tempPos.getPosition()).getPieceType() == ChessPiece.ROOK){
                            rook = (Rook) this.chessPieces.get(tempPos.getPosition());
                            rook.setMoved(false);
                            king = (King) this.chessPieces.get(kingPos.getPosition());
                            king.setMoved(false);
                        }else{
                            throw new Exception("error");
                        }
                    }
                }
                case 'k' -> {
                    kingPos = new Position(this.getBlackKingPos());
                    tempPos = new Position(kingPos.getPosition());
                    for(int i = 0; i < 3; i++){
                        tempPos = tempPos.getPositionToDirection(ChessPiece.MOMENT_DIRECTIONS[2]);
                    }
                    if(this.chessPieces.containsKey(tempPos.getPosition())){
                        if(this.chessPieces.get(tempPos.getPosition()).getPieceType() == ChessPiece.ROOK){
                            rook = (Rook) this.chessPieces.get(tempPos.getPosition());
                            rook.setMoved(false);
                            king = (King) this.chessPieces.get(kingPos.getPosition());
                            king.setMoved(false);
                        }else{
                            throw new Exception("error");
                        }
                    }
                }
            }
        }
    }

    public String convertToFen(){
        StringBuilder fen = new StringBuilder();
        for(int cell = 1; cell <= 64; cell++){
            if(this.chessPieces.containsKey(cell)){
                ChessPiece piece = this.chessPieces.get(cell);
                int color = piece.getPieceColor();
                int pieceType = piece.getPieceType();
                switch (pieceType){
                    case ChessPiece.PAWN -> {
                        if(color == ChessPiece.COLOR_WHITE){
                            fen.append('P');
                        }else{
                            fen.append('p');
                        }
                    }
                    case ChessPiece.ROOK -> {
                        if(color == ChessPiece.COLOR_WHITE){
                            fen.append('R');
                        }else{
                            fen.append('r');
                        }
                    }
                    case ChessPiece.BISHOP -> {
                        if(color == ChessPiece.COLOR_WHITE){
                            fen.append('B');
                        }else{
                            fen.append('b');
                        }
                    }
                    case ChessPiece.KNIGHT -> {
                        if(color == ChessPiece.COLOR_WHITE){
                            fen.append('N');
                        }else{
                            fen.append('n');
                        }
                    }
                    case ChessPiece.QUEEN -> {
                        if(color == ChessPiece.COLOR_WHITE){
                            fen.append('Q');
                        }else{
                            fen.append('q');
                        }
                    }
                    case ChessPiece.KING -> {
                        if(color == ChessPiece.COLOR_WHITE){
                            fen.append('K');
                        }else{
                            fen.append('k');
                        }
                    }
                }
            }
        }
        fen.append(' ');
        if((this.state & WHITE_TO_PLAY) == WHITE_TO_PLAY){
            fen.append('w');
        }else{
            fen.append('b');
        }
        fen.append(' ');
        King whiteKing = (King) this.chessPieces.get(this.getWhiteKingPos());
        if(!whiteKing.isMoved()){
            if(isCastallingAvaliable(whiteKing, 0)){
                fen.append('Q');
            }
            if(isCastallingAvaliable(whiteKing, 1)){
                fen.append('K');
            }
        }
        King blackKing = (King) this.chessPieces.get(this.getBlackKingPos());
        if(!blackKing.isMoved()){
            if(isCastallingAvaliable(blackKing, 0)){
                fen.append('q');
            }
            if(isCastallingAvaliable(blackKing, 1)){
                fen.append('k');
            }
        }
        return fen.toString();
    }

    // if side == 0 => queen side, side == 1 => king side
    private boolean isCastallingAvaliable(King king, int side){
        boolean available = false;
        // check queen side
        Position tempPos = king.getPosition();
        if(side == 0) {
            for (int i = 0; i < 4; i++) {
                tempPos = tempPos.getPositionToDirection(ChessPiece.MOMENT_DIRECTIONS[3]);
            }
        }else{
            for (int i = 0; i < 3; i++) {
                tempPos = tempPos.getPositionToDirection(ChessPiece.MOMENT_DIRECTIONS[2]);
            }
        }
        if (this.chessPieces.containsKey(tempPos.getPosition())) {
            if (this.chessPieces.get(tempPos.getPosition()).getPieceType() == ChessPiece.ROOK) {
                Rook rook = (Rook) this.chessPieces.get(tempPos.getPosition());
                if (!rook.isMoved()) {
                    available = true;
                }
            }
        }
        return available;
    }

    public HashMap<Integer, ChessPiece> getChessPieces(){
        return this.chessPieces;
    }

    public Player getWhitePlayer(){
        return this.player1;
    }

    public Player getBlackPlayer(){
        return this.player2;
    }

    public Player getCurrentPlayer(){
        if((this.state & WHITE_TO_PLAY) == WHITE_TO_PLAY){
            return this.getWhitePlayer();
        }else{
            return this.getBlackPlayer();
        }
    }

    public void updateCurrentPlayer(){
        if ((this.state & WHITE_TO_PLAY) == WHITE_TO_PLAY) {
            this.state |= BLACK_TO_PLAY;
        } else {
            this.state |= WHITE_TO_PLAY;
        }
    }

    public void update(){
        for (ChessPiece chessPiece : this.chessPieces.values()) {
            chessPiece.updatePossibleMoves(this);
        }
        ChessPiece.filterIlLegalMoves(this);
        this.updateCurrentPlayer();
    }

    public int getKingPos(int index){
        return this.kingPos[index];
    }

    public int getWhiteKingPos(){
        return this.kingPos[0];
    }

    public int getBlackKingPos(){
        return this.kingPos[1];
    }

    public String repr(){
        StringBuilder info = new StringBuilder();
        info.append("{\n player 1 : ").append(this.player1.repr()).append(", \n player 2 : ").append(this.player2.repr());
        info.append("\n total number of pieces : ").append(this.chessPieces.size());
        for (ChessPiece chessPiece : this.chessPieces.values()) {
            info.append("\n ").append(chessPiece.repr());
        }
        info.append("}");
        return info.toString();
    }
}
