package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url){
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close = " + url);
    }

    //의존관계 주입 종료시 호출. 인터페이스 상속, 빈 설정, 어노테이션 세가지 방법중 어노테이션을 권장함
    //코드를 고칠 수 없는 외부 메소드에 사용시 빈 설정의 initMethod, destroyMethod를 사용할 것
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지2");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
