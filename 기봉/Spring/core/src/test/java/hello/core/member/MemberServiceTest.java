package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    // 테스트 실행 전에 무조건 실행되는 코드
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    //MemberService memberService = new MemberServiceImpl();
    @Test
    void join(){
        //given (~~한 게 주어졌을 때)
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when (~~ 했을 때)
        memberService.join(member);
        Member findMemeber = memberService.findMember(1L);

        //then (~~ 이렇게 된다)
        //member랑 findMember랑 같은지 검증
        Assertions.assertThat(member).isEqualTo(findMemeber);
    }
}
