package model.dto.mail;

import model.dto.Convertible;
import model.dto.Encodable;

public class Mail {

    private final String emailAddress;
    private final String lastName;
    private final String firstName;

    private final Encoded encoded;

    Mail(final String emailAddress, final String lastName, final String firstName) {
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

    public class Encoded extends Encodable implements Convertible<Mail> {

        private final String emailAddress;
        private final String lastName;
        private final String firstName;

        Encoded(final String emailAddress, final String lastName, final String firstName) {
            this.emailAddress = encode(emailAddress);
            this.lastName = encode(lastName);
            this.firstName = encode(firstName);
        }

        @Override
        public Mail toParent() {
            return new Mail(emailAddress, lastName, firstName);
        }
    }
}
