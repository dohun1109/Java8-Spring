package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        
        //ThreadA : A 사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB : B 사용자 20000원 주문
        int userBPrice = statefulService2.order("userA", 20000);

        //ThreadA : 사용자 A가 주문 금액 조회
        int price = statefulService1.getPrice(); //stateless 한 상황이라면 10000 하지만 20000 공유변수 값변경됨. (동시성 문제)
        System.out.println("price = " + price);

        //해결 1 : 공유변수를 줄이고 각가의 로컬변수로 사용(ThreadLocal 사용)

        
        Assertions.assertThat(userAPrice).isNotEqualTo(userBPrice);
        
        //스프링 빈들은 항상 Stateless 하게 설계해야된다. 
        
    }



    static class  TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}