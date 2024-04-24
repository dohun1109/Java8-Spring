package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 아래 시작 위치를 다 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 됨
        // 탐색할 패키지의 시작 위치를 지정, 이 패키지를 포함해서 하위 패키지를 모두 탐색
//        basePackages = "hello.core.member",
        // 지정한 클래스의 패키지를 탐색 시작 위치로 지정
//        basePackageClasses = AutoAppConfig.class,
        // @Configuration 어노테이션이 붙은 클래스를 컴포넌트 스캔 대상에서 제외 (설정 클래스가 중복 등록되는 것을 방지하기 위함)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
