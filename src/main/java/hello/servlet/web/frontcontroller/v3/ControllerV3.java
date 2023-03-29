package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public interface ControllerV3 {

    // 서블릿 객체 매개변수 뺌 / Http ~ request 없애고 Map 대신 넣음
    ModelView process(Map<String, String> paramMap);

}
