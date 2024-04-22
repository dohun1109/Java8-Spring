package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// MemberService 을 implements 을 했다.
@Component // 자동으로 빈으로 등록된다.
public class MemberServiceImpl implements MemberService {
    // MemberRepository 을 가져와서 데이터를 접근한다.
    // 다형성을 위해서 MemberRepository 을 선언 후 MemoryMemberRepository 을 대입해준다.
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // DI 의존관계 주입 외부에서 주입해주는거 같아서 생성 자체는 AppConfig 가 해준다.
    // DIP 의존관 역전 원칙을 지켰다.
    private final MemberRepository memberRepository;

    @Autowired // 의존 관계 주입을 대신해준다. 마치 ac.getBean(MemberRepository.class) 처럼
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // MemberRepository 에는 save() 라는 메서드가 있다.
    // 이 메서드는 메모리 저장소에 직접적으로 데이터를 저장하는 메서드가 구현되어 있다.
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    // memberRepository 에 있는 findById를 통해서 멤버를 찾는다.
    // 멤버를 찾으면 return을 한다.
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
//    public MemberRepository getMemberRepository() {
//        return memberRepository;
//    }
}
