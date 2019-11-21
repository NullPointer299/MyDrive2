package model.util.login;

import model.dao.DAOUsers;
import model.dto.user.User;

import java.sql.SQLException;

public class Login {

    private final User user;
    private final boolean isSuccess;

    public Login(final String userId, final String password) throws SQLException {
        user = DAOUsers.login(userId, password);
        isSuccess = user != null;
    }

    public User getUser() {
        if (isSuccess) {
            return user;
        } else {
            throw new IllegalStateException("Login failed!");
        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
