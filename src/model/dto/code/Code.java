package model.dto.code;

import model.dto.Convertible;
import model.dto.Encodable;

import java.util.Objects;

public class Code {

    private final String code;

    private final Encoded encoded;

    Code(final String code) {
        this.code = code;
        this.encoded = new Encoded(this);
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Code code = (Code) obj;
        return Objects.equals(this.code, code.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, encoded);
    }

    @Override
    public String toString() {
        return new StringBuilder(code)
                .insert(1, ' ')
                .insert(3, ' ')
                .insert(5, ' ')
                .toString();
    }

    public class Encoded extends Encodable implements Convertible<Code> {

        private final String code;

        Encoded(final Code code) {
            this.code = encode(code.getCode());
        }

        @Override
        public Code toParent() {
            return new Code(code);
        }
    }
}
