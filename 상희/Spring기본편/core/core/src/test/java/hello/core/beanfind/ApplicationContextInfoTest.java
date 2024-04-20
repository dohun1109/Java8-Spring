package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        // 스프링 컨테이너에 있는 Bean 들의 이름들을 String 배열로 받아준다.
        // 스프링에 등록된 모든 빈 이름을 조회한다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        // String
        for (String beanDefinitionName : beanDefinitionNames) {
            // 빈 이름으로 빈 객체(인스턴스)를 조회한다.
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + "object = " + bean);
        }

    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            // 빈에 대한 정보들
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            /*
             Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
             Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈

             BeanDefinition.ROLE_APPLICATION 애플리케이션을 주로 개발하기 위해서 등록한 빈이다. 또는 외부 라이브러리
             이렇게 출력하면 컨터이너에 넣은 Bean 만 출력된다.
             */
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "object = " + bean);
            }
        }

    }

}
