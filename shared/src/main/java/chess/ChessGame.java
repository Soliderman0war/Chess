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

    public ChessGame() {
        board = new ChessBoard(); //Get Board
        setTeamTurn(TeamColor.WHITE); //Set beginning to White
        teamColor = TeamColor.WHITE;
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

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
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
        for( ChessMove move : possibleMoves ) {
            //If the piece moves does it check?
            ChessPiece phantomPiece = board.getPiece(move.getEndPosition());
            board.addPiece(move.getStartPosition(), null); //remove to see
            board.addPiece(move.getEndPosition(), thisPiece); //Place there to see if it blocks
            if(!isInCheck(thisPiece.getTeamColor())){
//                System.out.println("Check");
                validMoves.add(move);
            }
            board.addPiece(move.getEndPosition(), phantomPiece); //places back the piece if wasn't null
            board.addPiece(move.getStartPosition(), thisPiece);
        }

       return validMoves;




    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return teamColor == chessGame.teamColor && Objects.equals(board, chessGame.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor, board);
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


        if(validMoves.contains(move) && teamColor == getTeamTurn()){
            ChessPiece movePiece = getBoard().getPiece(move.getStartPosition());
            if(move.getPromotionPiece() != null){
                movePiece = new ChessPiece(movePiece.getTeamColor(), move.getPromotionPiece());
                //Put in the Promotion piece
            }
            getBoard().addPiece(move.getStartPosition(), null); //Remove piece
            getBoard().addPiece(move.getEndPosition(), movePiece); //Add piece
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

    @Override
    public String toString() {
        return "ChessGame{" +
                "teamColor=" + teamColor +
                ", board=" + board +
                '}';
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
        return isInCheck(teamColor) && isInStalemate(teamColor);
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
        Collection<ChessMove> validMovesList = new ArrayList<>();
        for(int i = 1; i <= 8; i ++){
            //rows
            for(int j = 1; j <= 8; j ++ ){
                //columns
                if(board.getPiece(new ChessPosition(i, j)).getTeamColor() == teamColor){
                    validMovesList.addAll(validMoves(new ChessPosition(i, j))); //add those moves to the list
                }
            }
        }

        return validMovesList.isEmpty();
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
