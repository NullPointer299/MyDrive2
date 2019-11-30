package model.dto.token;

import model.dto.Encodable;

import java.util.Objects;

public class Token {

    final String tokenString;

    final Encoded encoded;

    Token() {
        this.tokenString = TokenFactory.generateToken();
        this.encoded = new Encoded(this);
    }

    Token(final String tokenString) {
        this.tokenString = tokenString;
        this.encoded = null;
    }

    public String getTokenString() {
        return tokenString;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final Token token = (Token) obj;
        return Objects.equals(this.tokenString, token.tokenString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenString);
    }

    public class Encoded extends Encodable {

        Encoded(final Token token) {
            setToken(token);
        }
    }
}
