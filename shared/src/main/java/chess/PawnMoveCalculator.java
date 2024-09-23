package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMoveCalculator {
    Collection<ChessMove> pawnMoves(ChessBoard board, ChessPosition position){
        int row = position.getRow();
        int col = position.getColumn();
        Collection<ChessMove> moves = new ArrayList<>();
        //double move if white
        if(row == 2 && board.getPiece(position).getTeamColor() == ChessGame.TeamColor.WHITE){
            for(int i = 1; i <= 2; i++){
                if(board.getPiece(new ChessPosition(row + i, col)) == null){
                    moves.add(new ChessMove(position, new ChessPosition(row + i, col),null));

                }
                else{
                    break; //Don't hop over pieces
                }
            }
            //Function for pawn attack corner left and corner right
            if(col - 1 > 0 && col + 1 < 9) {
                moves.addAll(pawnWhiteAttackLeft(board, position, new ChessPosition(row + 1, col - 1)));
                moves.addAll(pawnWhiteAttackRight(board, position, new ChessPosition(row + 1, col + 1)));
            }
        }
        //normal move
        if(row != 2 && board.getPiece(position).getTeamColor() == ChessGame.TeamColor.WHITE){
            //PROMOTION
            if(row == 7 && board.getPiece(new ChessPosition(row + 1, col)) == null){
                moves.addAll(promotion(board, position, new ChessPosition(row + 1, col)));
            }
            if(row != 7 && board.getPiece(new ChessPosition(row + 1, col)) == null){
                moves.add(new ChessMove(position, new ChessPosition(row + 1, col),null));
            }
            if(board.getPiece(new ChessPosition(row + 1, col - 1)) != null){
                moves.addAll(pawnWhiteAttackLeft(board, position, new ChessPosition(row + 1, col - 1)));
            }
            if(board.getPiece(new ChessPosition(row + 1, col + 1)) != null){
                moves.addAll(pawnWhiteAttackRight(board, position, new ChessPosition(row + 1, col + 1)));
            }
        }


        //Double move black
        if(row == 7 && board.getPiece(position).getTeamColor() == ChessGame.TeamColor.BLACK){
            for(int i = 1; i <= 2; i++){
                if(board.getPiece(new ChessPosition(row - i, col)) == null){
                    moves.add(new ChessMove(position, new ChessPosition(row - i, col),null));
                }
                else{
                    break; //Dont hop
                }
            }
            if(col - 1 > 0 && col + 1 < 9) {
                moves.addAll(pawnBlackAttack(board, position, new ChessPosition(row + 1, col - 1)));
                moves.addAll(pawnBlackAttack(board, position, new ChessPosition(row + 1, col + 1)));
            }
        }
        //normal move
        if(row != 7 && board.getPiece(position).getTeamColor() == ChessGame.TeamColor.BLACK){
            //PROMOTION
            if(row == 2 && board.getPiece(new ChessPosition(row - 1, col)) == null){
                moves.addAll(promotion(board, position, new ChessPosition(row - 1, col)));
            }
            if(row != 2 && board.getPiece(new ChessPosition(row - 1, col)) == null){
                moves.add(new ChessMove(position, new ChessPosition(row - 1, col),null));
            }
            if(board.getPiece(new ChessPosition(row - 1, col - 1)) != null){
                moves.addAll(pawnBlackAttack(board, position, new ChessPosition(row - 1, col - 1)));
            }
            if(board.getPiece(new ChessPosition(row - 1, col + 1)) != null){
                moves.addAll(pawnBlackAttack(board, position, new ChessPosition(row - 1, col + 1)));
            }
        }




        return moves;
    }

    Collection<ChessMove> promotion(ChessBoard board, ChessPosition position, ChessPosition newPosition){
        Collection<ChessMove> promotions = new ArrayList<>();

        promotions.add(new ChessMove(position, newPosition, ChessPiece.PieceType.KNIGHT));
        promotions.add(new ChessMove(position, newPosition, ChessPiece.PieceType.BISHOP));
        promotions.add(new ChessMove(position, newPosition, ChessPiece.PieceType.ROOK));
        promotions.add(new ChessMove(position, newPosition, ChessPiece.PieceType.QUEEN));

        return promotions;
    }


    Collection<ChessMove> pawnBlackAttack(ChessBoard board, ChessPosition myPosition, ChessPosition newPosition){
        Collection<ChessMove> blackLeft = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        if(board.getPiece(newPosition) == null){
            //do nothing
        }
        else if(row == 2 && board.getPiece(newPosition) != null){
            blackLeft.addAll(promotion(board,myPosition,newPosition));

        }
        else{
            if(FriendOrFoe(board.getPiece(newPosition).getTeamColor(), board.getPiece(myPosition).getTeamColor())){
                blackLeft.add(new ChessMove(myPosition, newPosition,null));
            }

        }
        return blackLeft;

    }





    Collection<ChessMove> pawnWhiteAttackLeft(ChessBoard board, ChessPosition myPosition, ChessPosition newPosition){
        Collection<ChessMove> whiteLeft = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        if(board.getPiece(newPosition) == null){
            //do nothing
        }
        else if(row == 7 && board.getPiece(newPosition) != null){
            whiteLeft.addAll(promotion(board,myPosition,newPosition));

        }
        else{
            if(FriendOrFoe(board.getPiece(newPosition).getTeamColor(), board.getPiece(myPosition).getTeamColor())){
                whiteLeft.add(new ChessMove(myPosition, newPosition,null));
            }

        }
        return whiteLeft;
    }


    Collection<ChessMove> pawnWhiteAttackRight(ChessBoard board, ChessPosition myPosition, ChessPosition newPosition){
        Collection<ChessMove> whiteRight = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        if(board.getPiece(newPosition) == null){
            //do nothing
        }
        else if(row == 7 && board.getPiece(newPosition) != null){
            whiteRight.addAll(promotion(board,myPosition,newPosition));

        }
        else{
            if(FriendOrFoe(board.getPiece(newPosition).getTeamColor(), board.getPiece(myPosition).getTeamColor())){
                whiteRight.add(new ChessMove(myPosition, newPosition,null));
            }
        }
        return whiteRight;
    }

    public boolean FriendOrFoe(ChessGame.TeamColor theirColor, ChessGame.TeamColor myColor){
        return theirColor != myColor;
    }

}
