/*
Tokenを受け取って秘密の質問を返します
 */
package controller.register;

import controller.wrapper.AsynchronousHttpServlet;
import model.dto.question.Questions;
import model.dto.question.QuestionsFactory;
import model.dto.token.Token;
import model.dto.token.TokenFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletGetQuestions", urlPatterns = "/GetQuestions/")
public class ServletGetQuestions extends AsynchronousHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletGetQuestions!!!");
        try {
            final HttpSession session = request.getSession(true);
            final String jsonRequest = receiveJsonRequest(request);
            final Token.Encoded tokenEncoded = TokenFactory.deserialize(jsonRequest);
            if (isValidToken(session, tokenEncoded)) {
                final Token token=TokenFactory.createToken();
                final Questions questions=QuestionsFactory.create();
                questions.getEncoded().setToken(token);
                final String jsonResponse = questions.toJson();
                session.setAttribute("TOKEN", token);
                sendJsonResponse(response, jsonResponse);
            } else {
                // TODO Tokenが不正だったとき
            }
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
