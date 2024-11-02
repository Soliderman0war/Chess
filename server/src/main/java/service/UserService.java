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

    //creates a user
    public AuthData createUser(UserData userData) throws DataAccessException {
        //positive case
        try{
            userDAO.registerUser(userData);
        }
        //negative case
        catch(DataAccessException e){
            throw new BadRequest(e.getMessage());
        }

        //get random ID
        String authId = UUID.randomUUID().toString();
        AuthData authData = new AuthData(userData.username(), authId);
        authDAO.addAuth(authData);

        return authData;
    }

    //login a user with associated username and password
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

    //logout a user by deleting associated AuthID
    public  void logout(String authId) throws Unauthorized {
        //positive case
        try {
            authDAO.getAuth(authId);
        }
        //negative case
        catch (Unauthorized e) {
            throw new Unauthorized(e.getMessage());
        }

        authDAO.deleteAuth(authId);
    }


//retrieves an AuthId
    public AuthData getAuth(String authId) throws Unauthorized {
        //positive case
        try{
            return authDAO.getAuth(authId);
        }
        //negative case
        catch (Unauthorized e) {
            throw new Unauthorized(e.getMessage());
        }
    }
    //clears db
    public void clear(){
        userDAO.clear();
        authDAO.clear();
    }


}
