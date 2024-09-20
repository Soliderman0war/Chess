package chess;

import java.util.ArrayList;
import java.util.Collection;

        public class KnightMoveCalculator {

            public Collection<ChessMove> KnightMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor teamColor) {
                int row = myPosition.getRow();
                int col = myPosition.getColumn();
                Collection<ChessMove> moves = new ArrayList<>();

                int[][] positions = {
                        {row - 2, col - 1}, {row - 2, col + 1},
                        {row + 2, col - 1}, {row + 2, col + 1},
                        {row - 1, col - 2}, {row + 1, col - 2},
                        {row - 1, col + 2}, {row + 1, col + 2}
                };

                for (int[] pos : positions) {
                    int newRow = pos[0]; //iterate through each possible knight move
                    int newCol = pos[1];
                    if (newRow > 0 && newRow <= 8 && newCol > 0 && newCol <= 8) { //make sure still within bounds
                        ChessPiece piece = board.getPiece(new ChessPosition(newRow, newCol)); //Grabs piece if one
                        if (piece == null || FriendOrFoe(piece.getTeamColor(), teamColor)) {
                            moves.add(new ChessMove(myPosition, new ChessPosition(newRow, newCol), null));
                        }
                    }
                }

                return moves;
            }

            public boolean FriendOrFoe(ChessGame.TeamColor theirColor, ChessGame.TeamColor ourColor) {
                return theirColor != ourColor;
            }

    }

