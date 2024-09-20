
package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMoveCalculator{



    public Collection<ChessMove> PawnMoveCalculator(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
        Collection<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        if(teamColor == ChessGame.TeamColor.BLACK) {
            System.out.println("Black");
            if(row == 7) {
                // Check for initial position of black pawn and add two-square move
                for (int i = 0; i < 2; i++) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(row - i, col), null));
                }
            } else if ((row - 1) == 1) {
                // PROMOTION TIME!!
                for (ChessPiece.PieceType piece : ChessPiece.PieceType.values()) {
                    if(piece != ChessPiece.PieceType.KING && piece != ChessPiece.PieceType.PAWN) {
                        moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col), piece));
                    }
                }
            } else {
                // One-square move for black if not starting position
                moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col), null));
                System.out.println(row - 1);
                System.out.println(col);
            }

            // If there are pieces to the corners, it can eat them
            if (board.getPiece(new ChessPosition(row - 1, col - 1)) != null) {
                if ((row - 1) == 1) {
                    for (ChessPiece.PieceType piece : ChessPiece.PieceType.values()) {
                        if (piece != ChessPiece.PieceType.KING && piece != ChessPiece.PieceType.PAWN) {
                            moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col - 1), piece));
                        }
                    }
                } else {
                    moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col - 1), null));
                }
            }
            if (board.getPiece(new ChessPosition(row - 1, col + 1)) != null) {
                if ((row - 1) == 1) {
                    for (ChessPiece.PieceType piece : ChessPiece.PieceType.values()) {
                        if(piece != ChessPiece.PieceType.KING && piece != ChessPiece.PieceType.PAWN) {
                            moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col + 1), piece));
                        }
                    }
                } else {
                    moves.add(new ChessMove(myPosition, new ChessPosition(row - 1, col + 1), null));
                }
            }
        }

        if(teamColor == ChessGame.TeamColor.WHITE) {
            System.out.println("White");
            if (row == 2) {
                for (int i = 0; i < 2; i++) {
                    moves.add(new ChessMove(myPosition, new ChessPosition(row + i, col), null));
                }
            } else if ((row + 1) == 8) {
                // PROMOTION TIME!!
                for (ChessPiece.PieceType piece : ChessPiece.PieceType.values()) {
                    if (piece != ChessPiece.PieceType.KING && piece != ChessPiece.PieceType.PAWN) {
                        moves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col), piece));
                    }
                }
            } else {
                moves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col), null));
                System.out.println(row + 1);
                System.out.println(col);
            }

            // If there are pieces to the corners, it can eat them
            if (board.getPiece(new ChessPosition(row + 1, col - 1)) != null) {
                if ((row + 1) == 8) {
                    for (ChessPiece.PieceType piece : ChessPiece.PieceType.values()) {
                        if (piece != ChessPiece.PieceType.KING && piece != ChessPiece.PieceType.PAWN) {
                            moves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col - 1), piece));
                        }
                    }
                } else {
                    moves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col - 1), null));
                }
            }
            if (board.getPiece(new ChessPosition(row + 1, col + 1)) != null) {
                if ((row + 1) == 8) {
                    for (ChessPiece.PieceType piece : ChessPiece.PieceType.values()) {
                        if (piece != ChessPiece.PieceType.KING && piece != ChessPiece.PieceType.PAWN) {
                            moves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), piece));
                        }
                    }
                } else {
                    moves.add(new ChessMove(myPosition, new ChessPosition(row + 1, col + 1), null));
                }
            }
        }

        return moves;
    }
}