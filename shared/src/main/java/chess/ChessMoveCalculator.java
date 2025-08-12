package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

//Calculates any pieces move and returns Collection<ChessMove> set of moves
public class ChessMoveCalculator {
    //initialize the board and position
    private ChessBoard board;
    private ChessPosition myPosition;
    private ChessPosition currentPosition;
    private ChessGame.TeamColor myColor;
    //initialize row and column
    private int row;
    private int col;
    //initialize type, all moves, and promotion
    private ChessPiece.PieceType type;
    private Collection<ChessMove> moveList;
    private ChessPiece.PieceType promotion;


    public ChessMoveCalculator(ChessBoard board, ChessPosition myPosition){
        //assign board and position
        this.board = board;
        this.myPosition = myPosition;
        this.myColor = board.getPiece(myPosition).getTeamColor();
        //assign row and column values and new moveList
        this.row = myPosition.getRow();
        this.col = myPosition.getColumn();
        this.moveList = new ArrayList<>();

        //We also want what piece it is
        //type to reuse for other pieces
        this.type = board.getPiece(myPosition).getPieceType();

        //Call the correct method by finding the type
        //if(type == ChessPiece.PieceType.BISHOP){
          //  BishopMoveCalculator();
        //}


    }




    //Have a calculator for each piece type
    //Bishops Diagonal
    public Collection<ChessMove> BishopMoveCalculator(){
        /* Move diagonal from the position
            Northwest -1,1
            Northeast 1,1
            Southwest -1,-1
            Southeast 1,-1
            have withinBounds check
         */

        //rotated through all angles and reset current position each time
        for(int x = -1; x < 2; x+=2) {
            //x rotates through 1, -1
            for(int y = -1; y < 2; y+=2){
                //y rotates through 1, -1
                //resets current position before starting a new direction

                currentPosition = myPosition;
                ChessPosition futurePosition = DiagonalMove(x, y, currentPosition, myPosition).getEndPosition();


                //checks if the future move will be inbound
                while (WithinBounds(futurePosition)) {

                    //Continues as normal if position is null
                    if(board.getPiece(futurePosition) == null){
                        //adds all diagonal moves
                        moveList.add(DiagonalMove(x, y, currentPosition, myPosition));
                        //move current position
                        currentPosition = futurePosition;
                        //the future position changes since the current changes
                        futurePosition = DiagonalMove(x, y, currentPosition, myPosition).getEndPosition();
                        System.out.println(futurePosition);
                    }
                    else {
                        //find the blocking piece color
                        ChessGame.TeamColor blockingPieceColor = board.getPiece(futurePosition).getTeamColor();
                        System.out.println(blockingPieceColor);
                        //can only move to this square if it is the opposing color
                        if(myColor != blockingPieceColor){
                            //add the move
                            moveList.add(DiagonalMove(x, y, currentPosition, myPosition));
                        }
                        //otherwise get out of the loop
                        break;

                    }


                    //debug codes
                    //System.out.println("Row: " + currentPosition.getRow());
                    //System.out.println("Column: " + currentPosition.getColumn());
                    //System.out.println(x);
                    //System.out.println(y);
                    //System.out.println("Row: " + DiagonalMove(x,y,currentPosition).getEndPosition().getRow());
                    //System.out.println("Column: " + DiagonalMove(x,y,currentPosition).getEndPosition().getColumn());
                    //System.out.println(futurePosition);


                }
            }
        }


        return moveList;
    }

    //returns one move diagonal by adding the modifier to their current position but makes sure to hold the same position that was given
    public ChessMove DiagonalMove(int horizontalModifier, int verticalModifier, ChessPosition currentPosition, ChessPosition myPosition){
        if(type != ChessPiece.PieceType.PAWN){
            promotion = null;
        }
        return new ChessMove(myPosition, new ChessPosition(currentPosition.getRow() + horizontalModifier, currentPosition.getColumn() + verticalModifier), promotion);
    }

    /*ensures each piece is still moving within the bounds of a chess board
       returns true if still within bounds
     */
    public boolean WithinBounds(ChessPosition currentPosition){
        return (currentPosition.getRow() > 0 && currentPosition.getRow() < 9) && (currentPosition.getColumn() > 0 && currentPosition.getColumn()< 9);
    }


    /*ensures piece is not stopped by another piece
     and finds what color the piece is, however can't return two types
     * */
//    public ChessGame blockingPiece(ChessPosition currentPosition){
//        if(board.getPiece(currentPosition).getTeamColor() == null){
//            return null;
//        }
//        return board.getPiece(currentPosition).getTeamColor();
//    }


    //returns current move list
    public Collection<ChessMove> getMoveList(){
        return moveList;
    }





    //Rooks Horizontal/Vertical
    //Queen is Bishop and Rook combined
    //King one space around
    //Knights are L shaped
    //Pawns move up 1 (possible two at beginning) and attack diagonal


    //override


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessMoveCalculator that = (ChessMoveCalculator) o;
        return row == that.row && col == that.col && Objects.equals(board, that.board) && Objects.equals(myPosition, that.myPosition) && Objects.equals(currentPosition, that.currentPosition) && type == that.type && Objects.equals(moveList, that.moveList) && promotion == that.promotion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, myPosition, currentPosition, row, col, type, moveList, promotion);
    }
}
