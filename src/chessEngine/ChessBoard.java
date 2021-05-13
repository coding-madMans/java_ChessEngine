package chessEngine;

import java.util.HashMap;

public class ChessBoard {

    private static final int WHITE_TO_PLAY = 0;
    private static final int BLACK_TO_PLAY = 1;

    private HashMap<Integer, ChessPiece> chessPieces;
    private int[] kingPos;
    private final Player player1, player2;
    private int state;

    public ChessBoard(Player player1, Player player2, String fen) throws Exception {
        this.player1 = player1;
        this.player2 = player2;
        this.chessPieces = new HashMap<>();
        this.kingPos = new int[2];
        this.readFen(fen);
    }

    public void readFen(String fen) throws Exception {
        // System.out.println("The given fen : " + fen);
        this.chessPieces.clear();
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
                default -> pos += (fenChar - '0' - 1);
            }
            pos += 1;
        }
        for(ChessPiece piece : this.chessPieces.values()){
            if(piece.getPieceType() == ChessPiece.PAWN){
                Pawn pawn = (Pawn) piece;
                if(pawn.getPieceColor() == ChessPiece.COLOR_WHITE){
                    if((pawn.getPosition().getPosition() >= 44) && (pawn.getPosition().getPosition() <= 56)){
                        pawn.setMoved(false);
                    }
                }else {
                    if((pawn.getPosition().getPosition() >= 9) && (pawn.getPosition().getPosition() <= 16)){
                        pawn.setMoved(false);
                    }
                }
            }
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
        int count = 0, rowNumber = 1, tempRowNumber = rowNumber;
        for(int cell = 1; cell <= 64; cell++){
            if(this.chessPieces.containsKey(cell)){
                if(count > 0){
                    fen.append(count);
                }
                count = 0;
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
            }else{
                count += 1;
            }
            rowNumber = (cell / 8) + 1;
            if((rowNumber != tempRowNumber) && (rowNumber <= 8)){
                if(count == 0){
                    fen.append('/');
                }else{
                    fen.append(count).append('/');
                }
                count = 0;
            }
            tempRowNumber = rowNumber;
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
        for(int pos = 1; pos <= 64; pos++){
            if(this.chessPieces.containsKey(pos)){
                info.append("\n ").append(this.chessPieces.get(pos).repr());
            }
        }
        info.append("}");
        return info.toString();
    }
}
