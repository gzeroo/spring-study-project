package hello.servlet.basic.response;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ResponseHeaderServlet", value = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    // was가 클라에게 받은 request, response http로 만들어줌
    // http 서버 -> 클라 응답 시 보내는 메시지 구조 스탯(응답코드) - 헤더 - 바디
    // 응답헤더
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // [http status] - 응답코드
        response.setStatus(HttpServletResponse.SC_OK);

        // [http headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header","hello!");

        response.sendRedirect("basic/hello-form.html"); // 로그인하고 메인화면으로 갈 때 느낌
        // 너 다시 거기(경로)로 가

        // [http body]
        response.getWriter().println("Hello World!");
        response.getWriter().println("OK!");
        // response.getWriter().flush(); // 버퍼를 비워주는 역할

    }
}
