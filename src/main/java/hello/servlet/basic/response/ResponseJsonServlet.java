package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ResponseJsonServlet", value = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();  // 스트링 > 오브젝트 > 스트링 변경 가능 / 잭슨 오브젝트 / 제이슨으로 묶기 가능


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("content-type", "application/json");
        response.setCharacterEncoding("utf-8");

        /*
//        User user = new User(); // user 객체로 변환
//        user.setUsername("김지영");
//        user.setAge(20);

//        String result = objectMapper.writeValueAsString(user); // 제이슨으로 직렬화
//        System.out.println("result =" + result);
//        response.getWriter().write(user.toString());
         */

        // 문제!
        // QueryString을 이용해 클라이언트에서 username과 age를 보내고 // http://localhost:8080/response-json?username=김지영&age=20
        // 서버에서 해당 정보를 읽어 user 객체를 생성한다. //
        // 생성된 user 객체는 json으로 변환한다.

        // 전체 파라미터 읽기
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName ->{
                    System.out.println(paramName + "=" +
                            request.getParameter(paramName));
                });

        System.out.println(request.getParameter("username")); // 단일 파라미터

        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setAge(Integer.parseInt(request.getParameter("age")));

        String result = objectMapper.writeValueAsString(user);
        System.out.println("result =" + result);
        response.getWriter().write(result);
    }
}
