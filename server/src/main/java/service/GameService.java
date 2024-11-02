package service;

import chess.ChessBoard;
import chess.ChessGame;
import dataaccess.*;
import model.AuthData;
import model.GameData;

import java.util.Objects;
import java.util.Collection;


public class GameService {

    GameDAO gameDAO;
    AuthDAO authDAO;

    public GameService(GameDAO gameDAO, AuthDAO authDAO) {
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }

    public Collection<GameData> listGames(String authId) throws Unauthorized, DataAccessException {
        //positive case
        try{
            //Are they authorized?
            authDAO.getAuth(authId);
        }
        //negative case
        catch (Unauthorized e){
            throw new Unauthorized(e.getMessage());
        }

        return gameDAO.getAllGames();
    }

    public GameData getGame(int gameId, String authId) throws Unauthorized, BadRequest, DataAccessException {
        try{
            //Are they authorized?
            authDAO.getAuth(authId);
        }
        catch (Unauthorized e){
            throw new Unauthorized(e.getMessage());
        }

        try {
            return gameDAO.getGame(gameId);
        }
        catch (DataAccessException e){
            throw new BadRequest(e.getMessage());
        }
    }

    public void updateGame(GameData gameData, String authId) throws Unauthorized, DataAccessException {
        try{
            authDAO.getAuth(authId);
        }
        catch (Unauthorized e){
            throw new Unauthorized(e.getMessage());
        }

        try {
            gameDAO.updateGame(gameData);
        }
        catch (DataAccessException e){
            throw new BadRequest(e.getMessage());
        }
    }








}
