package model.dto.mail;

import com.google.gson.Gson;
import model.util.servlet.ServletUtil;

public class MailFactory {

    private static final Gson GSON = ServletUtil.getGSON();

    public static Mail createFromJson(final String json) {
        return GSON.fromJson(json, Mail.class);
    }
}
