package controller.main;

import controller.wrapper.AsynchronousHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletDownload", urlPatterns = "/Download/")
public class ServletDownload extends AsynchronousHttpServlet {
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletDownload!!!");
        notLoggedInIfLogin(request, response);

    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletDownload!!!");
        notLoggedInIfLogin(request, response);

    }
}
