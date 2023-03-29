package hello.servlet.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MemberFormServlet", value = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // HTML 폼을 응답할 것
        // Header 채우기
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        // body 채우기 - html 입력
        PrintWriter w = response.getWriter();
        w.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <form action=\"/servlet/members/save\" method=\"post\" >\n" +
                "    username: <input type=\"text\" name=\"username\">\n" +
                "    age: <input type=\"text\" name=\"age\">\n" +
                "    <button type=\"submit\">전송</button>\n" +
                "  </form>\n" +
                "</body>\n" +
                "</html>");
    }
}
