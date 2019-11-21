package model.dto.check;

import java.io.UnsupportedEncodingException;

public class UserID {
    private final String userId;

    public UserID(final String userId) throws UnsupportedEncodingException {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
