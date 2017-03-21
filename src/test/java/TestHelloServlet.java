import org.junit.Test;
import org.mockito.Mockito;
import servlets.HelloServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lukaszgodlewski on 12.03.2017.
 */
public class TestHelloServlet  extends Mockito {

    @Test
    public void servlet_should_not_greet_the_user_if_the_name_is_null() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HelloServlet servlet = new HelloServlet();
        PrintWriter writer = mock(PrintWriter.class);

        when(request.getParameter("name")).thenReturn(null);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        verify(response).sendRedirect("/");
    }

    @Test
    public void servlet_should_not_greet_the_user_if_the_name_is_empty() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HelloServlet servlet = new HelloServlet();
        PrintWriter writer = mock(PrintWriter.class);

        when(request.getParameter("name")).thenReturn("");
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        verify(response).sendRedirect("/");
    }

    @Test
    public void servlet_should_greet_the_user_if_the_name_is_provided() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HelloServlet servlet = new HelloServlet();

        PrintWriter writer = mock(PrintWriter.class);
        when(request.getParameter("name")).thenReturn("Tomek");
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        verify(writer).println("<h1>Hello Tomek</h1>");

    }
}
