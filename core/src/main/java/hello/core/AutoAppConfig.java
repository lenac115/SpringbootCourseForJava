package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//컴포넌트 스캔은 @Component뿐만 아니라 다음의 내용도 추가로 대상에 포함한다
//@Component = 컴포넌트 스캔에서 사용
//@Controller = 스프링 MVC 컨트롤러에서 사용, 스프링 MVC 컨트롤러로 인식
//@Service = 스프링 비즈니스 로직에서 사용, 스프링이 특별한 처리는 하지 않지만 개발자들이 비즈니스 로직을 인식할 수 있도록 도움
//@Repository = 스프링 데이터 접근 계층에서 사용, 스프링 데이터 접근 계층으로 인식하고 데이터 계층의 예외를 스프링 예외로 변환
//@Configuration = 스프링 설정 정보에서 사용, 스프링 설정 정보를 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가처리

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {


    //자동 빈 등록과 수동 빈 등록이 같은 이름으로 겹칠 경우, 수동 빈 등록이 우선권을 가지며 오버라이딩 된다.
    //그러나 버그가 발생할 가능성이 생기므로 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꾸었다.
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
