// ログイン処理
package controller.login;

import model.dto.user.User;
import model.dto.user.UserFactory;
import model.util.login.Login;
import model.util.servlet.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;

@WebServlet(name = "Login", urlPatterns = "/Login/")
public class ServletLogin extends HttpServlet {

    private final String JSP_LOGIN = ServletUtil.getJSP_LOGIN();
    private final String SERVLET_MAIN = ServletUtil.getSERVLET_MAIN(false);

    private final String COOKIE_LOGIN = ServletUtil.getCOOKIE_LOGIN();

    private final String UTF_8 = ServletUtil.getUTF_8();

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        // 認証できたらCookie発行する
        // 異常な場合はlogin.jspに遷移
        System.out.println("[POST]ServletLogin!!!");
        request.setCharacterEncoding(UTF_8);
        final String userId = request.getParameter("user-id");
        final String password = request.getParameter("password");
        String url;
        if (ServletUtil.nullCheck(userId, password)) {
            // パラメータ正常時
            try {
                final Login login = new Login(userId, password);
                if (login.isSuccess()) {
                    // ログイン成功時
                    final User user = login.getUser();
                    final Cookie cookie = new Cookie(COOKIE_LOGIN, URLEncoder.encode(user.toJson(), UTF_8));
                    cookie.setMaxAge(60 * 60 * 24);    // 24時間保持する
                    cookie.setPath("/MyDrive2");
                    final HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                    response.addCookie(cookie);
                    url = SERVLET_MAIN;
                } else {
                    // ログイン失敗時
                    url = JSP_LOGIN;
                }
            } catch (SQLException e) {
                // データベース例外
                // 503ページに遷移？
                e.printStackTrace();
                url = "503page";
            }
        } else {
            // パラメータ異常
            url = JSP_LOGIN;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        // CookieがあればHomeへ
        // なければLoginへ
        System.out.println("[GET]ServletLogin!!!");
        request.setCharacterEncoding(UTF_8);
        final Cookie[] cookies = request.getCookies();
        final Cookie cookie = ServletUtil.findCookieOrNull(cookies, COOKIE_LOGIN);
        String url;
        if (cookie == null) {
            //url = JSP_LOGIN;
            url="/WEB-INF/login.jsp";
        } else {
            request.getSession().setAttribute("USER",
                    UserFactory.createFromJson(URLDecoder.decode(cookie.getValue(), UTF_8)));
            url = SERVLET_MAIN;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
}
