package hello.core.member;

// 회원 Entity
public class Member {
    private Long id; // 아이디
    private String name; // 이름
    private Grade grade; // ENUM 에서 설정한 회원 등급(VIP, Basic)

    // 기본 생성자
    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // Member 멤버들의 getter, setter 들
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
