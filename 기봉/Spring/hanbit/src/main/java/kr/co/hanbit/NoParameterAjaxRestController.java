package kr.co.hanbit;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NoParameterAjaxRestController {
    @RequestMapping("/get-with-no-parameter")
    public String getWithNoParameter(){
        return "파라미터가 없는 GET 요청";
    }
}
