package server;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dataaccess.*;
import model.AuthData;
import model.UserData;
import service.UserService;
import spark.*;

public class UserHandler {
    UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Object register(Request request, Response response) {
        UserData userData = new Gson().fromJson(request.body(), UserData.class);

        if (userData.username() == null || userData.password() == null) {
            response.status(403);
            return "Username or password is missing";
        }

        //createUser, If already created Badrequest exception

        try{
            AuthData authData = userService.createUser(userData);
            response.status(200);
            return new Gson().toJson(authData);
        } catch (Exception e) {
            response.status(403);
            return  "Error creating user, Already taken";
        }


    }

    public Object login(Request request, Response response) throws DataAccessException {
        UserData userData = new Gson().fromJson(request.body(), UserData.class);

        if (userData.username() == null || userData.password() == null) {
            response.status(403);
        }

        AuthData authData = userService.login(userData.username(), userData.password());

        response.status(200);
        return new Gson().toJson(authData);
    }

    public Object logout(Request request, Response response) throws DataAccessException {
        String authID = request.session().attribute("AuthID");

        userService.logout(authID);

        response.status(200);
        return "{}";
    }




}
