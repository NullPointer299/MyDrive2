package model.dto.file;

import model.dto.Encodable;

// 送信専用だからJsonableは実装しない
public class SubFile {

    private final int id;
    private final int parentId;
    private final String name;
    private final boolean isDirectory;

    private final Encoded encoded;

    SubFile(final int id, final int parentId, final String name, final boolean isDirectory) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.isDirectory = isDirectory;

        this.encoded = new Encoded(this);
    }

    public int getId() {
        return id;
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

    public Encoded getEncoded() {
        return encoded;
    }

    // 送信専用のため、Convertibleは実装しない
    // SubTreeを通して通信するため、Tokenは実装しない
    public class Encoded extends Encodable {

        final String id;
        final String parentId;
        final String name;
        final String isDirectory;

        Encoded(final SubFile treeFile) {
            this.id = encode(treeFile.id);
            this.parentId = encode(treeFile.parentId);
            this.name = encode(treeFile.name);
            this.isDirectory = String.valueOf(treeFile.isDirectory);
        }

        @Override
        public String toString() {
            return id + " " + parentId + " " + name + " " + isDirectory;
        }
    }
}
