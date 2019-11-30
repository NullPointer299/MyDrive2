package model.dto.response;

public class JsonFactory {

    public static JsonResponse createJsonResponse(final boolean status) {
        return new JsonResponse(status);
    }

    public static JsonResponse createJsonResponse(final boolean status, final String message) {
        return new JsonResponse(status, message);
    }
}
