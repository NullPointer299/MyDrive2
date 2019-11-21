package model.wrap;

import model.util.servlet.ServletUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class AjaxHttpServlet extends HttpServlet {

    private static final String UTF_8 = ServletUtil.getUTF_8();

    protected void initialServlet(final HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding(UTF_8);
        // more initial code
    }

    protected String receiveJsonRequest(final HttpServletRequest request) throws IOException {
        final BufferedReader reader = new BufferedReader(request.getReader());
        final String jsonRequest = URLDecoder.decode(reader.readLine(), UTF_8);
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
}
