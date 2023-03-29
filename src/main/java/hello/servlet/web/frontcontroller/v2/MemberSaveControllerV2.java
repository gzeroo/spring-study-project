package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.domain.Member;
import hello.servlet.web.domain.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = Member.builder()
                .username(username)
                .age(age).build();

        memberRepository.save(member);

        request.setAttribute("member",member);

//        String viewPath = "/WEB-INF/views/save.jsp";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//        dispatcher.forward(request,response);

        return new MyView("/WEB-INF/views/save.jsp");
    }
}
