package chess;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPiece that)) return false;
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

   public Collection<ChessMove> PawnMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();





        if(getTeamColor() == ChessGame.TeamColor.BLACK){
            System.out.println("Black");
            if(row == 7) {
                // Check for initial position of black pawn and add two-square move
                for (int i = 0; i < 2; i++){
                    moves.add(new ChessMove(myPosition, new ChessPosition(row - i, col), null));
                }
            }
            else if((row - 1) == 1){
                //PROMOTION TIME!!
                for (PieceType piece : PieceType.values()) {
                    if(piece != PieceType.KING && piece != PieceType.PAWN){
                        moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col), piece));
                    }

                }
            }
            else{
                //one-square move for black if not starting position
                moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col), null));
                System.out.println(row-1);
                System.out.println(col);
            }
            //if there are pieces to the corners it can eat them
                if (board.getPiece(new ChessPosition(row - 1, col - 1)) != null) {
                    if ((row - 1) == 1) {
                        for (PieceType piece : PieceType.values()) {
                            moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col - 1), piece));
                        }
                    } else {
                        moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col - 1), null));
                    }
                }
                if (board.getPiece(new ChessPosition(row - 1, col + 1)) != null) {
                    if ((row - 1) == 1) {
                        for (PieceType piece : PieceType.values()) {
                            moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col + 1), piece));
                        }
                    } else {
                        moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col + 1), null));
                    }
                }
            }


        if(getTeamColor() == ChessGame.TeamColor.WHITE){
            System.out.println("White");
            if(row == 2) {
                for (int i = 0; i < 2; i++){
                    moves.add(new ChessMove(myPosition, new ChessPosition(row + i, col), null));
                }
            }
            else if((row + 1) == 8){
                //PROMOTION TIME!!
                for (PieceType piece : PieceType.values()) {
                    if(piece != PieceType.KING && piece != PieceType.PAWN){
                        moves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col), piece));
                    }
                }
            }
            else{
                moves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col), null));
                System.out.println(row+1);
                System.out.println(col);
            }
        }
        return moves;
    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
       System.out.println(getPieceType());
       if(getPieceType() == PieceType.PAWN){

           return PawnMoves(board, myPosition);
       }
       else {
           return null;
       }
    }
}
