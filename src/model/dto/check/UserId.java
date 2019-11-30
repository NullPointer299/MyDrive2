package model.dto.check;

import model.dto.Convertible;
import model.dto.Encodable;

// 受信専用だからJsonableは実装しない
public class UserId {
    private final String userId;

    UserId(final String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public class Encoded extends Encodable implements Convertible<UserId> {

        private final String userId;

        Encoded(final String userId) {
            this.userId = userId;
        }

        @Override
        public UserId toParent() {
            return new UserId(userId);
        }
    }
}
