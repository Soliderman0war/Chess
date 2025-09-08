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
    private ChessPosition futurePosition;
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
        if(type == ChessPiece.PieceType.BISHOP){
            BishopMoveCalculator();
        }
        if(type == ChessPiece.PieceType.ROOK){
            RookMoveCalculator();
        }
        if(type == ChessPiece.PieceType.QUEEN){
            QueenMoveCalculator();
        }
        if(type == ChessPiece.PieceType.KING){
            KingMoveCalculator();
        }
        if(type == ChessPiece.PieceType.KNIGHT){
            KnightMoveCalculator();
        }
        if(type == ChessPiece.PieceType.PAWN){
            PawnMoveCalculator();
        }

    }




    //Have a calculator for each piece type
    //Bishops Diagonal
    private void BishopMoveCalculator(){
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

                //Ensure future position is set before beginning
                currentPosition = myPosition;
                futurePosition = directionalMove(x, y, currentPosition, myPosition).getEndPosition();

                //call method
                addMoves(x,y,futurePosition);

                }
            }
        }

    //Rook Horizontal + Vertical
    private void RookMoveCalculator(){
        //x = -1 or 1  y = 0
        //x = 0 y = -1 or 1
        //Ensure future position is set before beginning

        for(int x =-1; x<2; x+=2) {
            //x rotates through -1, 1
            //reset position each time
            currentPosition = myPosition;
            //reset future position
            futurePosition = directionalMove(x, 0, currentPosition, myPosition).getEndPosition();
            //add the moves
            addMoves(x, 0, futurePosition);
        }
        for(int y=-1; y<2; y+=2){
            //y rotates through -1, 1
            //reset position each time
            currentPosition = myPosition;
            //reset future position
            futurePosition = directionalMove(0, y, currentPosition, myPosition).getEndPosition();
            //add the moves
            addMoves(0, y, futurePosition);
        }
    }

    //Rook+Bishop
    private void QueenMoveCalculator(){
        BishopMoveCalculator();
        RookMoveCalculator();
    }
    
    //Rook + Bishop but only 1 space
    private void KingMoveCalculator(){
        /*Positions
         -1,-1 -1,0 -1,1
          0,-1  0,0  0,1
          1,-1  1,0  1,1
         */

        for(int x=-1; x<2; x++){
            //rotates through -1,0,1
            for(int y=-1; y<2; y++){
                //completes all the positions needed

                //only one so use singleMove

                currentPosition = myPosition;
                futurePosition = directionalMove(x, y, currentPosition, myPosition).getEndPosition();
                singleMove(x, y, futurePosition);

            }
        }
    }

    //L shaped movement
    private void KnightMoveCalculator(){
        /* Positions
         1,-2  1,2
        -1,-2 -1,2
         2,1   -2,1
         2,-1  -2,-1
         */

        for(int i=-2; i<3; i+=4){
            //rotate through i=-2 and i = 2
            for(int j=-1; j<2; j+=2){
                //rotate through j=-1 and j=1
                currentPosition = myPosition;

                //goes through each move by switching the position of i and j in the x and y positions
                futurePosition = directionalMove(i, j, currentPosition, myPosition).getEndPosition();
                singleMove(i, j, futurePosition);

                futurePosition = directionalMove(j, i, currentPosition, myPosition).getEndPosition();
                singleMove(j, i, futurePosition);

            }
        }





    }

    /*
    Pawn movements are as follows:
    1 move forward Unless the beginning space then they can move 2 spaces
    Attack move is diagonal in front of them
     */
    private void PawnMoveCalculator(){
        /*2 moves at beginning, (row =7 for black pawns, row = 2 for white pawns)
        attack diagonally
        able to promote at the end
         */
        //if the pawn is white
        if(myColor == ChessGame.TeamColor.WHITE){
            //if the row is 2 then move one or two 0,1 0,2
            //able to attack so 1,1 or -1,1
            if(myPosition.getRow() == 2){
                currentPosition = myPosition;

                for(int i = 1; i < 3; i++){
                    futurePosition = directionalMove(0,i, currentPosition, myPosition).getEndPosition();
                    singleMove(0,i,futurePosition);
                }

                //if there is a piece to attack
                for(int j = -1; j < 2; j+=2){
                    futurePosition = directionalMove(j,1, currentPosition, myPosition).getEndPosition();
                    singleMove(j,1,futurePosition);
                }
            }
            //otherwise one move 0,1
            //still able to attack 1,1 or -1,1
            else{

                futurePosition = directionalMove(0,1, currentPosition, myPosition).getEndPosition();
                singleMove(0,1,futurePosition);


                for(int j = -1; j < 2; j+=2){
                    futurePosition = directionalMove(j,1, currentPosition, myPosition).getEndPosition();
                    singleMove(j,1,futurePosition);
                }

            }


        }


    }

    //returns one move in a direction by adding the modifier to their current position but makes sure to hold the same position that was given
    public ChessMove directionalMove(int horizontalModifier, int verticalModifier, ChessPosition currentPosition, ChessPosition myPosition){
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


    /*Moving through the board with the modifiers and
    ensures it is not blocked or off the board
     */
    public void addMoves(int x, int y, ChessPosition futurePosition) {
        //checks if the future move will be inbound
        while (WithinBounds(futurePosition)) {

            //Continues as normal if position is null
            if (board.getPiece(futurePosition) == null) {
                //adds all diagonal moves
                moveList.add(directionalMove(x, y, currentPosition, myPosition));
                //move current position
                currentPosition = futurePosition;
                //the future position changes since the current changes
                futurePosition = directionalMove(x, y, currentPosition, myPosition).getEndPosition();
            } else {
                //find the blocking piece color
                ChessGame.TeamColor blockingPieceColor = board.getPiece(futurePosition).getTeamColor();
                //can only move to this square if it is the opposing color
                if (myColor != blockingPieceColor) {
                    //add the move
                    moveList.add(directionalMove(x, y, currentPosition, myPosition));
                }
                //otherwise get out of the loop
                break;

            }
        }
    }

    /*Instead of grabbing every move through a direction, just grabs one.
    This is for the King, Knight, Pawn
     */
    public void singleMove(int x, int y, ChessPosition futurePosition) {
        //need to do WithinBounds first otherwise getPiece will fail
        if (WithinBounds(futurePosition)) {
            if (board.getPiece(futurePosition) == null) {
                //adds the directional move
                moveList.add(directionalMove(x, y, currentPosition, myPosition));
            } else {
                //find the blocking piece color
                ChessGame.TeamColor blockingPieceColor = board.getPiece(futurePosition).getTeamColor();
                //can only move to this square if it is the opposing color
                if (myColor != blockingPieceColor) {
                    //add the move
                    moveList.add(directionalMove(x, y, currentPosition, myPosition));
                }
            }
        }
    }
    //returns current move list
    public Collection<ChessMove> getMoveList(){
        return moveList;
    }

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

/*
debug codes
System.out.println("Row: " + currentPosition.getRow());
System.out.println("Column: " + currentPosition.getColumn());
System.out.println(x);
System.out.println(y);
System.out.println("Row: " + directionalMove(x,y,currentPosition).getEndPosition().getRow());
System.out.println("Column: " + directionalMove(x,y,currentPosition).getEndPosition().getColumn());
System.out.println(futurePosition);
System.out.println("i: " + i);
System.out.println("j: " + j);

 */