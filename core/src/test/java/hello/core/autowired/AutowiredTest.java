package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

//autowired는 타입으로 매칭한다. 같은 타입의 결과가 둘 이상일 경우 타입이름 + 구분자로 매칭 가능하다.

public class AutowiredTest {

    @Test
    void autowiredOption() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean  {

        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
