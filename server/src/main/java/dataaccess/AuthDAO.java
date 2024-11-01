package dataaccess;

import model.AuthData;

public interface AuthDAO {

    AuthData addAuth(AuthData authData);

    AuthData getAuth(String authToken);

    void deleteAuth(String authToken);

    void updateAuth(AuthData authData);

    void clear();
}
