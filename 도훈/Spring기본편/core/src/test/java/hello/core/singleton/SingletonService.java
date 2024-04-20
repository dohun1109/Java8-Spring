package hello.core.singleton;

public class SingletonService {

    //.class 생성되는 시점에 heap 영역에 딱 하나만 존재하게 된다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }
    private SingletonService(){
        //기본생성자 private 으로 외부에서 new 키워드로 생성하지 못하도록 막음.
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

    

    
}
