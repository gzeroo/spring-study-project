package hello.servlet.web.mvc;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


// mvc 모델 기법 / MvcMemberFormServlet 여기 클래스는 컨트롤러 역할임
@WebServlet(name = "MvcMemberFormServlet", value = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // request,response >  모델 역할 // 리소스를 호출할 수 있는 dispatcher을 호출할 것
        dispatcher.forward(request,response); //  "/WEB-INF/views/new-form.jsp" 경로를 호출해서 request,response 을 전달한다.
        // http request, response

    }

}
