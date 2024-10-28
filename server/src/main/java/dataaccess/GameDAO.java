package dataaccess;

import model.GameData;

public interface GameDAO {
    void createGame(GameData game) throws DataAccessException;

    GameData getGame(int gameID) throws DataAccessException;

    void updateGame(GameData game) throws DataAccessException;

    boolean gameExists(int gameID);

    void clear();
}
