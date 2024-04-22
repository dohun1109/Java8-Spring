package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B사용자 20000원 주문
        int userBPrice = statefulService1.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
//        System.out.println("price = " + userBPrice);
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

        /*
         우리는 1만원이 나오기를 기대했는데 2만원이 나온다.
         그 이유는 ThreadA가 생성이되고 ThreadB가 생성이 되었을때 값을 공유해버리기 때문이다.
         실무에서도 매년 한번씩은 공유 필드 문제로 오류 문제가 일어난다.
         그러므로 스프링 빈은 항상 무상태로 유지해야 된다.
         */
//        System.out.println("price = " + price);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}