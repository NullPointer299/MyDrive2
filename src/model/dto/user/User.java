package model.dto.user;

import model.dto.Encodable;
import model.dto.Jsonable;

public class User implements Jsonable<User.Encoded> {

    private final int id;
    private final String userId;
    private final String firstName;
    private final String lastName;
    private final String nickname;
    private final String emailAddress;
    private final boolean openness;

    private final Encoded encoded;

    User(final int id, final String userId, final String firstName, final String lastName,
         final String nickname, final String emailAddress, final boolean openness) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.emailAddress = emailAddress;
        this.openness = openness;

        this.encoded = new Encoded(this);
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean isOpenness() {
        return openness;
    }

    @Override
    public String toJson() {
        return toJson(encoded);
    }

    public class Encoded extends Encodable {

        private final String userId;
        private final String firstName;
        private final String lastName;
        private final String nickname;
        private final String emailAddress;
        private final String openness;

        Encoded(final User user) {
            this.userId = encode(user.userId);
            this.firstName = encode(user.firstName);
            this.lastName = encode(user.lastName);
            this.nickname = encode(user.nickname);
            this.emailAddress = encode(user.emailAddress);
            this.openness = String.valueOf(user.openness);
        }
    }
}
