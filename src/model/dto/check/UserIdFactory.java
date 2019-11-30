package model.dto.check;

import com.google.gson.Gson;
import controller.util.ServletUtil;

public class UserIdFactory {

    private static final Gson GSON = ServletUtil.GSON;

    public static UserId.Encoded deserialize(final String json) {
        return GSON.fromJson(json, UserId.Encoded.class);
    }
}
