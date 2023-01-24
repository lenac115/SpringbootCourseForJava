package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy") // qualifier는 qualifier끼리 묶는게 좋음 @Primary를 붙이는 경우 해당 어노테이션이 붙은 빈이 우선권을 지님
//spring은 세세한 것이 우선권을 지니므로 qualifier보다 primary가 우선권이 낮음
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP){
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
