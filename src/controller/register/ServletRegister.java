package controller.register;

import attribute.AttrCookie;
import attribute.AttrJsp;
import attribute.AttrServlet;
import controller.util.ServletUtil;
import controller.wrapper.SynchronousHttpServlet;
import model.dao.DAOFiles;
import model.dao.DAOUsers;
import model.dto.cookie.CookieFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet(name = "ServletRegister", urlPatterns = "/Register/")
public class ServletRegister extends SynchronousHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) {
        System.out.println("[POST]ServletRegister!!!");
        try {
            setCharacterEncodingUtf8(request);
            request.getSession().invalidate();
            CookieFactory.removeCookie(request, response, AttrCookie.LOGIN);
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

            ServletUtil.nullCheck(
                    emailAddress, lastName, firstName,
                    userId, password, question, answer, openness);

            DAOUsers.registerUser(userId, firstName, lastName,
                    emailAddress, openness, password, question, answer);

            DAOFiles.createFileEntry(userId, 1, 0, true, true, 1);

            // リダイレクトのためtrue
            response.sendRedirect(AttrServlet.LOGIN.getUrl(true));
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
        request.getRequestDispatcher(AttrJsp.REGISTER.getUrl()).forward(request, response);
    }
}
