package controller.check;

import model.dto.check.UserID;
import model.dto.check.UserIDFactory;
import model.dto.response.JsonFactory;
import model.util.check.Check;
import model.wrap.AjaxHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletCheckID", urlPatterns = "/CheckID/")
public class ServletCheckID extends AjaxHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletCheckID!!!");
        try {
            initialServlet(request);
            final UserID userID =
                    UserIDFactory.createCheckID(receiveJsonRequest(request));
            final String jsonResponse =
                    JsonFactory.createRequestResult(
                            Check.isValidID(userID)).toJson();
            sendJsonResponse(response, jsonResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletCheckID!!!");
        throw new IllegalStateException("Not implemented!!!");
    }
}
