package hello.servlet.web.mvc;

import hello.servlet.web.domain.Member;
import hello.servlet.web.domain.MemberRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 컨트롤러 역할 // 서블릿
@WebServlet(name = "MvcMemberListServlet", value = "/servlet-mvc/members")
public class MvcMemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("utf-8");

        List<Member> members = memberRepository.findAll();
        // ArrayList<Member> members = (ArrayList<Member>) memberRepository.findAll();
        // Member member = (Member) memberRepository.findAll();

        // model에 데이터 담기
        request.setAttribute("members",members); // 키 : 밸류 // 모델에 클라 정보 담기 setAttribute

        // jsp(뷰) 경로 가져오기 컨트롤러 -> 뷰 불러오는 과정
        String viewPath = "/WEB-INF/views/members.jsp"; // 뷰 경로
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }
}
