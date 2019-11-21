package controller.main;

import model.dto.tree.TreeFactory;
import model.dto.tree.TreeManagement;
import model.dto.user.User;
import model.util.servlet.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletMain", urlPatterns = "/Main/")
public class ServletMain extends HttpServlet {

    private static final String JSP_MAIN = ServletUtil.getJSP_MAIN();
    private static final String SERVLET_LOGIN = ServletUtil.getSERVLET_LOGIN(false);

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletHome!!!");
        request.setCharacterEncoding("UTF-8");
        String url;

        final HttpSession session = request.getSession();
        final User user = ((User) session.getAttribute("USER"));
        try {
            final TreeManagement tm = TreeFactory.createTreeManagement(user.getId());
            session.setAttribute("TREE_MANAGEMENT", tm);

            url = JSP_MAIN;

        } catch (SQLException e) {
            url = "503page";
            e.printStackTrace();
        }


        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletHome!!!");
        request.setCharacterEncoding("UTF-8");
        String url = JSP_MAIN;
        request.getRequestDispatcher(url).forward(request, response);
    }
}
