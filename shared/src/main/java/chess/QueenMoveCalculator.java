package chess;

import java.util.ArrayList;
import java.util.Collection;

public class QueenMoveCalculator {
    Collection<ChessMove> QueenMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        Collection<ChessMove> moves = new ArrayList<>();

        //Call Bishop Moves

        //Call Rook Moves


        return moves;
    }
}
