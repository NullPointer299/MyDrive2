package controller.util;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Objects;

public class ServletUtil {

    public static final Gson GSON = new Gson();

    private ServletUtil() {
    }

    public static boolean nullCheck(final Object... args) {
        return Arrays.stream(args).noneMatch(Objects::isNull);
    }

}
