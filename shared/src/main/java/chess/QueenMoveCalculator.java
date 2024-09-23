package chess;

import java.util.ArrayList;
import java.util.Collection;

public class QueenMoveCalculator {
    Collection<ChessMove> queenMove(ChessBoard board, ChessPosition position){
        Collection<ChessMove> moves = new ArrayList<>();
        RookMoveCalculator rookMove = new RookMoveCalculator();
        BishopMoveCalculator bishopMove = new BishopMoveCalculator();

        //call rook
        moves.addAll(rookMove.rookMove(board, position));


        //call bishop
        moves.addAll(bishopMove.bishopMoves(board, position));

        return moves;
    }
}
