package controller.register;

import controller.wrapper.AsynchronousHttpServlet;
import model.dto.code.Code;
import model.dto.code.CodeFactory;
import model.dto.mail.Mail;
import model.dto.mail.MailFactory;
import model.dto.response.JsonFactory;
import model.dto.response.JsonResponse;
import model.dto.token.TokenFactory;
import model.util.check.Check;
import model.util.mail.MailSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletAuthMail", urlPatterns = "/AuthMail/")
public class ServletAuthMail extends AsynchronousHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletAuthMail!!!");
        try {
            final HttpSession session = request.getSession(true);
            final String jsonRequest = receiveJsonRequest(request);
            final Mail.Encoded mailEncoded =
                    MailFactory.deserialize(jsonRequest);
            if (isValidToken(session, mailEncoded)) {
                final Mail mail = mailEncoded.toParent();
                // メールが有効だと判定できた時点でレスポンスを返すべきかな〜
                JsonResponse jsonResponse;
                if (Check.isValidMail(mail)) {
                    System.out.println("Valid mail!");  // TODO debug code here.
                    jsonResponse = JsonFactory.createJsonResponse(true);
                    jsonResponse.getEncoded().setToken(TokenFactory.createToken());
                    final String title = "認証コードのお知らせ";
                    final Code code = CodeFactory.create();
                    final String text = mail.getLastName() + " " + mail.getFirstName() + "さん\n\n" +
                            "あなたの認証コードは\n\n" +
                            code + "\n\n" +
                            "です";
                    final String toAddress = mail.getEmailAddress();
                    MailSender.send(title, text, toAddress);
                    session.setAttribute("CODE", code);
                } else {
                    System.out.println("Invalid mail!"); // TODO debug code here.
                    jsonResponse = JsonFactory.createJsonResponse(false);
                }
                sendJsonResponse(response, jsonResponse.toJson());
            } else {
                // tokenが無効だったとき
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletAuthMail!!!");
        notLoggedInIfLogin(request, response);
    }
}
