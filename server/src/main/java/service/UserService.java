package service;


import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;

import java.util.UUID;

public class UserService {

    UserDAO userDAO;
    AuthDAO authDAO;

    public UserService(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public AuthData register(UserData user) throws DataAccessException {
        userDAO.registerUser(user);
        String uuid = UUID.randomUUID().toString();
        AuthData authData = new AuthData(user.username(), uuid);
        authDAO.addAuth(authData);

        return authData;
    }
    public AuthData login(UserData user) throws DataAccessException {
        if(userDAO.authenticateUser(user.username(),user.password())) {
            String uuid = UUID.randomUUID().toString();
            AuthData authData = new AuthData(user.username(), uuid);
            authDAO.addAuth(authData);
            return authData;
        }

        return null;

    }
    public void logout(AuthData auth) {
        authDAO.getAuth(auth.authToken());
        authDAO.deleteAuth(auth.authToken());
    }

    public void clear() {
        userDAO.clear();
        authDAO.clear();
    }
}
