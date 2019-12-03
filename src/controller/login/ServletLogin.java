// ログイン処理
package controller.login;

import attribute.AttrCookie;
import attribute.AttrJsp;
import attribute.AttrServlet;
import controller.util.ServletUtil;
import controller.wrapper.SynchronousHttpServlet;
import model.cookie.CookieFactory;
import model.dto.user.User;
import model.dto.user.UserFactory;
import model.util.login.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Login", urlPatterns = "/Login/")
public class ServletLogin extends SynchronousHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletLogin!!!");
        setCharacterEncodingUtf8(request);
        final String userId = request.getParameter("user-id");
        final String password = request.getParameter("password");
        if (ServletUtil.nullCheck(userId, password)) {
            try {
                final Login login = new Login(userId, password);
                System.out.println("login: " + login.isSuccess());    // TODO debug code here.
                if (login.isSuccess()) {
                    final User user = login.getUser();
                    final Cookie cookie = CookieFactory.create(AttrCookie.LOGIN, URLEncode(user.toJson()));
                    final HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                    response.addCookie(cookie);
                    sendRedirect(response, AttrServlet.MAIN);
                } else {
                    forward(request, response, AttrJsp.LOGIN);
                }
            } catch (SQLException e) {
                //  TODO 503ページに遷移？
                e.printStackTrace();
            }
        } else {
            forward(request, response, AttrJsp.LOGIN);
        }
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletLogin!!!");
        setCharacterEncodingUtf8(request);
        final Cookie[] cookies = request.getCookies();
        final Cookie cookie = CookieFactory.findCookieOrNull(cookies, AttrCookie.LOGIN);
        if (cookie != null) {
            request.getSession().setAttribute("USER",
                    UserFactory.create(URLDecode(cookie.getValue())));
            sendRedirect(response, AttrServlet.MAIN);
        }
        forward(request, response, AttrJsp.LOGIN);
    }
}
