package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.domain.Member;
import hello.servlet.web.domain.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      response.setCharacterEncoding("utf-8");

      List<Member> members = memberRepository.findAll();


      // model에 데이터 담기
      request.setAttribute("members",members); // 키 : 밸류 // 모델에 클라 정보 담기 setAttribute

      // jsp(뷰) 경로 가져오기 컨트롤러 -> 뷰 불러오는 과정
//      String viewPath = "/WEB-INF/views/members.jsp"; // 뷰 경로
//      RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//      dispatcher.forward(request,response);

        return new MyView("/WEB-INF/views/members.jsp"); // 프론트 컨트롤러에게 전달
    }
}
