package controller.wrapper;

import attribute.AttrServlet;
import model.dto.user.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

abstract class AbstractHttpServletWrapper extends HttpServlet {

    protected void setCharacterEncodingUtf8(final HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
    }

    protected boolean isLoggedIn(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        final User user = session == null ? null : ((User) session.getAttribute("USER"));
        return user != null;
    }

    protected void notLoggedInIfLogin(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        if (isLoggedIn(request))
            setCharacterEncodingUtf8(request);
        else
            sendRedirect(response, AttrServlet.LOGIN);
    }

    protected void sendRedirect(final HttpServletResponse response, final AttrServlet servlet) throws IOException {
        System.out.println("Redirect to " + servlet.name());
        response.sendRedirect(servlet.getUrl(true));
    }

    protected String URLEncode(final String target) throws UnsupportedEncodingException {
        return URLEncoder.encode(target, "UTF-8");
    }

    protected String URLDecode(final String target) throws UnsupportedEncodingException {
        return URLDecoder.decode(target, "UTF-8");
    }
}
