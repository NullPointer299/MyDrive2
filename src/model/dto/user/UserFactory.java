package model.dto.user;

import com.google.gson.Gson;
import controller.util.ServletUtil;

public class UserFactory {

    private final static Gson GSON = ServletUtil.getGSON();

    public static User create(final int id, final String userId, final String firstName, final String lastName,
                              final String nickname, final String emailAddress, final boolean openness) {
        return new User(id, userId, firstName, lastName, nickname, emailAddress, openness);
    }

    public static User create(final String json) {
        return GSON.fromJson(json, User.class);
    }
}
