package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //필수값인 final을 받는 생성자를 자동으로 처리해주는 롬복 라이브러리 어노테이션(@Autowired)
public class OrderSerivceImpl implements OrderService{

    //필드 주입의 경우 코드가 간결하다는 이점이 있지만 외부에서 변경이 불가하므로 테스트에 어려움이 있어서 쓰지 않음.
    //@Autowired private MemberRepository memberRepository;
    //@Autowired private DiscountPolicy discountPolicy

    //수정자의 경우 생성자 이후에 진행되기 때문에 2번의 순서를 가진다.
    //이 방식을 수정자 주입이라고 하며, 변경 가능성이 존재하는 의존관계에 사용한다.

    /* @Autowired(required = false) // 주입할 대상이 존재하지 않는다고 해도 작동한다
    public void setMemberRepository (MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy (DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }*/

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //생성자는 클래스 생성시 작동하므로 생성 순서는 1번이다.
    //생성자가 1개일 경우 @Autowired 생략 가능
    @Autowired
    public OrderSerivceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy DiscountPolicy) {
        System.out.println("1. OrderSerivceImpl.OrderSerivceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = DiscountPolicy;
    }

    //일반 메소드 주입
    //수정자 주입과 크게 다른게 없음, 일반 메소드에서 사용 가능하며 한번에 여러 필드를 주입 가능함

    /*@Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
