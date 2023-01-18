package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출시 마다 객체를 생성하는지 테스트
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른지를 확인(객체를 호출 시 마다 매번 생성하는지 확인)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
        //Assertions.assertThat(memberService1).isEqualTo(memberService1);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {

        //SingletonService 객체는 static으로 정의되어있고 getInstance method는 public하고 static하므로 외부에서 호출 가능
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        //same과 equal의 차이는 same은 == 이고, equal은 자바의 equals의 의미를 가짐. 즉, equal은 가진 내용만 같으면 ture
        //same은 가진 내용과 관계없이 두 객체의 주소를 비교
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        Assertions.assertThat(singletonService1).isEqualTo(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        //AppConfig appConfig = new AppConfig();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = applicationContext.getBean("memberService", MemberService.class);
        MemberService memberService2 = applicationContext.getBean("memberService", MemberService.class);

        //참조값이 다른지를 확인(객체를 호출 시 마다 매번 생성하는지 확인)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 == memberService2
        Assertions.assertThat(memberService1).isSameAs(memberService2);
        //Assertions.assertThat(memberService1).isEqualTo(memberService1);
    }
}
