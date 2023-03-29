package hello.servlet.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.web.domain.Member;
import hello.servlet.web.domain.MemberRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// 멤버 회원 리스트 조회하기

@WebServlet(name = "MemberListServlet", value = "/servlet/members")
public class MemberListServlet extends HttpServlet {
    MemberRepository memberRepository = MemberRepository.getInstance();
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // json으로 응답
        respondJson(response);
        // html로 응답
        respondHtml(response);
    }

    private void respondHtml(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();

        List<Member> members = memberRepository.findAll();

        w.write("<html>");
        w.write("<head>");
        w.write(" <meta charset=\"UTF-8\">");
        w.write(" <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write(" <thead>");
        w.write(" <th>id</th>");
        w.write(" <th>username</th>");
        w.write(" <th>age</th>");
        w.write(" </thead>");
        w.write(" <tbody>");
        for (Member member : members) {
            w.write("   <tr>");
            w.write("     <td>" + member.getId() + "</td>");
            w.write("     <td>" + member.getUsername() + "</td>");
            w.write("     <td>" + member.getAge() + "</td>");
            w.write(" </tr>");
        }
        w.write(" </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }

    private void respondJson(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();

        List<Member> members = memberRepository.findAll();

        members.stream().forEach((member)-> {
            try {
                String result = objectMapper.writeValueAsString(member);
                w.write(result);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
