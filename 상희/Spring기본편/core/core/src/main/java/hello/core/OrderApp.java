package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        /*
        솔리드 5원칙에서 DIP 원칙 의존 관계를 어겼다.
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();
         */

        // 이 밑에 있는 코드들은 DIP 원칙을 어기지 않았다.
        // 'applicationContext' 를 스프링 컨테이너라고 한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService(); 와 동일한 역할을 한다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        // OrderService orderService = appConfig.memberService(); 와 동일한 역할을 한다.
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order.toString());
//        System.out.println("order = " + order.calculatePrice());
    }
}
