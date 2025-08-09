package chess;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    //create the Chess Board
    private ChessPiece[][] squares = new ChessPiece[9][9];

    public ChessBoard() {

    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        //Set a position to a chess piece
        squares[position.getColumn()][position.getRow()] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        //return what piece is at a position
        return squares[position.getColumn()][position.getRow()];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        //Reset Pawns
        for(int i = 1; i < 9; i++){
            //Add in White pawns across row 2
            addPiece(new ChessPosition(2,i),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
            //Add in Black pawns across row 7
            addPiece(new ChessPosition(7,i),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        }

        //add in Rooks
        for(int i = 1; i < 9; i+=7){
            //White Rooks 1,1 1,8
            addPiece(new ChessPosition(1,i),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
            //Black Rooks 8,1 8,8
            addPiece(new ChessPosition(8,i),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        }

        //Add in Knights
        for(int i = 2; i < 8; i+=5){
            //White Knights 1,2 1,7
            addPiece(new ChessPosition(1,i),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
            //Black Knights 8,2 8,7
            addPiece(new ChessPosition(8,i),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        }

        //Add in Bishops
        for(int i = 3; i < 7; i+=3){
            //White Bishops 1,3 1,6
            addPiece(new ChessPosition(1,i),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
            //Black Bishops 8,3 8,6
            addPiece(new ChessPosition(8,i), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        }

        //Add in Queens
        //White Queen 1,4
        addPiece(new ChessPosition(1,4), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN));
        //Black Queen 8,4
        addPiece(new ChessPosition(8,4), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));

        //Add in Kings
        //White King 1,5
        addPiece(new ChessPosition(1,5), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
        //Black King 8,5
        addPiece(new ChessPosition(8,5), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));

    }



    //Override
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(squares);
    }

}
