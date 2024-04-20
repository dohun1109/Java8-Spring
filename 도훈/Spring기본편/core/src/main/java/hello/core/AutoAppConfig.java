package hello.core;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration //@ 붙은 에노테이션의 경우 자바가 지원하는게 아니라 스프링프레임워크가 지원하는 기능이다.
@ComponentScan(
//        basePackages = "hello.core", default 값
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
                classes = Configuration.class) //기존 appConfig
)
public class AutoAppConfig {

//     @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//         return new MemoryMemberRepository();
//     }
    
}
