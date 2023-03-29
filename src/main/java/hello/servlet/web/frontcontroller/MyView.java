package hello.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response); // forward 하면 인셉션 떠서 throws / try 캐치해야함
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // model 안에 클라이언트가 보낸 데이터가 들어있다.
        // 하지만 뷰(jsp)로 포워딩할 때 필요한 매개값은 "request" 다.
        // 그러므로, model 안에 있는 데이터를 request에 복사해야한다.

        modelToRequestAttribute(model, request); // 리퀘스트 대신 모델(리퀘스트에 데이터 정보 없어서)
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response); // forward 하면 인셉션 떠서 throws / try 캐치해야함 / dispatcher : 뷰 호출하는중
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
       // forEach를 사용하면 map 안에 있는 모든 <키, 값> 쌍을 순회할 수 있다.
        model.forEach((key, value) -> {
            request.setAttribute(key, value);
        });
    }
}
