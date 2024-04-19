package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier: 추가 구분자를 붙여주는 방법 (빈 이름을 변경하는것은 아님)
//@Primary: 우선순위를 정하는 방법. @Autowired 시 여러 빈이 매칭되면 @Primary가 우선권을 가짐
@MainDiscountPolicy
//@Primary
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;  // 10% 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return (price * discountPercent) / 100;
        } else {
            return 0;
        }
    }
}
