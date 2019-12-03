package controller.register;

import attribute.AttrServlet;
import controller.wrapper.AsynchronousHttpServlet;
import model.dto.code.Code;
import model.dto.code.CodeFactory;
import model.dto.response.JsonFactory;
import model.dto.response.JsonResponse;
import model.dto.token.Token;
import model.dto.token.TokenFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletAuthCode", urlPatterns = "/AuthCode/")
public class ServletAuthCode extends AsynchronousHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletAuthCode!!!");
        setCharacterEncodingUtf8(request);
        final HttpSession session = request.getSession(true);
        final String jsonRequest = receiveJsonRequest(request);
        final Code.Encoded codeEncoded = CodeFactory.deserialize(jsonRequest);
        if (isValidToken(session, codeEncoded)) {
            final Token token=TokenFactory.createToken();
            final Code code = codeEncoded.toParent();
            final boolean status =
                    code.equals((session.getAttribute("CODE")));
            final JsonResponse jsonResponse = JsonFactory.createJsonResponse(status);
            jsonResponse.getEncoded().setToken(token);
            session.setAttribute("TOKEN", token);
            sendJsonResponse(response, jsonResponse.toJson());
        } else {
            sendRedirect(response, AttrServlet.LOGIN);
        }
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletAuthCode!!!");
        setCharacterEncodingUtf8(request);
        sendRedirect(response, AttrServlet.LOGIN);
    }
}
