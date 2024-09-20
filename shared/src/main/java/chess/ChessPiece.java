package chess;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import chess.PawnMoveCalculator;
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

    @Override
    public String toString() {
        return "ChessPiece{" +
                "pieceType=" + pieceType +
                ", pieceColor=" + pieceColor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceType == that.pieceType && pieceColor == that.pieceColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, pieceColor);
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */


    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
       System.out.println(getPieceType());
       if(getPieceType() == PieceType.PAWN){
           PawnMoveCalculator pawn = new PawnMoveCalculator();
           return pawn.PawnMoveCalculator(board, myPosition, getTeamColor());
       }
       else if(getPieceType() == PieceType.KING){
           KingMoveCalculator king = new KingMoveCalculator();
           return king.KingMoves(board, myPosition, getTeamColor()) ;
       }
       else if(getPieceType() == PieceType.QUEEN){
           QueenMoveCalculator queen = new QueenMoveCalculator();
           return queen.QueenMoves(board, myPosition, getTeamColor());
       }
       else if(getPieceType() == PieceType.BISHOP){
           BishopMoveCalculator bishop = new BishopMoveCalculator();
           return bishop.BishopMoves(board, myPosition, getTeamColor());
       }
       else if(getPieceType() == PieceType.KNIGHT){
           KnightMoveCalculator knight = new KnightMoveCalculator();
           return knight.KnightMoves(board, myPosition, getTeamColor());
       }
       else if(getPieceType() == PieceType.ROOK){
           RookMoveCalculator rook = new RookMoveCalculator();
           return rook.RookMoves(board, myPosition, getTeamColor());
       }
       else{
           return null;
       }
    }
}
