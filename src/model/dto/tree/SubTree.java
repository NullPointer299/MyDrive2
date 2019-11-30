package model.dto.tree;

import com.google.gson.reflect.TypeToken;
import model.dto.Encodable;
import model.dto.Jsonable;
import model.dto.file.FileFactory;
import model.dto.file.SubFile;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class SubTree implements Jsonable<SubTree.Encoded> {

    private final Map<Integer, List<SubFile>> subTree;

    private final Encoded encoded;

    public SubTree(final ResultSet result) throws SQLException {
        final Map<Integer, List<SubFile>> subTree = new HashMap<>();
        while (result.next()) {
            final SubFile file = createFile(result);
            if (file.getId() == 1)
                continue;
            final List<SubFile> files = subTree.putIfAbsent(
                    file.getParentId(), new ArrayList<>(Collections.singletonList(file)));
            if (files != null)
                files.add(file);
        }
        this.subTree = subTree;
        this.encoded = new Encoded(this);
    }

    @Override
    public String toJson() {
        return toJson(this.encoded);
    }

    @Override
    public String toJson(final Encoded encoded) {
        final Type type = new TypeToken<Map<Integer, List<SubFile.Encoded>>>() {
        }.getType();
        return GSON.toJson(encoded.subTree, type);
    }

    private SubFile createFile(final ResultSet result) throws SQLException {
        final int id = result.getInt("id");
        final int parentID = result.getInt("parent_id");
        final String name = result.getString("name");
        final boolean isDirectory = result.getBoolean("is_directory");
        return FileFactory.createSubFile(id, parentID, name, isDirectory);
    }

    // 送信専用のため、Convertibleは実装しない
    class Encoded extends Encodable {

        private final Map<Integer, List<SubFile.Encoded>> subTree;

        Encoded(final SubTree subTree) {

            final Map<Integer, List<SubFile.Encoded>> tree = new HashMap<>();
            subTree.subTree.forEach((key, files) ->
                    tree.put(key, files.stream().map(SubFile::getEncoded).collect(Collectors.toList())));
            this.subTree = tree;

            this.subTree.forEach((key, value) -> System.out.println(key + " " + value)); // TODO debug code here.
        }
    }
}
