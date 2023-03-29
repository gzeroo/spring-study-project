package hello.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// @WebServlet (name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
// was 에게 웹 서블릿인 것을 알려주는 어노테이션
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
// ' * ' front-controller/v1/ 경로로 들어오는 모든 파일(요청 HTTP)을 frontControllerServletV1가 우선 받겠다.
public class FrontControllerServletV1 extends HttpServlet {

    // 키 : 밸류 // ControllerV1 은 각 컨트롤러가 있음
    // ControllerV1 인터페이스를 이용해서 각 컨트롤러를 묶어온다
    // 각 컨트롤러의 공통점(인터페이스)를 이용
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1(){
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // (클라에서 요청받은 HTTP(HTML 헤더)를 확인해서 프론트 컨트롤러가 각 컨트롤러에게 경로 이동)

        // http 요청 메시지를 읽어 URI를 확인
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        ControllerV1 controller = controllerMap.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request,response);
    }
}
