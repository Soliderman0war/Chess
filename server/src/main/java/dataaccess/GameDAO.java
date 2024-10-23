package dataaccess;

public interface GameDAO {

    GameData getGame(int gameID) throws DataAccessException;
    boolean gameExists(int gameID);
}
