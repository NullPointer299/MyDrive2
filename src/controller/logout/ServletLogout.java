// ログアウト処理
// セッションがあれば破棄する
// Cookieがあれば破棄する
// いまのところgetもpostも同じ処理

package controller.logout;

import model.util.servlet.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ServletLogout", urlPatterns = "/Logout/")
public class ServletLogout extends HttpServlet {

    // リダイレクトのするため、JSPとして呼び出す
    private final String SERVLET_LOGIN = ServletUtil.getSERVLET_LOGIN(true);

    private final String COOKIE_LOGIN = ServletUtil.getCOOKIE_LOGIN();
    private final String COOKIE_PATH = ServletUtil.getCOOKIE_PATH();

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletLogout!!!");
        logout(request, response);
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletLogout!!!");
        logout(request, response);
    }

    private void logout(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String url;
        url = SERVLET_LOGIN;
        final HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
        final Cookie login = ServletUtil.findCookieOrNull(request.getCookies(), COOKIE_LOGIN);
        if (login != null) {
            login.setMaxAge(0); //Cookieの削除
            login.setPath(COOKIE_PATH);
            response.addCookie(login);
        }
        response.sendRedirect(url);
    }
}
