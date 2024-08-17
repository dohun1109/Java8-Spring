package hello.springmvc.basic;

import lombok.Data;

/**
 * `@Data` -> @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgusConstructor
 */
@Data
public class HelloData {

    private String username;
    private int age;

    /**
     * 프로퍼티
     * 객체에 getUsername, setUsername 메서드가 있으면 , 이객체는 username이라는 프로퍼티를 가지고 있다.
     * username 프로퍼티의 값을 변경하면 setUsername() 이 호출되고 ,조회하면 getUsername() 이 호출된다
     *
     * 수정자 프로퍼티
     * setXxx -> xxx
     * getXxx -> xxx
     */
}
