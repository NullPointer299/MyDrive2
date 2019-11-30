package model.dto.mail;

import com.google.gson.Gson;
import controller.util.ServletUtil;

public class MailFactory {

    private static final Gson GSON = ServletUtil.GSON;

    public static Mail.Encoded deserialize(final String json) {
        return GSON.fromJson(json, Mail.Encoded.class);
    }
}
