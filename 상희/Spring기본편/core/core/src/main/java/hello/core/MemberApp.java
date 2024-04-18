package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        /*
        스프링 컨테이너를 사용하지 않고 사용하는 예제
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        MemberService memberService = new MemberServiceImpl(); 기존 코드
        */


        // @Bean 어노테이션을 전부 관리해 준다.
        // AppConfig 에 있는 환경 설정 정보를 가지고 스프링이 @Bean 어노테이션이 붙어있는 메서드를 스프링 컨테이너에 넣는다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 기본적으로 메서드 이름으로 등록 된다.
        // memberService 을 반환하고 타입은 MemberService(반환 타입도 맞다) 이다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
