package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = applicationContext.getBean(StatefulService.class);
        StatefulService statefulService2 = applicationContext.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        int priceA = statefulService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        int priceB = statefulService2.order("userB", 20000);

        //ThreadA : A사용자 주문 금액 조회
        System.out.println("priceA = " + priceA);
        //ThreadB : B사용자 주문 금액 조회
        System.out.println("priceB = " + priceB);

        //assertThat은 Singleton 객체의 getPrice로 인해 상태가 유지되는 변수 Price를 비교하므로 통과됨 (Price는 10000 -> 20000)
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService () {
            return new StatefulService();
        }
    }
}