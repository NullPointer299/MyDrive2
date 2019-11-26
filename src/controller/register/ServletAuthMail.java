package controller.register;

import model.dto.code.Code;
import model.dto.code.CodeFactory;
import model.dto.mail.Mail;
import model.dto.mail.MailFactory;
import model.dto.response.JsonFactory;
import model.util.check.Check;
import model.util.mail.MailSender;
import controller.wrapper.AsynchronousHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletAuthMail", urlPatterns = "/AuthMail/")
public class ServletAuthMail extends AsynchronousHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletAuthMail!!!");
        try {
            final String jsonRequest = receiveJsonRequest(request);
            if ("".equals(jsonRequest))
                notLoggedInIfLogin(request, response);
            final Mail mail =
                    MailFactory.createMail(jsonRequest);
            String jsonResponse;
            if (Check.isValidMail(mail)) {
                System.out.println("Valid mail!");  // TODO debug code here.
                jsonResponse = JsonFactory.createRequestResult(true).toJson();
                final String title = "認証コードのお知らせ";
                final Code code = CodeFactory.create();
                final String text = mail.getLastName() + " " + mail.getFirstName() + "さん\n\n" +
                        "あなたの認証コードは\n\n" +
                        code + "\n\n" +
                        "です";
                final String toAddress = mail.getEmailAddress();
                MailSender.send(title, text, toAddress);
                request.getSession().setAttribute("CODE", code);
            } else {
                System.out.println("Invalid mail!"); // TODO debug code here.
                jsonResponse = JsonFactory.createRequestResult(false).toJson();
            }
            sendJsonResponse(response, jsonResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletAuthMail!!!");
        notLoggedInIfLogin(request, response);
    }
}
