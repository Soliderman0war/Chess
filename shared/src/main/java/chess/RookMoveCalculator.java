package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RookMoveCalculator {
    Collection<ChessMove> rookMove(ChessBoard board, ChessPosition position){
        int row = position.getRow();
        int col = position.getColumn();
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


        //Call for up moves
        moves.addAll(upDownmoves(board, position, up));

        //Call for down moves
        moves.addAll(upDownmoves(board, position, down));

        //call for left moves
        moves.addAll(leftRightmoves(board, position, left));

        //Call for right moves
        moves.addAll(leftRightmoves(board, position, right));

        return moves;
    }

    Collection<ChessMove> upDownmoves(ChessBoard board, ChessPosition myPosition, List<Integer> rows){
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        Collection<ChessMove> upDownmoves = new ArrayList<>();

        for(int i = 0; i < rows.size(); i++){
            System.out.println(rows.get(i));
            boolean within_bounds = row > 0 && row <= 8 && col > 0 && col <= 8;
            if(within_bounds){
                if(board.getPiece(new ChessPosition(rows.get(i), col)) == null){

                    upDownmoves.add(new ChessMove(myPosition, new ChessPosition(rows.get(i), col),null));
                }
                else{
                    if(FriendOrFoe(board.getPiece(new ChessPosition(rows.get(i), col)).getTeamColor(), board.getPiece(myPosition).getTeamColor())){

                        upDownmoves.add(new ChessMove(myPosition, new ChessPosition(rows.get(i), col),null));

                    }

                    break; //Dont hop over
                }
            }

        }
        return upDownmoves;

    }

    Collection<ChessMove> leftRightmoves(ChessBoard board, ChessPosition myPosition, List<Integer> leftright){
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        Collection<ChessMove> leftRightmoves = new ArrayList<>();

        for(int i = 0; i < leftright.size(); i++){
            boolean within_bounds = row > 0 && row <= 8 && col > 0 && col <= 8;
            if(within_bounds){
                if(board.getPiece(new ChessPosition(row, leftright.get(i))) == null){
                    leftRightmoves.add(new ChessMove(myPosition, new ChessPosition(row, leftright.get(i)),null));
                }
                else{
                    if(FriendOrFoe(board.getPiece(new ChessPosition(row, leftright.get(i))).getTeamColor(), board.getPiece(myPosition).getTeamColor())){


                        leftRightmoves.add(new ChessMove(myPosition, new ChessPosition(row, leftright.get(i)),null));
                    }
                    break; // Don't hop
                }
            }
        }
        return leftRightmoves;
    }

    public boolean FriendOrFoe(ChessGame.TeamColor theirColor, ChessGame.TeamColor myColor){
        return theirColor != myColor;
    }

}
