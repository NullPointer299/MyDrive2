package model.dto.tree;

import model.dao.DAOFiles;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TreeFactory {

    public static MainTree createMainTree(final ResultSet result) throws SQLException {
        result.beforeFirst();
        return new MainTree(result);
    }

    public static SubTree createSubTree(final ResultSet result) throws SQLException {
        result.beforeFirst();
        return new SubTree(result);
    }

    public static TreeManagement createTreeManagement(final int userId) throws SQLException {
        return DAOFiles.getDirectoryTree(userId);
    }
}
