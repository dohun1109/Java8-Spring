package hello.core.singleton;

// 싱글톤 패턴 : 클래스의 인스턴스가 1개만 생성되는 것을 보장하는 디자인 패턴이다.
// 이 패턴을 사용하면 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
public class SingletonService {
    // 자기 자신을 스태틱 영역에다가 선언한다.
    private static final SingletonService instance = new SingletonService();

    // SingletonService 객체가 필요하면 오직 이 메서드만 통해서만 조회할 수 있다.
    public static SingletonService getInstance() {
        return instance;
    }

    // private 생성자를 사용해서 생성을 막는다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
