package model.dto.mail;

import com.google.gson.Gson;
import controller.util.ServletUtil;

public class MailFactory {

    private static final Gson GSON = ServletUtil.getGSON();

    public static Mail createMail(final String json) {
        return GSON.fromJson(json, Mail.class);
    }
}
