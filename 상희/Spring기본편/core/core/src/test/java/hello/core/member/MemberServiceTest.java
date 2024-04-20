package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    // test 실행전에 실행이 된다.
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP); // 멤버 생성

        // when
        memberService.join(member); // 저장
        Member findMember = memberService.findMember(1L); // Id 를 가지고 찾는다.

        // then
        Assertions.assertThat(member).isEqualTo(findMember); // 내가 찾은 멤버와 given 에서 생성한 member 가 같은가?
    }

}
