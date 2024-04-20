package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @ComponentScan : 자동 으로 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능이다.
@ComponentScan(
        /*
         excludeFilters: @ComponentScan을 하면 자동으로 빈을 등록하는데 그중에서 뺄거를 등록한다.
         classes = Configuration.class: 그중에서 Configuration을 빼준다.
         Appconfig는 수동으로 등록하기 떄문이다. 안하면 충돌이 난다.
         보틍은 컴포넌트 스캔 대상으로 제외하지 않지만, 기존 에제 코드를 최대한 남기기 위해 이 방법을 선택했다.

         */
        // basePackages = "hello.core.member" , // 이런씩으로 하면 hello.core.member 패키지의 하위 폴더만 검색한다.
        // basePackageClasses = AutoAppConfig.class, // 지정한 클래스의 패키지를 탐색 시작 위로 지정한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 자동과 수동이 있으면 수동이 먼저 우선 순위이다. 스프링 부트는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꿈
    // 개발은 얘매한 상황을 만들지 마라, 이런씩으로 내가 스프링 부트에 대해 잘 안다고 수동, 자동을 해도 개발은 혼자가 하는게 아니기 때문이다.
/*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
*/

}



/*
권장하는 방법: 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는것이다.
최근 스프링 붙도 이 방버을 기본으로 제공한다. @SpringApplication를 이 프로젝트 시작 루트 위치에 두는 것이 관례이다.
(그리고 이 설정안에 @ComponetScan이 들어있다!) 그래서 스프링 부트를 쓰면 그냥 할 ComponentScan을 쓸 이유가 없다.
*/