package controller.wrapper;

import attribute.AttrJsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SynchronousHttpServlet extends AbstractHttpServletWrapper {

    protected void forward(final HttpServletRequest request, final HttpServletResponse response, final AttrJsp jsp) throws ServletException, IOException {
        request.getRequestDispatcher(jsp.getUrl()).forward(request, response);
    }
}
