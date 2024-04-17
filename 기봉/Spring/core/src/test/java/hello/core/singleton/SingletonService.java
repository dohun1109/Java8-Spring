package hello.core.singleton;

public class SingletonService {
    
    //싱글톤 패턴을 구현하는 방법은 여러가지가 있지만, 여기서는 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 씀
    private static final SingletonService instance = new SingletonService();

    //이 객체 인스턴스가 필요하면 오직 getInstance() 메소드를 통해서만 조회가 가능. 항상 같은 인스턴스를 반환.
    public static SingletonService getInstance(){
        return instance;
    }

    //딱 1개의 객체 인스턴스만 존재해야 되므로 생성자를 private로 막아서 외부에서 new 키워드로 객체 인스턴스가 생성되는걸 막음
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
