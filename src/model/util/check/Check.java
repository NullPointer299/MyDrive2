package model.util.check;

import model.dao.DAOUsers;
import model.dto.check.UserId;
import model.dto.mail.Mail;

import java.sql.SQLException;

public class Check {

    public static boolean isValidID(final UserId userID) throws SQLException {
        return !DAOUsers.isExistUserID(userID);
    }

    public static boolean isValidMail(final Mail mail) throws SQLException {
        return !DAOUsers.isExistMailAddress(mail);
    }
}
