package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{

        //@Autowired(required = false): 자동 주입할 대상이 없으면 수정자 메소드 자체가 호출이 안됨
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }
        @Autowired(required = false)
        //@Nullable: 자동 주입할 대상이 없으면 수정자 메소드 자체가 호출 안됨
        public void setNoBean2(@Nullable Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        //Optional<>: 자동 주입할 대상이 없으면 Optional.empty가 입력됨
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
