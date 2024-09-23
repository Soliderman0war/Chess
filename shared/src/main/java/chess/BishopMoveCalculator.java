package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMoveCalculator {
    Collection<ChessMove> bishopMoves(ChessBoard board, ChessPosition myPosition){
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        Collection<ChessMove> moves = new ArrayList<>();
        List<Integer> up = new ArrayList<>();
        List<Integer> down = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();


        //up
        for(int i = row + 1; i <= 8; i++){
            up.add(i);
        }

        //down
        for(int i = row - 1; i > 0; i--){
            down.add(i);
        }

        //left
        for(int i = col - 1; i > 0; i--){
            left.add(i);
        }

        //right
        for(int i = col + 1; i <= 8; i++){
            right.add(i);
        }

        //Call a function Diagonalmoves to implement movement
        //upleft
        moves.addAll(Diagonalmoves(board, myPosition, up, left));
        //upright
        moves.addAll(Diagonalmoves(board, myPosition, up, right));
        //downleft
        moves.addAll(Diagonalmoves(board, myPosition, down, left));
        //downright
        moves.addAll(Diagonalmoves(board, myPosition, down, right));

        return moves;

    }

    Collection<ChessMove> Diagonalmoves(ChessBoard board, ChessPosition myPosition, List<Integer> rows, List<Integer> cols){
        int row = myPosition.getRow();
        int col = myPosition.getColumn();


        Collection<ChessMove> diagonalmoves = new ArrayList<>();

        for(int i = 0; i < rows.size(); i++){
            boolean within_bounds = row > 0 && row <= 8 && col > 0 && col <= 8;
            if(within_bounds && i < cols.size() ){
                if(board.getPiece(new ChessPosition(rows.get(i), cols.get(i))) == null){
                    diagonalmoves.add(new ChessMove(myPosition, new ChessPosition(rows.get(i), cols.get(i)),null));
                }
                else{
                    if(FriendOrFoe(board.getPiece(new ChessPosition(rows.get(i), cols.get(i))).getTeamColor(), board.getPiece(myPosition).getTeamColor())){
                        diagonalmoves.add(new ChessMove(myPosition, new ChessPosition(rows.get(i), cols.get(i)),null));

                    }
                    break; //Don't hop over pieces
                }

            }
        }





        return diagonalmoves;
    }

    //decide if piece is friend or foe, return true if foe
    public boolean FriendOrFoe(ChessGame.TeamColor theirColor, ChessGame.TeamColor myColor){
        return theirColor != myColor;
    }
}
