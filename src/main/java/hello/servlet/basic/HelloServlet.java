package hello.servlet.basic;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        //System.out.println("req = " + req);
        //System.out.println("resp = " + resp);

        System.out.println(req.getMethod());
        System.out.println(req.getRemoteUser());
        System.out.println(req.getRequestURL());

        System.out.println(req.getQueryString()); // ? name=jiyoung&age=20 / ?뒤에 키 = 밸류
        System.out.println(req.getParameter("age"));

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("hello world!");
        resp.getWriter().write("\n");
        resp.getWriter().write(req.getParameter("username")+"씨 안녕하세요~~~! ");
    }
}

