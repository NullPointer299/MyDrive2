package model.dto.response;

import model.dto.Encodable;
import model.dto.Jsonable;

public class JsonRequestResult implements Jsonable<JsonRequestResult.Encoded> {

    private final boolean _valid;
    private final String message;

    private final Encoded encoded;

    JsonRequestResult(final boolean _valid) {
        this._valid = _valid;
        this.message = null;
        this.encoded = new Encoded(this);
    }

    JsonRequestResult(final boolean _valid, final String message) {
        this._valid = _valid;
        this.message = message;
        this.encoded = new Encoded(this);
    }

    @Override
    public String toJson() {
        return toJson(encoded);
    }

    public class Encoded implements Encodable {

        private final String _valid;
        private final String message;

        Encoded(final JsonRequestResult requestResult) {
            this._valid = String.valueOf(requestResult._valid);
            this.message = encode(requestResult.message);
        }
    }
}
