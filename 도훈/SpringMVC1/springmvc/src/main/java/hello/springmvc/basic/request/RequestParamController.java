package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }


    @ResponseBody //RestController 와 동일한 효과
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //@RequestParam(name="xx") 생략가능  아무래도 team convention 상황에 따라 사용여부 결정
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, //required 의 경우 기본 false 인데 true 일경우 queryString 으로 넘어오는 RequestParameter 가 없다면 BadRequest 로 인식한다.
            @RequestParam(required = false) Integer age
            // null, "";
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age
            // null, "" defaultValue 의 경우 "" 도 default 값으로 인식한다.;
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap
    ) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
        //파라미터의 값이 1개가 확실하다면 Map 그렇지 않다면 MultiValueMap 을 사용 하지만, 보통 1개로 사용  
    }


    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) { //@ModelAttribute 생략가능
        /*
         * 스프링 MVC 는 @ModelAttribute 가 있으면 다음을 실행한다.
         * 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter 를 호출해서 파라미터의 값을 입력(바인딩) 한다. 
         */

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
//        log.info("helloData={}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2( HelloData helloData) { //@ModelAttribute 생략가능

        /*
         *  그런데, @RequestParam 도 생략할 수 있으니 혼란이 발생할 수 있다.
         *
         *  스프링은 해당 생략시 다음과 같은 규칙을 적용한다.
         *  String, int , Integer 같은 단순 타입 = @RequestParam
         *  나머지 = @ModelAttribute(argument resolver 로 지정해둔 타입 외)
         */

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
//        log.info("helloData={}", helloData);
        return "ok";
    }
}
