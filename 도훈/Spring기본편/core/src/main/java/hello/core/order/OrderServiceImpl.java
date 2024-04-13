package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;


public class OrderServiceImpl implements OrderService{


    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;  //DIP 위반(리펙토링)

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {   //SRP
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
