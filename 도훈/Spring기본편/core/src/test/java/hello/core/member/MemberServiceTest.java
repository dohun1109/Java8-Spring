package hello.core.member;


import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given     ~주어졌을 때

        Member member = new Member(1L, "memberA", Grade.VIP);

        //when      언제
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        
        //then      일어난다.
        assertThat(member).isEqualTo(findMember);

        
    }


}
