package controller.register;

import model.dao.DAOFiles;
import model.dao.DAOUsers;
import model.util.servlet.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet(name = "ServletRegister", urlPatterns = "/Register/")
public class ServletRegister extends HttpServlet {

    private static final String REGISTER_JSP = ServletUtil.getJSP_REGISTER();
    private static final String SERVLET_MAIN = ServletUtil.getSERVLET_MAIN(false);
    // リダイレクトするからtrue
    private static final String SERVLET_LOGIN = ServletUtil.getSERVLET_LOGIN(true);

    private static final String COOKIE_LOGIN = ServletUtil.getCOOKIE_LOGIN();

    private final String UTF_8 = ServletUtil.getUTF_8();

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) {
        System.out.println("[POST]ServletRegister!!!");
        try {
            request.setCharacterEncoding(UTF_8);
            request.getSession().invalidate();
            final Cookie login =
                    ServletUtil.findCookieOrNull(request.getCookies(), COOKIE_LOGIN);
            if (login != null)
                login.setMaxAge(0);
            // TODO tokenとか使って正しい手順で作成してるかチェックすべき？
            final String emailAddress = request.getParameter("email-address");
            final String lastName = request.getParameter("last-name");
            final String firstName = request.getParameter("first-name");
            final String userId = request.getParameter("user-id");
            final String password = request.getParameter("password");
            final int question = Integer.parseInt(request.getParameter("question"));
            final String answer = request.getParameter("answer");
            final boolean openness = Boolean.parseBoolean(request.getParameter("openness"));

            System.out.println(emailAddress);
            System.out.println(lastName);
            System.out.println(firstName);
            System.out.println(userId);
            System.out.println(password);
            System.out.println(question);
            System.out.println(answer);
            System.out.println(openness);

            DAOUsers.registerUser(userId, firstName, lastName,
                    emailAddress, openness, password, question, answer);

            DAOFiles.createFileEntry(userId, 1, 0, true, true, 1);

            response.sendRedirect(SERVLET_LOGIN);
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletRegister!!!");
        request.setCharacterEncoding(UTF_8);
        request.getRequestDispatcher(REGISTER_JSP).forward(request, response);
    }
}
