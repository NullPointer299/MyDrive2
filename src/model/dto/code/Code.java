package model.dto.code;

import model.dto.Encodable;
import model.dto.Jsonable;


public class Code implements Jsonable<Code.Encoded> {

    private final String code;

    private final Encoded encoded;

    Code(String code) {
        this.code = code;
        this.encoded = new Encoded(code);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toJson() {
        return toJson(encoded);
    }

    @Override
    public boolean equals(Object o) {
        if (!o.getClass().equals(Code.class))
            return false;
        return ((Code) o).getCode().equals(code);
    }

    @Override
    public String toString() {
        return new StringBuilder(code)
                .insert(1, ' ')
                .insert(3, ' ')
                .insert(5, ' ')
                .toString();
    }

    public class Encoded implements Encodable {

        private final String code;

        Encoded(final String code) {
            this.code = encode(code);
        }
    }
}
