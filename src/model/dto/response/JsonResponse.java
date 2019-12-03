package model.dto.response;

import model.dto.Encodable;
import model.dto.Jsonable;
import model.dto.token.Token;

public class JsonResponse implements Jsonable<JsonResponse.Encoded> {

    private final boolean _valid;
    private final String message;

    private final Encoded encoded;

    JsonResponse(final boolean _valid) {
        this._valid = _valid;
        this.message = null;
        this.encoded = new Encoded(this);
    }

    JsonResponse(final boolean _valid, final String message) {
        this._valid = _valid;
        this.message = message;
        this.encoded = new Encoded(this);
    }

    public Encoded getEncoded() {
        return encoded;
    }

    @Override
    public String toJson() {
        return toJson(encoded);
    }

    // 送信専用のため、Convertibleは実装しない
    public class Encoded extends Encodable {

        private final boolean _valid;
        private final String message;

        Encoded(final JsonResponse requestResult) {
            this._valid = requestResult._valid;
            this.message = encode(requestResult.message);
        }

        @Override
        public void setToken(Token token) {
            super.setToken(token);
        }
    }
}
