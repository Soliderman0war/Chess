package chess;

import java.util.ArrayList;
import java.util.Collection;


public class KingMoveCalculator {
    Collection<ChessMove> kingMoves(ChessBoard board, ChessPosition position){
        int row = position.getRow();
        int col = position.getColumn();
        Collection<ChessMove> moves = new ArrayList<>();

        //create a 3x3 grid for the king to move along the edges, exclude center
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if((i != 0 || j != 0) && row + i > 0 && row + i <= 8 && col + j > 0 && col + j <= 8){
                    if(board.getPiece(new ChessPosition(row + i, col + j)) == null){
                        moves.add(new ChessMove(position, new ChessPosition(row + i, col + j),null));
                    }
                    else{
                        if(FriendOrFoe(board.getPiece(new ChessPosition(row + i, col + j)).getTeamColor(), board.getPiece(position).getTeamColor())){
                            moves.add(new ChessMove(position, new ChessPosition(row + i, col + j),null));
                        }


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
