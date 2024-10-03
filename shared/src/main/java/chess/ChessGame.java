package chess;

import java.util.ArrayList;
import java.util.Collection;
/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private TeamColor teamColor;

    public ChessGame(TeamColor teamColor) {
        setTeamTurn(TeamColor.WHITE); //Set beginning to White
        this.teamColor = teamColor;
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
            ChessPiece phantomPiece = board.getPiece(move.getEndPosition());
            board.addPiece(startPosition, null);
            board.addPiece(move.getEndPosition(), phantomPiece);
            if(!isInCheck(thisPiece.getTeamColor())){
                validMoves.add(move);
            }
            board.addPiece(move.getEndPosition(), phantomPiece);
            board.addPiece(startPosition, thisPiece);

        }

       return validMoves;




    }


    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        Collection<ChessMove> validMoves = validMoves(move.getStartPosition());
        if (validMoves.isEmpty()) {
            throw new InvalidMoveException("No valid move");
        }

        if(teamColor == getTeamTurn()){

        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        throw new RuntimeException("Not implemented");
    }
}
