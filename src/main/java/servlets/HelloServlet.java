package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lukaszgodlewski on 12.03.2017.
 */

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        response.setContentType("text/html");
        response.getWriter().println("<h1>Hello " + name + " World</h1>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        if(name==null || name.equals("")){
            response.sendRedirect("/");
        }
        response.setContentType("text/html");
        response.getWriter().println("<h1>Hello " + name + "</h1>");
    }
}

