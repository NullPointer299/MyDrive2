package controller.main;

import attribute.AttrJsp;
import controller.wrapper.SynchronousHttpServlet;
import model.dto.tree.TreeFactory;
import model.dto.tree.TreeManagement;
import model.dto.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletMain", urlPatterns = "/Main/")
public class ServletMain extends SynchronousHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletMain!!!");
        notLoggedInIfLogin(request, response);
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("USER");
        try {
            if (session.getAttribute("TREE_MANAGEMENT") == null) {
                final TreeManagement tm = TreeFactory.createTreeManagement(user.getId());
                session.setAttribute("TREE_MANAGEMENT", tm);
            }
        } catch (final SQLException e) {
            // 503?
            e.printStackTrace();
        }
        forward(request, response, AttrJsp.MAIN);
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletMain!!!");
        System.out.println("Redirect to POST");
        setCharacterEncodingUtf8(request);
        doPost(request, response);
    }
}
