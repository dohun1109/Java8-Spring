package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

// TARGET_CLASS : 적용할 대상이 인터페이스가 아닌 클래스일때 사용
// INTERFACE : 인터페이스면 사용
// MyLogger의 가짜 클래스를 만들어두고 HTTP request와 상관없이 가짜 프록시 클래스를 다른 빈에 미리 주입할 수 있다.
@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }


    // Bean 생성될때 생성
    @PostConstruct
    public void init() {
        // Id를 랜덤으로 만들어준다. (유저 식별용)
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope been create:" + this);
    }

    // Bean 사라질때 생성
    @PreDestroy
    public void destroy() {
        System.out.println("[" + uuid + "] request scope been close" + this);
    }
}
