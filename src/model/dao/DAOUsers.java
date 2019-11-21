package model.dao;

import model.dto.check.UserID;
import model.dto.mail.Mail;
import model.dto.user.User;
import model.dto.user.UserFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SqlResolve")
public class DAOUsers {

    private final static DAOMyDrive2 DAO_MYDRIVE2 = DAOMyDrive2.DAO_MYDRIVE2;

    public static boolean isExistUserID(final UserID userID) throws SQLException {
        final String sql = String.format(
                "SELECT id" +
                        " FROM users" +
                        " WHERE user_id = '%s'", userID.getUserId());
        try (final Connection connection = DAO_MYDRIVE2.getDataSource().getConnection()) {
            return connection.createStatement().executeQuery(sql).first();
        }
    }

    public static boolean isExistMailAddress(final Mail mail) throws SQLException {
        final String sql = String.format(
                "SELECT id" +
                        " FROM users" +
                        " WHERE email_address = '%s'", mail.getEmailAddress());
        try (final Connection connection = DAO_MYDRIVE2.getDataSource().getConnection()) {
            return connection.createStatement().executeQuery(sql).first();
        }
    }

    public static void registerUser(final String userId, final String firstName, final String lastName,
                                    final String emailAddress, final boolean openness, final String password,
                                    final int question, final String answer) throws SQLException {
        final String sql = String.format(
                "INSERT INTO users (user_id, first_name, last_name, email_address, openness, password, secret_question_id, secret_answer)" +
                        " VALUES ('%s', '%s', '%s', '%s', %s, '%s', %d, '%s')", userId, firstName, lastName, emailAddress, openness, password, question, answer);
        System.out.println(sql);
        try (final Connection connection = DAO_MYDRIVE2.getDataSource().getConnection()) {
            connection.createStatement().executeUpdate(sql);
        }
    }

    public static User login(final String userId, final String password) throws SQLException {
        final String sql = String.format(
                "SELECT id, first_name, last_name, nickname, email_address, openness, password" +
                        " FROM users" +
                        " WHERE user_id = '%s' AND password = '%s'", userId, password);
        try (final Connection connection = DAO_MYDRIVE2.getDataSource().getConnection()) {
            final ResultSet result = connection.createStatement().executeQuery(sql);
            return result.first() ?
                    UserFactory.create(
                            result.getInt("id"),
                            userId,
                            result.getString("first_name"),
                            result.getString("last_name"),
                            result.getString("nickname"),
                            result.getString("email_address"),
                            result.getBoolean("openness")) :
                    null;
        }
    }

    public static String[] getQuestions() throws SQLException {
        final String sql =
                "SELECT question" +
                        " FROM secret_questions";
        try (final Connection connection = DAO_MYDRIVE2.getDataSource().getConnection()) {
            final ResultSet result = connection.createStatement().executeQuery(sql);
            final List<String> questions = new ArrayList<>();
            while (result.next()) {
                questions.add(result.getString("question"));
            }
            return questions.toArray(new String[0]);
        }
    }
}
