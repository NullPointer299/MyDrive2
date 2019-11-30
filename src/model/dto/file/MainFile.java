package model.dto.file;

import java.time.LocalDateTime;

public class MainFile {
    private final int id;
    private final String name;
    private final int parentId;
    private final int size;
    private final boolean isDirectory;
    private final boolean openness;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;
    private final int thumbnailPath;
    private final String ownerUserName;

    MainFile(final int id, final String name, final int parentId, final int size, final boolean isDirectory, boolean openness,
             final LocalDateTime createAt, final LocalDateTime modifiedAt, final int thumbnailPath, final String ownerUserName) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.size = size;
        this.isDirectory = isDirectory;
        this.openness = openness;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
        this.thumbnailPath = thumbnailPath;
        this.ownerUserName = ownerUserName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getParentId() {
        return parentId;
    }

    public int getSize() {
        return size;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public boolean isOpenness() {
        return openness;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public int getThumbnailPath() {
        return thumbnailPath;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }
}

