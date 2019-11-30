package model.dto.token;

import com.google.gson.Gson;
import controller.util.ServletUtil;

public class TokenFactory {

    private static final Gson GSON = ServletUtil.GSON;

    public static Token createToken() {
        return new Token();
    }

    public static Token createToken(final String token) {
        return new Token(token);
    }

    public static Token.Encoded deserialize(final String json) {
        return GSON.fromJson(json, Token.Encoded.class);
    }

    static String generateToken() {
        // TODO token実装する
        return null;
    }

}
