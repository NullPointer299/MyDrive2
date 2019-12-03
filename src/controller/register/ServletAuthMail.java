package controller.register;

import attribute.AttrServlet;
import controller.wrapper.AsynchronousHttpServlet;
import model.dto.code.Code;
import model.dto.code.CodeFactory;
import model.dto.mail.Mail;
import model.dto.mail.MailFactory;
import model.dto.response.JsonFactory;
import model.dto.response.JsonResponse;
import model.dto.token.Token;
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
                    final Token token=TokenFactory.createToken();
                    final String title = "認証コードのお知らせ";
                    final Code code = CodeFactory.create();
                    System.out.println("generate code!!! : " + code); // TODO debug code here.
                    final String text = mail.getLastName() + " " + mail.getFirstName() + "さん\n\n" +
                            "あなたの認証コードは\n" +
                            code + "\n" +
                            "です\n\n" +
                            "*** 注意 ***\n" +
                            "同一のブラウザからのアクセスのみ有効です\n" +
                            "************\n\n" +
                            "このメールに心当たりがない場合は削除してください。";
                    final String toAddress = mail.getEmailAddress();
                    if (MailSender.send(title, text, toAddress)) {
                        session.setAttribute("CODE", code);
                        session.setAttribute("TOKEN", token);
                        jsonResponse = JsonFactory.createJsonResponse(true);
                        jsonResponse.getEncoded().setToken(token);
                    } else {
                        System.out.println("Failed send mail!"); // TODO debug code here.
                        jsonResponse = JsonFactory.createJsonResponse(false, "Failed to send Email!");
                    }
                } else {
                    System.out.println("Invalid mail!"); // TODO debug code here.
                    jsonResponse = JsonFactory.createJsonResponse(false, "Already registered address!");
                }
                sendJsonResponse(response, jsonResponse.toJson());
            } else {
                sendRedirect(response, AttrServlet.LOGIN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletAuthMail!!!");
        setCharacterEncodingUtf8(request);
        sendRedirect(response, AttrServlet.LOGIN);
    }
}
