package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 구체 클래스를 선택한다. 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다.
 이 AppConfig 가 있어서 OCP 원칙을 지킬 수 있다.
 AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 ioC 또는 DI 컨테이너라고 부른다.
 AppConfig 는 공연 기획자(DI)다.
 */

// 싱글톤: Configuration이 없으면 싱글톤이 깨진다.
@Configuration // 애플리케이션의 설정 정보
public class AppConfig {

    /*
     스프링 AppConfig(DI)을 설정하는 방식은 크게 두가지 있다.
     - java 코드로 쓰면 팩토리 메소드(Factory Bean) 방법 (스프링은 팩토리 메서드 방식으로 얘기한다.)
     - xml는 직접 스프링 컨테이너를 등록하는 방법 (xml 방식은 잘 안씀)

     @Bean을 붙이면 스프링 컨테이너라는 곳에다가 등록이 된다.
     Bean 이 붙은 메서드 명을 스프링 빈의 이름으로 사용한다. @Bean(name = "name") 으로 이름을 바꿔도 된다
     특별한 경우가 아니면 관례(기본 이름)를 따르는게 좋다. 그리고 @Bean 이 붙은 메서드들을 스프링 빈이라고 한다.
     Bean의 이름은 유니크 해야된다. 겹치면 덮어쓰거나 오류가 난다.
    */

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    /*
    ConfigurationSingletonTest.class 코드

    현재 memberService()를 호출하면서 memberRepository()가 한번 호출되고
    그리고 orderService()를 호출하면서 memberRepository()가 한번 더 호출 되고
    마지막으로 memberRepository() 호출되면서 memberRepository()가 한번 더 호출이될꺼 같다.
    하지만 실제 테스트 코드를 작성해서 실해을 해보면 memberRepository() 한번만 실행이 되었다.
    그 이유는 @Configuration에 있다.
     */
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
         return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // 이제 이렇게 코드를 변경해도 외부에서 변경 즉 DI가 변경을 해줘서 내부 코드를 전혀 바꿀 필요가 없다.
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
