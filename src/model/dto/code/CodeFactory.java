package model.dto.code;

import com.google.gson.Gson;
import controller.util.ServletUtil;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.IntStream;

public class CodeFactory {

    private static final Gson GSON = ServletUtil.GSON;

    private static final Random random = new SecureRandom();

    public static Code create() {
        return new Code(generateAuthCode());
    }

    public static Code.Encoded deserialize(final String json) {
        return GSON.fromJson(json, Code.Encoded.class);
    }

    private static String generateAuthCode() {
        final StringBuilder authCode = new StringBuilder();
        IntStream.generate(
                () -> random.nextInt(10))
                .limit(4)
                .forEach(authCode::append);
        return authCode.toString();
    }
}
