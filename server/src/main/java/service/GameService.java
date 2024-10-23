package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.GameData;


public class GameService {

    GameDAO gameDAO;
    AuthDAO authDAO;

    public GameService(GameDAO gameDAO, AuthDAO authDAO) {
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }

    public GameData getGameData(int gameID) throws DataAccessException {
        return gameDAO.getGame(gameID);
    }

    public int createGame(GameData gameData) throws DataAccessException {
        int gameID;
        //make gameID's so they don't overlap
    }



}
