package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelAndView;

import java.util.Map;

public interface ControllerV3 {


    ModelAndView process(Map<String, String> paramMap);

}
