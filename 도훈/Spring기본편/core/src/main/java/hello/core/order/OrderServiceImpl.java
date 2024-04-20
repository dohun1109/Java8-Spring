package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
     

@Component
@RequiredArgsConstructor
@Getter
public class OrderServiceImpl implements OrderService{

    
    private  final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;  //DIP 위반(리펙토링)

    
    
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {   //SRP
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    
}
