package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

//        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2= orderService.getMemberRepository();

//        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository  = " + memberRepository);

//        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        // AnnotationConfigApplicationContext으로 AppConfig을 넘기면 AppConfig도 Bean으로 등록이 된다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        /*
         출력 결과: bean = class hello.core.AppConfig$$SpringCGLIB$$0
         순수한 클래스라면 'class hello.core.AppConfig' 라고 출려되어야 한다. 그런데 xxxCGLIB 가 붙었다.
         스프링은 바이트 코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의 다른 클래스를 만들고,
         그 다른 클래스를 빈으로 등록을 한다. 임의로 등록해주는 클래스가 싱글톤을 보장하도록 해주기 때문이다.
         */
    }
}
