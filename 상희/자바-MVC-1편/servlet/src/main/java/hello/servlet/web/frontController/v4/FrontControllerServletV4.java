package hello.servlet.web.frontController.v4;

import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);

        // null 예외처리
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        Map<String, String> paramMap = createParamMap(request); // 값들이 들어간다.
        Map<String, Object> model = new HashMap<>(); // Model 생성
        String viewName = controller.process(paramMap, model); // new-form이 들어간다.

//        System.out.println("Front Controller Members: "+ model.get("members"));
//        System.out.println("Front Controller Member : "+ model.get("member"));

        MyView view = viewResolver(viewName);
        view.render(model, request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    // 파라미터들을 가져옴
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(
                        paramName -> paramMap.put(paramName, request.getParameter(paramName)
                )
        );

        return paramMap;
    }
}
