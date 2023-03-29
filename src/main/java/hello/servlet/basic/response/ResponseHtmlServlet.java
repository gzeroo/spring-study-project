package hello.servlet.basic.response;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResponseHtmlServlet", value = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter(); //바디 입력
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<h1>안녕하세요, 잘지내요?</h1>");
        writer.println("</body>");
        writer.println("</html>");

    }
}
