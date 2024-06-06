package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 만약 URI가 new-form이라면 [/front-controller/v3/members/new-form]을 넣는다.
        String requestURI = request.getRequestURI();

        // Map에다가 들어온 URL 을 조회한다. new-form 이니 MemberFormControllerV3이 반환
        ControllerV3 controller = controllerMap.get(requestURI);

        // null 예외처리
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        // createParamMap에다가 request정보를 보낸다.
        Map<String, String> paramMap = createParamMap(request); // username, age에 관한 값이 들어있는 Map이 들어온다.
        // 가져온 파라미터를 MemberFormControllerV3의 process에다가 넣는다.
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();// new-from 반환
        MyView view = viewResolver(viewName); // 이름 생성

        view.render(mv.getModel(), request, response); // 랜더 한다.
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        // getParameterNames으로 모든 요청 페이지의 파라미터를 반환한다.
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(
                        // 가져온 파라미터를 Map에다가 넣는다.
                        // <input name="username"><input name="age">이 있다고 하면 그 값들을 가져와서 Map에다가 넣는다.
                        paramName -> paramMap.put(paramName, request.getParameter(paramName)
                        )
                );

        return paramMap;
    }
}
