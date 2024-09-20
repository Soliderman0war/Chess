package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KingMoveCalculator {
    public Collection<ChessMove> KingMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        Collection<ChessMove> moves = new ArrayList<>();
        boolean withinBounds = row >= 0 && row <= 7 && col >= 0 && col <= 7;

        //Check all surrounding 8 square
        //goes from left to right up to down
        for(int i = -1; i < 2; i++) {
            System.out.println("New");
            System.out.println(row + i);

            for(int j = -1; j < 2; j++) {
                if ((i != 0 || j != 0) && withinBounds) {
                    ChessPosition piece_check = new ChessPosition(row + i, col + j);
                    //Self position no need to add
                    if (board.getPiece(piece_check) == null) {
                        System.out.println(col + j);
                        moves.add(new ChessMove(myPosition, new ChessPosition(row + i, col + j), null));
                    } else {
                        //return true if foe and return false if friend  then clears all continuing moves
                        if (FriendOrFoe(board.getPiece(piece_check).getTeamColor(), teamColor)) {
                            System.out.println(col + j);
                            moves.add(new ChessMove(myPosition, new ChessPosition(row + i, col + j), null));
                        }
                    }
                }
            }
        }
        return moves;
    }
    public boolean FriendOrFoe(ChessGame.TeamColor their_color, ChessGame.TeamColor our_color) {
        return their_color != our_color;
    }
}
