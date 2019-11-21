package model.dto.mail;

import model.dto.Encodable;
import model.dto.Jsonable;

import java.io.UnsupportedEncodingException;

public class Mail implements Jsonable<Mail.Encoded> {

    private final String emailAddress;
    private final String lastName;
    private final String firstName;

    private final Encoded encoded;

    Mail(String emailAddress, String lastName, String firstName) throws UnsupportedEncodingException {
        this.emailAddress = emailAddress;
        this.lastName = lastName;
        this.firstName = firstName;
        this.encoded = new Encoded(emailAddress, lastName, firstName);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toJson() {
        return toJson(encoded);
    }

    public class Encoded implements Encodable {

        private final String emailAddress;
        private final String lastName;
        private final String firstName;

        Encoded(String emailAddress, String lastName, String firstName) throws UnsupportedEncodingException {
            this.emailAddress = encode(emailAddress);
            this.lastName = encode(lastName);
            this.firstName = encode(firstName);
        }
    }
}
