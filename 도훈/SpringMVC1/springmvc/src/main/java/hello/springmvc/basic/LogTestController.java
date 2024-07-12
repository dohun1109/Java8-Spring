package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller     반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 렌더링 된다.
@Slf4j
@RestController   //반환 값으로 뷰를 찾는것이 아니라 HTTP 메시지 바디에 바로 입력한다.
// 따라서 실행결과로 ok 메세지를 받을수 있다.
public class LogTestController {

//   private final Logger log = LoggerFactory.getLogger(getClass()); //lombok 이 대신 넣어줌


   @RequestMapping("/log-test")
   public String logTest() {
      String name = "Spring";

      System.out.println("name = " + name);

      log.trace(" trace my log=" + name);    //자동으로 연산이 일어남,즉. resource 낭비가 일어남

      log.trace(" trace log={}", name);

      log.debug(" debug log={}", name);   //debug 정보
      log.info(" info log={}", name);  //중요한 정보, EX)비즈니스정보, 운영시스템에서 보는 정보
      log.warn(" warn log={}", name);  //경고 위험
      log.error(" error log={}", name);   //error

      //로그를 사용하지 않아도 a + b 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 x
      log.debug("String concat log=", name);

      
      return "ok";
   }
}
