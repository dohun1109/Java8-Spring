package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // DIP 의존관 역전 원칙을 지켰다.
    // 어떤 생성자가 주입할지는 오직 외부 'AppConfig' 에서 결정한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*
    // 할인 정책을 위해 DiscountPolicy 을 선언 해준다. 구현 클래스에도 의존하고 있다. DIP 위반
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 할인 정책이 바뀌어서 10퍼센트 할인정책으로 변경 하지만 OCP 를 위반하고 있다.
    // (변경하지 한고 확장 할 수 있다고 했는데 이 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다.)
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
   */

    // 주문을 하고 나면 생성되는 createOrder 이다.
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // memberRepository 의 findById 을 찾아서
        Member member = memberRepository.findById(memberId);
        // 할인에 관한 것은 discountPrice 가 처리하기 때문에 설계가 잘된 케이스다. (단일 책임 원칙)
        int discountPrice = discountPolicy.discount (member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }


}
