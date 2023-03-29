package hello.servlet.web.mvc;

import hello.servlet.web.domain.Member;
import hello.servlet.web.domain.MemberRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MvcMemberSaveServlet", value = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //request, response 모델 mvc에서 모델 역할임
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = Member.builder()
                .username(username)
                .age(age).build();

        memberRepository.save(member);

        // model에 데이터 담기
        request.setAttribute("member",member); // 키 : 밸류 // 모델에 클라 정보 담기 setAttribute

        // jsp(뷰) 경로 가져오기 컨트롤러 -> 뷰 불러오는 과정
        String viewPath = "/WEB-INF/views/save.jsp"; // 뷰 경로
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response); // forward()[전달하기]는 클라이언트가 요청하면서 전송한 데이터를 그대로 유지한다.
    }
}
