package service;

import chess.ChessBoard;
import chess.ChessGame;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.AuthData;
import model.GameData;

import java.util.Objects;


public class GameService {

    GameDAO gameDAO;
    AuthDAO authDAO;
    int i;

    public GameService(GameDAO gameDAO, AuthDAO authDAO) {
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }

    public GameData getGameData(int gameID) throws DataAccessException {
        return gameDAO.getGame(gameID);
    }

    public int createGame(GameData gameData) throws DataAccessException {
        i = ++i;
        ChessGame game = new ChessGame();
        ChessBoard board = new ChessBoard();
        board.resetBoard();
        game.setBoard(board);
        gameDAO.createGame(new GameData(i, null, null, gameData.gameName(), game));
        //make gameID's so they don't overlap
        return i;
    }

    public boolean joinGame(String authToken, int gameID, String side) throws DataAccessException {
        AuthData authData;
        GameData gameData;
        authData = authDAO.getAuth(authToken);
        gameData = gameDAO.getGame(gameID);

        String whiteName = gameData.whiteUsername();
        String blackName = gameData.blackUsername();
        if(Objects.equals(side,"WHITE") && whiteName == null && whiteName.equals(authData.username())) {
            whiteName = authData.username();
        }
        else if(Objects.equals(side,"BLACK") && blackName == null && blackName.equals(authData.username())) {
            blackName = authData.username();
        }
        else{
            return false;
        }

        gameDAO.updateGame(new GameData(gameID, whiteName, blackName, gameData.gameName(), gameData.game()));

        return true;
    }

    public void clear() {
        gameDAO.clear();
    }



}
