package hello.servlet.basic.request;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


// 클라의 전송 방식은 2가지
// 1. 쿼리파라미터 방식 / 2. html 폼 방식

// 쿼리파마미터 방식으로 클라 -> 서버에게 보내는 전송 방식
// 클라 입력 예시 아래
// http://localhost:8080/request-param?username=박가경&username=김지영&age=25&username=윤지수=40
// 전체 파라미터 조회 시 가장 첫번째 값이 나온다. ex. 유저네임 박가경, age 25 나옴
@WebServlet(name = "RequestParamServlet", value = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[전체 파라미터 조회] - start"); // 클라이언트가 서버에 보내는 전체 파라미터 정보 보기
        request.getParameterNames().asIterator()
                        .forEachRemaining(paramName ->{
                            System.out.println(paramName + "=" +
                                    request.getParameter(paramName));
                        });
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        System.out.println("request.getParameter(\"username\") = "
                + request.getParameter("username"));
        System.out.println("request.getParameter(\"age\") = "
                + request.getParameter("age"));

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        System.out.println("request.getParameterValues(username)");
        String[] username = request.getParameterValues("username");
        for (String name : username){
            System.out.println("username =" + name);
        }

        response.getWriter().write("OK");
    }
}
