package model.dto.tree;

import model.dto.file.FileFactory;
import model.dto.file.MainFile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

class MainTree {

    private final Map<Integer, List<MainFile>> mainTree;

    MainTree(final ResultSet result) throws SQLException {
        final Map<Integer, List<MainFile>> mainTree = new HashMap<>();
        while (result.next()) {
            final MainFile file = createFile(result);
            if (file.getId() == 1)
                continue;
            final List<MainFile> files = mainTree.putIfAbsent(
                    file.getParentId(), new ArrayList<>(Collections.singletonList(file)));
            if (files != null)
                files.add(file);
        }
        this.mainTree = mainTree;
    }

    private MainFile createFile(final ResultSet result) throws SQLException {
        final int id = result.getInt("id");
        final String name = result.getString("name");
        final int parentID = result.getInt("parent_id");
        final int size = result.getInt("size");
        final boolean isDirectory = result.getBoolean("is_directory");
        final boolean openness = result.getBoolean("openness");
        final LocalDateTime createdAt = result.getTimestamp("created_at").toLocalDateTime();
        final LocalDateTime modifiedAt = result.getTimestamp("modified_at").toLocalDateTime();
        final int thumbnailID = result.getInt("thumbnail_id");
        final String ownerUserName = result.getString("owner_user_name");
        return FileFactory.createMainFile(id, name, parentID, size, isDirectory, openness,
                createdAt, modifiedAt, thumbnailID, ownerUserName);
    }
}
