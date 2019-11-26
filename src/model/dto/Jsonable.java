package model.dto;

import com.google.gson.Gson;
import controller.util.ServletUtil;

@SuppressWarnings("SpellCheckingInspection")
public interface Jsonable<T extends Encodable> {

    Gson GSON = ServletUtil.getGSON();

    default String toJson(final T encoded) {
        return GSON.toJson(encoded);
    }

    String toJson();
}
