package model.dto.cookie;


import attribute.AttrCookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

public class CookieFactory {
    public static Cookie create(final AttrCookie attrCookie, final String body) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie(attrCookie.name(), URLEncoder.encode(body, "UTF-8"));
        cookie.setMaxAge(attrCookie.getMaxAge());    // 24時間保持する
        cookie.setPath(attrCookie.getPath());
        return cookie;
    }

    public static Cookie findCookieOrNull(final Cookie[] cookies, final AttrCookie attrCookie) {
        return cookies == null ? null : Arrays.stream(cookies).filter(c -> c.getName().equals(attrCookie.name())).findFirst().orElse(null);
    }

    public static void removeCookie(final HttpServletRequest request, final HttpServletResponse response, final AttrCookie attrCookie) {
        final Cookie cookie =
                findCookieOrNull(request.getCookies(), AttrCookie.LOGIN);
        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setPath(attrCookie.getPath());
            response.addCookie(cookie);
        }
    }
}
