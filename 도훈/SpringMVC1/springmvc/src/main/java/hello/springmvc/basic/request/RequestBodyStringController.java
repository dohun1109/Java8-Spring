package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Controller
@Slf4j
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={} ",messageBody );
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={} ",messageBody );
        responseWriter.write("ok");
    }

    /** 스프링 MVC 는 다음 파라미터를 지원한다.
     *  HttpEntity : Http header, body 정보를 편리하게 조회
     *      - 메시지 바디 정보를 직접 조회
     *      - 요청 파리멑를 조회하는 기능과 관계 없음 @RequestParam X, @ModelAttribute X
     *
     *  HttpEntity 는 응답에도 사용 가능
     *      - 메시지 바디 정보 직접 반환
     *      - 헤더 정보 포함 기능
     *      - view 조회 x
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();

        log.info("messageBody={} ", messageBody );

        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }
    // HttpEntity 를 상속받은 다음 객체들도 기능을 제공한다.
    /*
    * RequestEntity : HttpMethod, url 정보가 추가, 요청에서 사용
    * ResponseEntity
    *   - Http 상태코드 설정 가능, 응답에서 사용
    *   - return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CRATED)
    *
    *  참고
    *   스프링 MVC 내부에서 HTTP 메시지 바디를 읽어서 문자나 바디를 읽어서 문자나 객체로 변환해서 전달해주는데, 이때 HTTP 메시지 컨버터라는 기능을 사용한다. 
    * */


    /**
     * @RequestBody
     *      - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
     *      - HttpMassageConverter 사용 -> StringHttpMessageConverter 적용
     *
     * @ResponseBody
     *      - 메시지 바디 정보를 직접 변환(view 조회 X)
     *      - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용 
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={} ", messageBody );

        return "ok";
    }

    






}
