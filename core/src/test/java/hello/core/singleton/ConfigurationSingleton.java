package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderSerivceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingleton {

    @Test
    void configurationTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        OrderSerivceImpl orderService = applicationContext.getBean("orderService", OrderSerivceImpl.class);
        MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        //싱글톤이 깨지는 것 처럼 보이지만 실제로는 깨지지 않고, 실제로는 모두 같은 인스턴스가 공유되어 사용된다.
        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = applicationContext.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
