package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component // @Component: Bean을 안 달아줘도 된다.
// MemoryMemberRepository 은 MemberRepository 을 구현해서 데이터를 가져오거나 저장한다.
public class MemoryMemberRepository implements MemberRepository {

    // HashMap<>을 사용 (딕셔너리)
    private static Map<Long, Member> store = new HashMap<>();

    // HashMap 에다가 멤버의 아이디을 key, 그리고 value 로는 member 클래스 자체를 넣는 메서드
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    // 멤버의 아이디를 통해 멤버를 찾는 메서드
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
