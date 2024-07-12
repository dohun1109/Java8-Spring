package hello.item_service.domain.item;

import lombok.Getter;
import lombok.Setter;

//Dto 는 가능 그러나 확인 필요 @Data
@Getter @Setter
public class Item {


    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
