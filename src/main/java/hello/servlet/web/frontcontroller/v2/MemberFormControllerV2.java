package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        return new MyView("/WEB-INF/views/new-form.jsp"); // 리턴 MyView 클래스 public MyView(String viewPath) 이용
        // new를 이용해 기본 생성자 초기화 진행
    }
}
