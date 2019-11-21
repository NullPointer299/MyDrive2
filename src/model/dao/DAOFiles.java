package model.dao;

import model.dto.tree.TreeFactory;
import model.dto.tree.TreeManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOFiles {

    private static final DAOMyDrive2 DAO_MYDRIVE2 = DAOMyDrive2.DAO_MYDRIVE2;

    public static TreeManagement getDirectoryTree(final int userId) throws SQLException {
        final String sql = String.format(
                "SELECT files.id AS id, " +
                        "files.name AS name," +
                        "files.parent_id AS parent_id," +
                        "files.size AS size," +
                        "files.is_directory AS is_directory," +
                        "files.openness AS openness," +
                        "files.created_at AS created_at," +
                        "files.modified_at AS modified_at," +
                        "files.thumbnail_id AS thumbnail_id," +
                        "concat(users.last_name, ' ', users.first_name) AS owner_user_name" +
                        " FROM files" +
                        " INNER JOIN users ON files.owner_user_id = users.id" +
                        " WHERE files.owner_user_id = %d OR" +
                        " files.owner_user_id = 1 AND" +
                        " files.name = 'root' OR" +
                        " files.name = (SELECT user_id FROM users WHERE id = %d)",
                userId, userId);
        try (final Connection connection = DAO_MYDRIVE2.getDataSource().getConnection()) {
            final ResultSet result = connection.createStatement().executeQuery(sql);
            return new TreeManagement(TreeFactory.createMainTree(result), TreeFactory.createSubTree(result));
        }
    }

    public static void createFileEntry(final String name, final int parentId, final int size, final boolean isDirectory, final boolean openness, final int ownerUserId) throws SQLException {
        // thumbnail_idの扱いを決める
        final String sql = String.format(
                "INSERT INTO files(name, parent_id, size, is_directory, openness, thumbnail_id, owner_user_id)" +
                        "VALUES ('%s', %d, %d, %s, %s, %d, %d)",
                name, parentId, size, isDirectory, openness, 1, ownerUserId);
        // TODO degug code here.
        System.out.println(sql);
        try (final Connection connection = DAO_MYDRIVE2.getDataSource().getConnection()) {
            connection.createStatement().executeUpdate(sql);
        }
    }
}
