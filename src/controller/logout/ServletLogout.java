// ログアウト処理
// セッションがあれば破棄する
// Cookieがあれば破棄する
// いまのところgetもpostも同じ処理

package controller.logout;

import attribute.AttrCookie;
import attribute.AttrServlet;
import controller.wrapper.SynchronousHttpServlet;
import model.cookie.CookieFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletLogout", urlPatterns = "/Logout/")
public class ServletLogout extends SynchronousHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletLogout!!!");
        setCharacterEncodingUtf8(request);
        logout(request, response);
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletLogout!!!");
        setCharacterEncodingUtf8(request);
        logout(request, response);
    }

    private void logout(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
        CookieFactory.removeCookie(request, response, AttrCookie.LOGIN);
        sendRedirect(response, AttrServlet.LOGIN);
    }
}
