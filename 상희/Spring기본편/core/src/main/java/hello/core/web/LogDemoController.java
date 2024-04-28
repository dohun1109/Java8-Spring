package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        // 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환 한다. (DL)
        MyLogger myLogger = myLoggerProvider.getObject();

        System.out.println("myLogger = " + myLogger.getClass());
        // 클라이언트 요청시 여기서 request가 만들어진다.
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);
        Thread.sleep(1000);
        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
