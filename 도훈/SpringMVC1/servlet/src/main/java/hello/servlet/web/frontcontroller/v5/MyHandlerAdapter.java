package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelAndView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler);

    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;


}
