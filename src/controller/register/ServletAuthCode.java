package controller.register;

import model.dto.code.Code;
import model.dto.code.CodeFactory;
import model.dto.response.JsonFactory;
import model.wrap.AjaxHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletAuthCode", urlPatterns = "/AuthCode/")
public class ServletAuthCode extends AjaxHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletAuthCode!!!");
        initialServlet(request);
        final String jsonRequest = receiveJsonRequest(request);
        final Code code = CodeFactory.createFromJson(jsonRequest);
        final boolean status =
                code.equals((request.getSession().getAttribute("CODE")));
        final String jsonResponse = JsonFactory.createRequestResult(status).toJson();
        sendJsonResponse(response, jsonResponse);
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletAuthCode!!!");
        throw new IllegalStateException("Not implemented!");
    }
}
