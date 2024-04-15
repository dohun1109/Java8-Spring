package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();
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
