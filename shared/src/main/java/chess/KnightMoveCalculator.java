package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMoveCalculator {
    Collection<ChessMove> knightMove(ChessBoard board, ChessPosition position){
        int row = position.getRow();
        int col = position.getColumn();
        Collection<ChessMove> moves = new ArrayList<>();
        int[][] possiblemoves = {
                {row + 2, col + 1},{row + 2, col - 1},
                {row - 2, col + 1},{row - 2, col - 1},
                {row + 1, col + 2},{row - 1, col + 2},
                {row + 1, col - 2},{row - 1, col - 2}
        };


        for(int[] pos: possiblemoves){
            int newRow = pos[0];
            int newCol = pos[1];
            if(newRow > 0 && newRow <= 8 && newCol > 0 && newCol <= 8){
                if(board.getPiece(new ChessPosition(newRow, newCol)) == null){
                    moves.add(new ChessMove(position, new ChessPosition(newRow, newCol),null));
                }
                else{
                    if(FriendOrFoe(board.getPiece(new ChessPosition(newRow, newCol)).getTeamColor(), board.getPiece(position).getTeamColor())){
                        moves.add(new ChessMove(position, new ChessPosition(newRow, newCol),null));
                    }
                }
            }

        }







        return moves;
    }
    public boolean FriendOrFoe(ChessGame.TeamColor theirColor, ChessGame.TeamColor myColor){
        return theirColor != myColor;
    }
}
