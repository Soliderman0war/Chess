package chess;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private final ChessGame.TeamColor pieceColor;
    private final ChessPiece.PieceType pieceType;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.pieceType = type;
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
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, pieceType);
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        //Go through each piece and moves
        if(pieceType == PieceType.PAWN) {
            PawnMoveCalculator pawn = new PawnMoveCalculator();
            return pawn.pawnMoves(board, myPosition);
        }
        if(pieceType == PieceType.KING) {
            KingMoveCalculator king = new KingMoveCalculator();
            return king.kingMoves(board, myPosition);
        }
        if(pieceType == PieceType.QUEEN) {
            QueenMoveCalculator queen = new QueenMoveCalculator();
            return queen.queenMove(board, myPosition);
        }
        if(pieceType == PieceType.BISHOP) {
            BishopMoveCalculator bishop = new BishopMoveCalculator();
            return bishop.bishopMoves(board, myPosition);
        }
        if(pieceType == PieceType.KNIGHT) {
            KnightMoveCalculator knight = new KnightMoveCalculator();
            return knight.knightMove(board, myPosition);
        }
        if(pieceType == PieceType.ROOK) {
            RookMoveCalculator rook = new RookMoveCalculator();
            return rook.rookMove(board, myPosition);
        }
        else{
            return null;
        }
    }
}
