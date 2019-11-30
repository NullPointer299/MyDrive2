package controller.main;

import controller.wrapper.AsynchronousHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


@WebServlet(name = "ServletUpload", urlPatterns = "/Upload/")
public class ServletUpload extends AsynchronousHttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[POST]ServletUpload!!!");   // TODO debug code here.
        notLoggedInIfLogin(request, response);
        final Part part = request.getPart("file");
        final String name = getFileName(part);
        part.write(getServletContext().getRealPath("/WEB-INF/uploaded") + "/" + name);
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[GET]ServletUpload!!!");    // TODO debug code here.
        notLoggedInIfLogin(request, response);
    }

    private String getFileName(final Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }
}
