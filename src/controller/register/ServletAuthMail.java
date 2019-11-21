package controller.register;

import model.dto.code.Code;
import model.dto.code.CodeFactory;
import model.dto.mail.Mail;
import model.dto.mail.MailFactory;
import model.dto.response.JsonFactory;
import model.util.check.Check;
import model.util.mail.MailSender;
import model.util.servlet.ServletUtil;
import model.wrap.AjaxHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletAuthMail", urlPatterns = "/AuthMail/")
public class ServletAuthMail extends AjaxHttpServlet {

    // リダイレクトするため、jspとして呼び出す
    private final String SERVLET_LOGIN = ServletUtil.getSERVLET_LOGIN(true);

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletAuthMail!!!");
        try {
            initialServlet(request);
            final Mail mail =
                    MailFactory.createFromJson(receiveJsonRequest(request));
            String jsonResponse;
            if (Check.isValidMail(mail)) {
                System.out.println("Valid mail!");
                final String title = "認証コードのお知らせ";
                final Code code = CodeFactory.create();
                final String text = mail.getLastName() + " " + mail.getFirstName() + "さん\n\n" +
                        "あなたの認証コードは\n\n" +
                        code + "\n\n" +
                        "です";
                final String toAddress = mail.getEmailAddress();
                final boolean isSuccess = MailSender.send(title, text, toAddress);
                if (isSuccess)
                    // 送信成功したら
                    request.getSession().setAttribute("CODE", code);
                jsonResponse = JsonFactory.createRequestResult(isSuccess).toJson();
            } else {
                System.out.println("Invalid mail!");
                jsonResponse = JsonFactory.createRequestResult(false).toJson();
            }
            sendJsonResponse(response, jsonResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletAuthMail!!!");
        response.sendRedirect(SERVLET_LOGIN);
    }
}
