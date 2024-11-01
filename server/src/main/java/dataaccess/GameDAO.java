package dataaccess;

import model.GameData;

import java.util.Collection;

public interface GameDAO {
    GameData createGame(GameData game) throws DataAccessException;

    GameData getGame(int gameID) throws DataAccessException;

    void updateGame(GameData game) throws DataAccessException;

    boolean gameExists(int gameID);

    Collection<GameData> getAllGames() throws DataAccessException;

    void clear();
}
