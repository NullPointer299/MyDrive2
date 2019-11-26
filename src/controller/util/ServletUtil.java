package controller.util;

import attribute.AttrCookie;
import com.google.gson.Gson;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Objects;

public class ServletUtil {

    private static final Gson GSON = new Gson();

    private ServletUtil() {
    }

    public static Gson getGSON() {
        return GSON;
    }

    public static boolean nullCheck(final Object... args) {
        return Arrays.stream(args).noneMatch(Objects::isNull);
    }

}
