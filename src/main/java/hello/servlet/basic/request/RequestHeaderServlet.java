package hello.servlet.basic.request;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RequestHeaderServlet", value = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
    }

    public void printHeaders(HttpServletRequest request){
        System.out.println("--- Headers - Start ---");

        request.getHeaderNames().asIterator().
                forEachRemaining(headerName -> {
                    System.out.println(headerName + ":"
                            + request.getHeader(headerName));
        });

        System.out.println("--- Headers - End ---");
        System.out.println();
    }

    private void printStartLine(HttpServletRequest request){
        System.out.println("--- REQUEST-LINE - Start ---");

        System.out.println("request.getMethod() = " + request.getMethod()); // 클라이언트가 어떤 메소드로 요청했는지 알 수 있음.
        System.out.println("request.getProtocol() = " + request.getProtocol());
        System.out.println("request.getScheme() = " + request.getScheme());
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); // http's' 가 적용되었는지 여부

        System.out.println("--- REQUEST-LINE - End ---");
        System.out.println();
    }

    // 클라 전송 방식
    // 1. html 폼 2. 쿼리파라미터 입력(url창에 ?username 쓰던거)
}
