package model.dto.check;

import com.google.gson.Gson;
import model.util.servlet.ServletUtil;

public class UserIDFactory {

    private static final Gson GSON = ServletUtil.getGSON();

    public static UserID createCheckID(final String json) {
        return GSON.fromJson(json, UserID.class);
    }
}
