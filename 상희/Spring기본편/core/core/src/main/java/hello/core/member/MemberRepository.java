package hello.core.member;

// MemberRepository 인터페이스
// 데이터베이스 테이블에 접근하는 메서드들을 사용하기 위한 인터페이스
public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
