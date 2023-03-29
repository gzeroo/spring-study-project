package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// 참고 사이트 페이지 링크: https://passionate.tistory.com/41#recentComments

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3(){
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // (클라에서 요청받은 HTTP(HTML 헤더)를 확인해서 프론트 컨트롤러가 각 컨트롤러에게 경로 이동)

        // http 요청 메시지를 읽어 URI를 확인
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        // 1. 컨트롤러 조회 - 매핑 정보
        ControllerV3 controller = controllerMap.get(requestURI);

        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


       // v2: MyView view = controller.process(request,response); // jsp 호출 경로
       // v3: request와 response를 map으로 대체(서브릿에 대한 종속성 제거)
       //   : MyView -> ModerlView

        Map<String, String> paramMap = createParamMap(request);
        // 2. 컨트롤러 호출 / 3. 모델뷰 반환
        ModelView mv = controller.process(paramMap);


        //4. viewResolver 호출 / 5. MyView 반환
        // "save" -> "WEB-INF/views/save.jsp"
        MyView view = viewResolver(mv.getViewName()); // Resolver 접두사 접두미 붙여주는 역할

        // 클라이언트가 폼을 통해 보낸
        // username : 이정은 & age: 12 정보는 어디에 들어있나?
        // => ModelView 객체 안에 들어있다.

        // 6. render 모델 호출
        view.render(mv.getModel(),request,response);
        // viewResolver(mv.getViewName());


       // view.render(request,response); //  render 뷰 호출
    }

    private MyView viewResolver(String viewName) {
       return new MyView("/WEB-INF/views/" + viewName +".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        // request : http 요청 정보(<- was가 파싱해서 넣어준 정보)
        // { username : 이정은
        // age : 12 }
        // request.getParameter("username");
        Map<String, String> paramMap = new HashMap<>();

        // 모든 파라미터 나옴 (클라에서 입력한 값 ex.폼에 입력한 유저네임, 나이)
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,request.getParameter(paramName)));

        return paramMap;
    }
}
