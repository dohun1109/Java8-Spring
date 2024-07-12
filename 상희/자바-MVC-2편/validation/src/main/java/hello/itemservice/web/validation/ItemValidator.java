package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// 스프링 빈 등록
@Component
public class ItemValidator implements Validator {

    // true이면 validate가 검증을 시작한다.
    @Override
    public boolean supports(Class<?> clazz) {
        // 파라미터로 넘어오는 클래스가 Item에 지원이 되는지 확인하는 코드
        return Item.class.isAssignableFrom(clazz);
        // item == claszz 아이템이랑 같으면 true
        // item == subItem 자식 클래스라도 true
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName", "required"); // rejectValue을 이해할려면 messageRecolver을 이해해야한다.
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        // 특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

    }
}
