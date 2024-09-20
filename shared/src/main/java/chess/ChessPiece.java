package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private PieceType pieceType;
    private ChessGame.TeamColor pieceColor;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceType = type;
        this.pieceColor = pieceColor;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
   public Collection<ChessMove> PawnMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        if(board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.BLACK){
            if(row == 6) {
                // Check for initial position of black pawn and add two-square move
                moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col), null));
                moves.add(new ChessMove(myPosition, new ChessPosition(row - 2, col), null));
            }
            else{
                //one-square move for black if not starting position
                moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col), null));

            }
        }


        if(board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.WHITE){
            if(row == 1) {
                moves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col), null));
                moves.add(new ChessMove(myPosition, new ChessPosition(row + 2, col), null));
            }
            else{
                moves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col), null));
            }
        }
        return moves;
    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return PawnMoves(board, myPosition);
    }
}
