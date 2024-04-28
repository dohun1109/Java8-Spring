package hello.core.test;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class NoUniqueBeanTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void ThisIsTest() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String s : beanDefinitionNames) {
            System.out.println("beanDefinitionNames = " + s);
        }
    }
}
