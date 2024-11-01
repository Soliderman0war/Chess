package service;


import dataaccess.*;
import model.AuthData;
import model.UserData;

import java.util.UUID;

public class UserService {

    UserDAO userDAO;
    AuthDAO authDAO;

    public UserService(UserDAO userDao, AuthDAO authDAO) {
        this.userDAO = userDao;
        this.authDAO = authDAO;
    }

    public AuthData createUser(UserData userData) throws DataAccessException {
        //positive case
        try{
            userDAO.registerUser(userData);
        }
        //negative case
        catch(DataAccessException e){
            throw new BadRequest(e.getMessage());
        }

        //get random ID and create to put in db
        String authId = UUID.randomUUID().toString();
        AuthData authData = new AuthData(userData.username(), authId);
        authDAO.addAuth(authData);

        return authData;
    }

    public AuthData login(String username, String password) throws DataAccessException {
        //positive case
        boolean isUserCorrect = false;
        try{
            isUserCorrect = userDAO.authenticateUser(username,password);
        }
        //negative case
        catch (DataAccessException e) {
            throw new Unauthorized(e.getMessage());
        }

        //If user correctly signs in then assign Authdata
        if(isUserCorrect){
            String authId = UUID.randomUUID().toString();
            AuthData authData = new AuthData(username, authId);
            authDAO.addAuth(authData);
            return authData;
        }
        else{
            throw new Unauthorized("Incorrect username or password");
        }

    }

    public  void logout(String authToken) throws Unauthorized {
        //positive case
        try {
            authDAO.getAuth(authToken);
        }
        //negative case
        catch (Unauthorized e) {
            throw new Unauthorized(e.getMessage());
        }

        authDAO.deleteAuth(authToken);
    }

    public AuthData getAuth(String authToken) throws Unauthorized {
        //positive case
        try{
            return authDAO.getAuth(authToken);
        }
        //negative
        catch (Unauthorized e) {
            throw new Unauthorized(e.getMessage());
        }
    }

    public void clear(){
        userDAO.clear();
        authDAO.clear();
    }


}
