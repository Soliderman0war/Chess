package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RookMoveCalculator {
    Collection<ChessMove> RookMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        Collection<ChessMove> moves = new ArrayList<>();
        List<Integer> rook_rows_down = new ArrayList<>();
        List<Integer> rook_rows_up = new ArrayList<>();
        List<Integer> rook_cols_left = new ArrayList<>();
        List<Integer> rook_cols_right = new ArrayList<>();
        boolean piece_blocking_right = false;
        boolean piece_blocking_left = false;
        boolean piece_blocking_up = false;
        boolean piece_blocking_down = false;
        boolean withinBounds = row >= 0 && row <= 7 && col >= 0 && col <= 7;

        //Find all available moves
        //Moving Down
        for(int i = 1; i < row; i++){
            rook_rows_down.add(row - i);
        }
        //Moving left
        for(int j = 1; j < col; j++){
            rook_cols_left.add(col - j);
        }
        //Moving Up
        for(int k = row + 1; k <= 8; k++){
            rook_rows_up.add(k);
        }
        //Moving Right
        for(int l = col + 1; l <= 8; l++){
            rook_cols_right.add(l);
        }

        for(int i = 0; i < rook_rows_down.size(); i++) {
            if (!piece_blocking_down && withinBounds && board.getPiece(new ChessPosition(rook_rows_down.get(i), col)) == null) {
                moves.add(new ChessMove(myPosition, new ChessPosition(rook_rows_down.get(i), col), null));

            }
            else {
                if (board.getPiece(new ChessPosition(rook_rows_down.get(i), col)) != null) {
                    if (FriendOrFoe(board.getPiece(new ChessPosition(rook_rows_down.get(i), col)).getTeamColor(), teamColor)) {

                        moves.add(new ChessMove(myPosition, new ChessPosition(rook_rows_down.get(i), col), null));
                    }
                    piece_blocking_down = true;
                }
            }
        }

        for(int i = 0; i < rook_rows_up.size(); i++) {
            if (!piece_blocking_up && withinBounds && board.getPiece(new ChessPosition(rook_rows_up.get(i), col)) == null) {
                moves.add(new ChessMove(myPosition, new ChessPosition(rook_rows_up.get(i), col), null));

            }
            else {
                if (board.getPiece(new ChessPosition(rook_rows_up.get(i), col)) != null) {
                    if (FriendOrFoe(board.getPiece(new ChessPosition(rook_rows_up.get(i), col)).getTeamColor(), teamColor)) {

                        moves.add(new ChessMove(myPosition, new ChessPosition(rook_rows_up.get(i), col), null));
                    }
                    piece_blocking_up = true;
                }
            }
        }

        for(int i = 0; i < rook_cols_left.size(); i++) {
            if (!piece_blocking_left && withinBounds && board.getPiece(new ChessPosition(row, rook_cols_left.get(i))) == null) {
                moves.add(new ChessMove(myPosition, new ChessPosition(row , rook_cols_left.get(i)), null));

            }
            else {
                if (board.getPiece(new ChessPosition(row, rook_cols_left.get(i))) != null) {
                    if (FriendOrFoe(board.getPiece(new ChessPosition(row, rook_cols_left.get(i))).getTeamColor(), teamColor)) {

                        moves.add(new ChessMove(myPosition, new ChessPosition(row, rook_cols_left.get(i)), null));
                    }
                    piece_blocking_left = true;
                }
            }
        }

        for(int i = 0; i < rook_cols_right.size(); i++) {
            if (!piece_blocking_right && withinBounds && board.getPiece(new ChessPosition(row, rook_cols_right.get(i))) == null) {
                moves.add(new ChessMove(myPosition, new ChessPosition(row, rook_cols_right.get(i)), null));

            }
            else {
                if (board.getPiece(new ChessPosition(row, rook_cols_right.get(i))) != null) {
                    if (FriendOrFoe(board.getPiece(new ChessPosition(row, rook_cols_right.get(i))).getTeamColor(), teamColor)) {

                        moves.add(new ChessMove(myPosition, new ChessPosition(row, rook_cols_right.get(i)), null));
                    }
                    piece_blocking_right = true;
                }
            }
        }



        return moves;
    }

    public boolean FriendOrFoe(ChessGame.TeamColor their_color, ChessGame.TeamColor our_color) {
        return their_color != our_color;
    }
}
