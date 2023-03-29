package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    // 키 : 밸류 // ControllerV2 은 각 컨트롤러가 있음
    // ControllerV1 인터페이스를 이용해서 각 컨트롤러를 묶어온다
    // 각 컨트롤러의 공통점(인터페이스)를 이용
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2(){
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // (클라에서 요청받은 HTTP(HTML 헤더)를 확인해서 프론트 컨트롤러가 각 컨트롤러에게 경로 이동)

        // http 요청 메시지를 읽어 URI를 확인
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        ControllerV2 controller = controllerMap.get(requestURI);

        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 모델(데이터) 전달을 위해 request 매개변수 이용
        MyView view = controller.process(request,response); // jsp 호출 경로
        view.render(request,response); //  render 뷰 호출
    }
}
