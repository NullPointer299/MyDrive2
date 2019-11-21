package model.dto.question;

import model.dao.DAOUsers;

import java.sql.SQLException;

public class QuestionsFactory {

    public static Questions create() throws SQLException {
        return new Questions(DAOUsers.getQuestions());
    }
}
