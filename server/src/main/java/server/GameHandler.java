package server;

import com.google.gson.Gson;
import dataaccess.*;
import model.*;
import service.GameService;
import spark.*;

import java.util.Collection;

public class GameHandler {
    GameService gameService;

    public GameHandler(GameService gameService) {
        this.gameService = gameService;
    }

    public Object listGames(Request request, Response response) {
        String AuthID = request.session().attribute("AuthID");

        Collection<GameData> games = new gameService.listGames(AuthID);

        response.status(200);
        return new Gson().toJson(games);
    }

    public Object createGame(Request request, Response response) {
        String AuthID = request.session().attribute("AuthID");

    }


}
