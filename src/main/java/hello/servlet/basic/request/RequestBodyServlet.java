package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RequestBodyServlet", value = "/request-body")
public class RequestBodyServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 클라이언트가 보낸 요청 HTTP 메시지 body 읽기(body에 데이터가 담기니까)
        // System.out.println(request.getServletContext());

        /*
        BufferedReader reader = request.getReader(); // 전체 라인 메시지 읽기
        System.out.println(reader.readLine());
        reader.lines().forEach(line ->{
            System.out.println(line);
        });
         */

        // 클라가 서버에게 보내는 방법
        ServletInputStream inputStream = request.getInputStream();
        String messageBody
                = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 클라 메시지 파싱

        System.out.println("messageBody =" + messageBody);
        // String(json) -> 객체로 변환


        // 서버가 클라에게 보내는 방법
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8"); // 인코딩 한국어로 설정
        response.getWriter().write("OK"); // 클라 -> 서버 전송 완료되었으면 OK 출력 되도록 설정
        User user = objectMapper.readValue(messageBody, User.class);
        response.getWriter().write(String.valueOf(user.getAge())+"\n");
        response.getWriter().write(user.getUsername()+"\n");

    }
}
