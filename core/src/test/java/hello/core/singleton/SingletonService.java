package hello.core.singleton;

//Singleton은 객체 인스턴스를 하나만 생성해 공유하기때문에 상태를 유지해선 안된다.
//특정 클라이언트에 의존적인 필드가 있으면 안되고, 값을 변경가능한 필드가 있어선 안된다. 가급적 읽기만 가능해야한다.
//공유값을 사용해선 안된다
//순수 JAVA 코드로 짠 Singleton Pattern
public class SingletonService {

    //1. static 영역에 객체를 1개 생성
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스턴스가 필요하다면 static 메서드를 통해서만 조회 가능하도록 한다.
    public static SingletonService getInstance() {
        return instance;
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 통한 객체 생성을 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
