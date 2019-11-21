package model.util.servlet;

import com.google.gson.Gson;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Objects;

public class ServletUtil {

    private static final String WEB_INF = "/WEB-INF/";
    private static final String MY_DRIVE2 = "/MyDrive2";

    private static final String JSP_LOGIN = WEB_INF + "login.jsp";
    private static final String JSP_REGISTER = WEB_INF + "register.jsp";
    private static final String JSP_MAIN = WEB_INF + "main.jsp";

    private static final String SERVLET_LOGIN = "/Login/";
    private static final String SERVLET_LOGOUT = "/Logout/";
    private static final String SERVLET_REGISTER = "/Register/";
    private static final String SERVLET_Main = "/Main/";

    private static final String UTF_8 = "UTF-8";

    private static final String COOKIE_LOGIN = "Login";
    private static final String COOKIE_PATH = "/MyDrive2";

    private static final Gson GSON = new Gson();

    private ServletUtil() {
    }

    public static String getJSP_LOGIN() {
        return JSP_LOGIN;
    }

    public static String getJSP_REGISTER() {
        return JSP_REGISTER;
    }

    public static String getJSP_MAIN() {
        return JSP_MAIN;
    }

    public static String getSERVLET_LOGIN(final boolean isCallingFromJSP) {
        return isCallingFromJSP ? MY_DRIVE2 + SERVLET_LOGIN : SERVLET_LOGIN;
    }

    public static String getSERVLET_LOGOUT(final boolean isCallingFromJSP) {
        return isCallingFromJSP ? MY_DRIVE2 + SERVLET_LOGOUT : SERVLET_LOGOUT;
    }

    public static String getSERVLET_REGISTER(final boolean isCallingFromJSP) {
        return isCallingFromJSP ? MY_DRIVE2 + SERVLET_REGISTER : SERVLET_REGISTER;
    }

    public static String getSERVLET_MAIN(final boolean isCallingFromJSP) {
        return isCallingFromJSP ? MY_DRIVE2 + SERVLET_Main : SERVLET_Main;
    }

    public static String getUTF_8() {
        return UTF_8;
    }

    public static String getCOOKIE_LOGIN() {
        return COOKIE_LOGIN;
    }

    public static String getCOOKIE_PATH() {
        return COOKIE_PATH;
    }

    public static Gson getGSON() {
        return GSON;
    }

    public static boolean nullCheck(final Object... args) {
        return Arrays.stream(args).noneMatch(Objects::isNull);
    }

    public static Cookie findCookieOrNull(final Cookie[] cookies, final String target) {
        return cookies == null ? null : Arrays.stream(cookies).filter(c -> c.getName().equals(target)).findFirst().orElse(null);
    }
}
