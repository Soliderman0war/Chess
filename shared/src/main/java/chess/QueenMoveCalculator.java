package chess;

import java.util.ArrayList;
import java.util.Collection;

public class QueenMoveCalculator {
    Collection<ChessMove> QueenMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        Collection<ChessMove> moves = new ArrayList<>();

        //Call Bishop Moves
        BishopMoveCalculator bishop = new BishopMoveCalculator();
        moves.addAll(bishop.BishopMoves(board, myPosition, teamColor));


        //Call Rook Moves
        RookMoveCalculator rook = new RookMoveCalculator();
        moves.addAll(rook.RookMoves(board, myPosition, teamColor));


        return moves;
    }
}
