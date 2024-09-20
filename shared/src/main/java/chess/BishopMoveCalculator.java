package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMoveCalculator {

    public Collection<ChessMove> BishopMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        Collection<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        List<Integer> bishop_rows_down = new ArrayList<>();
        List<Integer> bishop_rows_up = new ArrayList<>();
        List<Integer> bishop_cols_left = new ArrayList<>();
        List<Integer> bishop_cols_right = new ArrayList<>();
        boolean piece_blocking_down_r = false;
        boolean piece_blocking_down_l = false;
        boolean piece_blocking_up_r = false;
        boolean piece_blocking_up_l = false;

        //Find all available moves
        //Moving Down
        for(int i = 1; i < row; i++){
            bishop_rows_down.add(row - i);
        }
        //Moving left
        for(int j = 1; j < col; j++){
            bishop_cols_left.add(col - j);
        }
        //Moving Up
        for(int k = row + 1; k < 9; k++){
            bishop_rows_up.add(k);
        }
        //Moving Right
        for(int l = col + 1; l < 9; l++){
            bishop_cols_right.add(l);
        }
        //Going through and matching down with left and right movements
        for(int i = 0; i < bishop_rows_down.size(); i++) {
            System.out.println("New");
            System.out.println(bishop_rows_down.get(i));
            if (i < bishop_cols_left.size() && !piece_blocking_down_l) {

                ChessPosition piece_check_down_l = new ChessPosition(bishop_rows_down.get(i), bishop_cols_left.get(i));
                //check if there's a piece
                if (board.getPiece(piece_check_down_l) == null) {
                    System.out.println(bishop_cols_left.get(i));
                    moves.add(new ChessMove(myPosition, new ChessPosition(bishop_rows_down.get(i), bishop_cols_left.get(i)), null));
                } else {
                    //return true if foe and return false if friend  then clears all continuing moves
                    if (FriendOrFoe(board.getPiece(piece_check_down_l).getTeamColor(), teamColor)) {
                        System.out.println(bishop_cols_left.get(i));
                        moves.add(new ChessMove(myPosition, new ChessPosition(bishop_rows_down.get(i), bishop_cols_left.get(i)), null));
                    }
                    piece_blocking_down_l = true;
                }
            }
                if (i < bishop_cols_right.size() && !piece_blocking_down_r) {
                    ChessPosition piece_check_down_r = new ChessPosition(bishop_rows_down.get(i), bishop_cols_right.get(i));
                    //check if there's a piece
                    if (board.getPiece(piece_check_down_r) == null) {
                        System.out.println(bishop_cols_right.get(i));
                        moves.add(new ChessMove(myPosition, new ChessPosition(bishop_rows_down.get(i), bishop_cols_right.get(i)), null));
                    } else {
                        //return true if foe and return false if friend  then clears all continuing moves
                        if (FriendOrFoe(board.getPiece(piece_check_down_r).getTeamColor(), teamColor)) {
                            System.out.println(bishop_cols_right.get(i));
                            moves.add(new ChessMove(myPosition, new ChessPosition(bishop_rows_down.get(i), bishop_cols_right.get(i)), null));
                        }
                        piece_blocking_down_r = true;
                    }
                }
        }
            //Get moves Up then (Left and Right)
        for(int i = 0; i < bishop_rows_up.size(); i++){
                System.out.println("New");
                System.out.println(bishop_rows_up.get(i));
                if(i < bishop_cols_left.size() && !piece_blocking_up_l) {
                    ChessPosition piece_check = new ChessPosition(bishop_rows_up.get(i), bishop_cols_left.get(i));
                    //check if there's a piece
                    if (board.getPiece(piece_check) == null) {
                        System.out.println(bishop_cols_left.get(i));
                        moves.add(new ChessMove(myPosition, new ChessPosition(bishop_rows_up.get(i), bishop_cols_left.get(i)), null));
                    } else {
                        //return true if foe and return false if friend  then clears all continuing moves
                        if (FriendOrFoe(board.getPiece(piece_check).getTeamColor(), teamColor)) {
                            System.out.println(bishop_cols_left.get(i));
                            moves.add(new ChessMove(myPosition, new ChessPosition(bishop_rows_up.get(i), bishop_cols_left.get(i)), null));
                        }
                        piece_blocking_up_l = true;
                    }
                }
                    
                    if (i < bishop_cols_right.size() && !piece_blocking_up_r) {
                        ChessPosition piece_check_up_r = new ChessPosition(bishop_rows_up.get(i), bishop_cols_right.get(i));
                        //check if there's a piece
                        if(board.getPiece(piece_check_up_r) == null) {
                            System.out.println(bishop_cols_right.get(i));
                            moves.add(new ChessMove(myPosition, new ChessPosition(bishop_rows_up.get(i), bishop_cols_right.get(i)), null));
                        }
                        else{
                            //return true if foe and return false if friend  then clears all continuing moves
                            if(FriendOrFoe(board.getPiece(piece_check_up_r).getTeamColor(),teamColor)) {
                                System.out.println(bishop_cols_right.get(i));
                                moves.add(new ChessMove(myPosition, new ChessPosition(bishop_rows_up.get(i), bishop_cols_right.get(i)), null));
                            }
                            piece_blocking_up_r = true;
                        }
                }

                }



        return moves;
    }

    public boolean FriendOrFoe(ChessGame.TeamColor their_color, ChessGame.TeamColor our_color) {
        return their_color != our_color;
    }
}
