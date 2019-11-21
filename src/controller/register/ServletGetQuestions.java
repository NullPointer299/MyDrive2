package controller.register;

import model.dto.question.QuestionsFactory;
import model.util.servlet.ServletUtil;
import model.wrap.AjaxHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletGetQuestions", urlPatterns = "/GetQuestions/")
public class ServletGetQuestions extends AjaxHttpServlet {

    private final String SERVLET_LOGIN = ServletUtil.getSERVLET_LOGIN(true);

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletGetQuestions!!!");
        try {
            initialServlet(request);
            final String jsonResponse = QuestionsFactory.create().toJson();
            sendJsonResponse(response, jsonResponse);
        } catch (SQLException e) {
            // 503ページ？
            e.printStackTrace();
        }
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletGetQuestions!!!");
        throw new IllegalStateException("Not implemented!");
    }
}
