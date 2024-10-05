package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private TeamColor teamColor;
    private ChessBoard board;
    private ChessMove previousMove;
    private ChessMove enPassantMove;

    public ChessGame() {
        board = new ChessBoard(); //Get Board

        resetBoard(); // put pieces in
        setTeamTurn(TeamColor.WHITE); //Set beginning to White
    }

    public void resetBoard(){
        board.resetBoard();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return teamColor; //Returns teamColor
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        teamColor = team; //Sets teamColor to the team whose turn it is
    }

    public ChessMove setUpMove(){
        return previousMove; // gets previous move
    }

    public void setSetUpMove(ChessMove move){
        previousMove = move; //sets previous move
    }

    public void setEnPassantMove(ChessMove move){
        enPassantMove = move;
    }

    public ChessMove getEnPassantMove() {
        return enPassantMove;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return teamColor == chessGame.teamColor && Objects.equals(board, chessGame.board) && Objects.equals(previousMove, chessGame.previousMove);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor, board, previousMove);
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessBoard board = getBoard(); // get board
        ChessPiece thisPiece = board.getPiece(startPosition);
        if (thisPiece == null) {
            return null; //If there is no piece return null
        }
        //Use piece move function to get the collection of validMoves for the position

        Collection<ChessMove> validMoves = new ArrayList<>();
        Collection<ChessMove> possibleMoves = thisPiece.pieceMoves(board, startPosition);
        if(enPassantMove(getTeamTurn(), startPosition) != null){
            possibleMoves.add(enPassantMove(getTeamTurn(), startPosition));
        }
        for( ChessMove move : possibleMoves ) {
            //If the piece moves does it check?
            ChessPiece phantomPiece = board.getPiece(move.getEndPosition());
            board.addPiece(move.getStartPosition(), null); //remove to see
            board.addPiece(move.getEndPosition(), thisPiece); //Place there to see if it blocks
            if(!isInCheck(thisPiece.getTeamColor())){
                validMoves.add(move);

            }
            board.addPiece(move.getEndPosition(), phantomPiece); //places back the piece if wasn't null
            board.addPiece(move.getStartPosition(), thisPiece);
        }




        return validMoves;




    }

    @Override
    public String toString() {
        return "ChessGame{" +
                "teamColor=" + teamColor +
                ", board=" + board +
                ", previousMove=" + previousMove +
                '}';
    }

    public ChessMove enPassantMove(TeamColor teamColor, ChessPosition startPosition) {
        //if takes is it check?
        ChessBoard board = getBoard();
        ChessPiece thisPiece = board.getPiece(startPosition);
        if (thisPiece == null) {
            return null;
        }
        if(thisPiece.getPieceType() == ChessPiece.PieceType.PAWN && setUpMove() != null && thisPiece.getTeamColor() == teamColor){ //looks if it is a pawn
            if(board.getPiece(setUpMove().getEndPosition()).getPieceType() == ChessPiece.PieceType.PAWN){ //looks to see if other piece is a move that just moved
                if(setUpMove().getEndPosition().getColumn() == (startPosition.getColumn() + 1)  ||  setUpMove().getEndPosition().getColumn() == (startPosition.getColumn() - 1)){
                    //Check columns beside it
                    if(setUpMove().getStartPosition().getRow() == 2 && setUpMove().getEndPosition().getRow() == 4 && startPosition.getRow() == 4){ //if previous move was white
                        //Check if it was starting position

                        setEnPassantMove(new ChessMove(startPosition,new ChessPosition(setUpMove().getEndPosition().getRow() - 1, setUpMove().getEndPosition().getColumn()), null));
                        return new ChessMove(startPosition,new ChessPosition(setUpMove().getEndPosition().getRow() - 1, setUpMove().getEndPosition().getColumn()), null);
                    }
                    if(setUpMove().getStartPosition().getRow() == 7 && setUpMove().getEndPosition().getRow() == 5 && startPosition.getRow() == 5){ //if previous move was black
                        setEnPassantMove(new ChessMove(startPosition,new ChessPosition(setUpMove().getEndPosition().getRow() + 1, setUpMove().getEndPosition().getColumn()), null));
                        return new ChessMove(startPosition,new ChessPosition(setUpMove().getEndPosition().getRow() + 1, setUpMove().getEndPosition().getColumn()), null);
                    }
                }
            }
            else{
                return null; //stop looking otherwise
            }
        }
        return null; // No moves found

    }


    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        Collection<ChessMove> validMoves = validMoves(move.getStartPosition());
        if (validMoves == null) {
            throw new InvalidMoveException("No valid move");
        }


        if(validMoves.contains(move) && getBoard().getPiece(move.getStartPosition()).getTeamColor() == getTeamTurn()){
            ChessPiece movePiece = getBoard().getPiece(move.getStartPosition());

            if(move.equals(getEnPassantMove())){
                //take the pawn away if it was the enPassant move
                getBoard().addPiece(setUpMove().getEndPosition(), null) ;
            }
            if(move.getPromotionPiece() != null){
                movePiece = new ChessPiece(movePiece.getTeamColor(), move.getPromotionPiece());
                //Put in the Promotion piece
            }

            getBoard().addPiece(move.getStartPosition(), null); //Remove piece
            getBoard().addPiece(move.getEndPosition(), movePiece); //Add piece

            setSetUpMove(move);
            if(getTeamTurn() == TeamColor.WHITE){
                setTeamTurn(TeamColor.BLACK); //If last move was White then go to Black
            }
            else{
                setTeamTurn(TeamColor.WHITE); //Otherwise it was Blacks turn and now it is Whites
            }

        }
        else{
            throw new InvalidMoveException("Invalid move");
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        //Check if King is in the other teamColor's moves
        //go through board and check for king
        ChessPosition myKing = null;
        for(int i = 1; i <= 8; i ++){
            //rows
            for(int j = 1; j <= 8; j ++ ){
                //columns
                if(board.getPiece(new ChessPosition(i, j)) != null){
                    if(ChessPiece.PieceType.KING == board.getPiece(new ChessPosition(i, j)).getPieceType() && teamColor == board.getPiece(new ChessPosition(i, j)).getTeamColor()){
                        //Found king
                        myKing = new ChessPosition(i, j);
//                        System.out.println(i + " " + j);
                    }

                }
            }
        }

        //go through board and get other pieces moves
        for(int i = 1; i <= 8; i ++){
            //rows
            for(int j = 1; j <= 8; j ++ ){
                //columns
                ChessPiece piece = board.getPiece(new ChessPosition(i, j));
                if(board.getPiece(new ChessPosition(i, j)) != null){
                    if(board.getPiece(new ChessPosition(i, j)).getTeamColor() != teamColor){
//                        System.out.println(i + " " + j);
                        //check list
                        for(ChessMove move : piece.pieceMoves(board, new ChessPosition(i, j))){
                            if(move.getEndPosition().equals(myKing)){
                                return true;
                            }
                        }
                    }

                }
            }
        }




        return false; //the list doesn't have the king


    }


    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        //Check if no valid moves (which is stalemate) and if it is in Check
        if (noValid(teamColor)) return false;

        return isInCheck(teamColor);
    }

    private boolean noValid(TeamColor teamColor) {
        Collection<ChessMove> validMovesList;
        for(int i = 1; i <= 8; i ++){
            //rows
            for(int j = 1; j <= 8; j ++ ){
                //columns
                ChessPosition myPosition = new ChessPosition(i, j);
                ChessPiece piece = board.getPiece(myPosition);
                if(piece != null && board.getPiece(new ChessPosition(i, j)).getTeamColor() == teamColor){
                    validMovesList = validMoves(myPosition);
                    if(validMovesList != null && !validMovesList.isEmpty()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        //use the valid move list but this team if the teamcolor is itself and check if it is empty
        if (noValid(teamColor)) return false;
        return !isInCheck(teamColor); //return false if in check otherwise true because checkmate possibility
    }


    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }
}