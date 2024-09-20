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
        for(int i = 0; i < bishop_rows_down.size(); i++){
            System.out.println(bishop_rows_down.get(i));
            if(i < bishop_cols_left.size()){
                System.out.println(bishop_cols_left.get(i));
                moves.add(new ChessMove(myPosition, new ChessPosition(bishop_rows_down.get(i), bishop_cols_left.get(i)), null));
            }
            if(i < bishop_cols_right.size()){
                System.out.println(bishop_cols_right.get(i));
                moves.add(new ChessMove(myPosition, new ChessPosition(bishop_rows_down.get(i), bishop_cols_right.get(i)), null));
            }

        }
        for(int i = 0; i < bishop_rows_up.size(); i++){
                System.out.println(bishop_rows_up.get(i));
                if(i < bishop_cols_left.size()) {
                    //check if there's a piece
                    if (Chess(new ChessPosition(bishop_rows_up.get(i), bishop_cols_left.get(i))) != null) {

                    } else {
                        System.out.println(bishop_cols_left.get(i));
                        moves.add(new ChessMove(myPosition, new ChessPosition(bishop_rows_up.get(i), bishop_cols_left.get(i)), null));
                    }
                    if (i < bishop_cols_right.size()) {
                        System.out.println(bishop_cols_right.get(i));
                        moves.add(new ChessMove(myPosition, new ChessPosition(bishop_rows_up.get(i), bishop_cols_right.get(i)), null));
                    }

                }


            }



        return moves;
    }

    public boolean IsMyPiece(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        if(ChessPiece.getTeamColor() == teamColor) {

        }
    }

    public boolean IsEnemy(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        if() {}
    }
}
