package controller.check;

import attribute.AttrServlet;
import controller.wrapper.AsynchronousHttpServlet;
import model.dto.check.UserId;
import model.dto.check.UserIdFactory;
import model.dto.response.JsonFactory;
import model.util.check.Check;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletCheckID", urlPatterns = "/CheckID/")
public class ServletCheckID extends AsynchronousHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletCheckID!!!");
        setCharacterEncodingUtf8(request);
        try {
            final UserId.Encoded userIdEncoded =
                    UserIdFactory.deserialize(receiveJsonRequest(request));
            final UserId userId = userIdEncoded.toParent();
            final String jsonResponse =
                    JsonFactory.createJsonResponse(
                            Check.isValidID(userId)).toJson();
            sendJsonResponse(response, jsonResponse);
        } catch (final SQLException e) {
            // 503?
            e.printStackTrace();
        }
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletCheckID!!!");
        setCharacterEncodingUtf8(request);
        sendRedirect(response, AttrServlet.LOGIN);
    }
}
