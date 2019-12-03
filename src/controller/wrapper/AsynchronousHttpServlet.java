package controller.wrapper;

import model.dto.Encodable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

public class AsynchronousHttpServlet extends AbstractHttpServletWrapper {

    protected String receiveJsonRequest(final HttpServletRequest request) throws IOException {
        final BufferedReader reader = new BufferedReader(request.getReader());
        final String jsonRequest = URLDecoder.decode(reader.readLine(), "UTF-8");
        System.out.println("request = " + jsonRequest); //TODO debug code here.
        return jsonRequest;
    }

    protected void sendJsonResponse(final HttpServletResponse response, final String jsonResponse) throws IOException {
        final PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        System.out.println("response = " + jsonResponse); //TODO debug code here.
        out.print(jsonResponse);
        out.flush();
    }

    protected boolean isValidToken(final HttpSession session, final Encodable encoded) {
        if(encoded.getToken().equals(session.getAttribute("TOKEN"))) {
            System.out.println("Valid Token!");
            return true;
        }else {
            System.out.println("Invalid Token!");
            return false;
        }
    }
}
