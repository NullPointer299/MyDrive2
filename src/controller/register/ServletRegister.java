package controller.register;

import attribute.AttrCookie;
import attribute.AttrJsp;
import attribute.AttrServlet;
import controller.util.ServletUtil;
import controller.wrapper.SynchronousHttpServlet;
import model.cookie.CookieFactory;
import model.dao.DAOFiles;
import model.dao.DAOUsers;
import model.dto.token.TokenFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletRegister", urlPatterns = "/Register/")
public class ServletRegister extends SynchronousHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) {
        System.out.println("[POST]ServletRegister!!!");
        try {
            setCharacterEncodingUtf8(request);
            final HttpSession session = request.getSession(true);

            final String emailAddress = request.getParameter("email-address");
            final String lastName = request.getParameter("last-name");
            final String firstName = request.getParameter("first-name");
            final String userId = request.getParameter("user-id");
            final String password = request.getParameter("password");
            final String question = request.getParameter("question");
            final String answer = request.getParameter("answer");
            final String openness = request.getParameter("openness");
            final String token = request.getParameter("token");

            System.out.println(token);

            // TODO 値チェックする parseできない値とか
            if (ServletUtil.nullCheck(emailAddress, lastName, firstName,
                    userId, password, question, answer, openness) &&
                    session.getAttribute("TOKEN").equals(TokenFactory.createToken(token))) {
                System.out.println("RegisterUser!!!"); // TODO debug code here
                DAOUsers.registerUser(userId, firstName, lastName,
                        emailAddress, Boolean.parseBoolean(openness),
                        password, Integer.parseInt(question), answer);
                DAOFiles.createFileEntry(userId, 1, 0, true, true, 1);
                request.getSession().invalidate();
                CookieFactory.removeCookie(request, response, AttrCookie.LOGIN);
                response.sendRedirect(AttrServlet.LOGIN.getUrl(true));
            }else{
                // 無効なtokenか不正な引数
                System.out.println("Invalid Token or Illegal arguments!");
            }
        } catch (final IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletRegister!!!");
        final HttpSession session=request.getSession(true);
        session.setAttribute("TOKEN", TokenFactory.createToken());
        request.getRequestDispatcher(AttrJsp.REGISTER.getUrl()).forward(request, response);
    }
}
