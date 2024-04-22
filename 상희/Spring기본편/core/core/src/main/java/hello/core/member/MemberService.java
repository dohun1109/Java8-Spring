package hello.core.member;

// MemberService 인터페이스
// 데이터 처리를 위해 작성하는 클래스 비즈니스 로직을 구현하는 한다.
public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
}
