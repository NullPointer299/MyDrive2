package model.dto.file;

public class UploadFile {

    private final int parentId;
    private final String name;
    private final boolean isDirectory;

    UploadFile(final int parentId, final String name, final boolean isDirectory) {
        this.parentId = parentId;
        this.name = name;
        this.isDirectory = isDirectory;
    }

    public int getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public boolean isDirectory() {
        return isDirectory;
    }
}
