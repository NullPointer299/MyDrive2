package model.dto.response;

public class JsonFactory {

    public static JsonRequestResult createRequestResult(final boolean status) {
        return new JsonRequestResult(status);
    }

    public static JsonRequestResult createRequestResult(final boolean status, final String message) {
        return new JsonRequestResult(status, message);
    }
}
