package model.dto.file;

import java.time.LocalDateTime;

public class FileFactory {

    public static MainFile createMainFile(final int id, final String name, final int parentID,
                                          final int size, final boolean isDirectory, final boolean openness,
                                          final LocalDateTime createdAt, final LocalDateTime modifiedAt,
                                          final int thumbnailPath, final String ownerUserName) {
        return new MainFile(
                id, name, parentID, size, isDirectory, openness,
                createdAt, modifiedAt, thumbnailPath, ownerUserName);
    }

    public static SubFile createSubFile(final int id, final int parentID, final String name, final boolean isDirectory) {
        return new SubFile(id, parentID, name, isDirectory);
    }
}
