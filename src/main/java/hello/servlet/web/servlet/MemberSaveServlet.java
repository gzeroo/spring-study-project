package hello.servlet.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.web.domain.Member;
import hello.servlet.web.domain.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


// 클라가 form에 입력한 정보를 서버에서 저장하는 클래스
@WebServlet(name= "memberSaveServlet", value = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet { // extends HttpServlet  상속받아야 서블릿임!

    private MemberRepository memberRepository = MemberRepository.getInstance();
    private ObjectMapper objectMapper = new ObjectMapper(); // 직렬화

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age")); // 스트링에서 인티저로

        // 멤버 객체 만들기
        // builder 이용 시 매개변수 입력 순서 x
        Member member = Member.builder()
                .username(username)
                .age(age).build();

        memberRepository.save(member);

        // 매개변수 순서를 지켜야함
        // Member member1 = new Member(username, age);
        // Member member2 = new Member(age, username);
        // 위에 생성자랑 같음 // 생성자 //

        respondOfJson(resp, member);
        respondHtml(resp, member);
    }

    // throws 함수를 소환한 애한테 인셉션 처리해 던짐(거의 메인한테 던짐)
    // 서블릿 안에 직접 동적 HTML 생성
    public void respondHtml(HttpServletResponse resp,  Member member) throws IOException{
        // 헤더
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter w = resp.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+ member.getId() + "</li>\n" +
                " <li>username="+ member.getUsername() + "</li>\n" +
                " <li>age="+ member.getAge() + "</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }

    // json 방식으로 서버 -> 클라 전달하는 http
    public void respondOfJson(HttpServletResponse resp,  Member member) throws IOException{

        // 헤더
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        // 직렬화 objectMapper (잭슨 라이브러리)
        String result = objectMapper.writeValueAsString(member);
        // String result2 = objectMapper.writeValueAsString(memberRepository.findById(member.getId()));
        resp.getWriter().write(result);
       // resp.getWriter().write(result2);

    }
}
