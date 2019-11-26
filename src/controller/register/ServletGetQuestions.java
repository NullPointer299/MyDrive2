package controller.register;

import model.dto.question.QuestionsFactory;
import controller.wrapper.AsynchronousHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletGetQuestions", urlPatterns = "/GetQuestions/")
public class ServletGetQuestions extends AsynchronousHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletGetQuestions!!!");
        try {
            notLoggedInIfLogin(request, response);
            final String jsonResponse = QuestionsFactory.create().toJson();
            sendJsonResponse(response, jsonResponse);
        } catch (SQLException e) {
            // 503ページ？
            e.printStackTrace();
        }
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletGetQuestions!!!");
        notLoggedInIfLogin(request, response);
    }
}
