package hello.itemservice.domain.item;


import lombok.Data;

@Data // @Data는 위험하다. 그래서 Getter, Setter를 직접 만들어주는 것이 좋다.
public class Item {
    private Long id;
    private String itemName;
    private Integer price = 0;
    private Integer quantity = 0;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
