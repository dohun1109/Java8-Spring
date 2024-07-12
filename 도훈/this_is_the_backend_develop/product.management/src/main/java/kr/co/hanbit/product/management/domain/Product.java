package kr.co.hanbit.product.management.domain;

import java.util.Objects;

/**
 * Product는 구체적으로 Entity 라고 부를 수 있다.
 * 도메인 객체이면서 id를 가지는 존재를 Entity
 * 도메인 객체이면서 id를 가지지않는 존재를 VO(Value Object);값 객체 라고 부른다.
 */
public class Product {

    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 도메인 객체에 대한 getter와 setter가 반드시 필요한 경우가 아니라면 getter, setter 를 사용하지않고
     * 최대한 표현력 있는 메서드를 만들어 사용하자.
     * 이러한 습관이 도메인 객체를 캡슐화시켜 객체지향적인 코드 만들기에 도움이 된다.
     */

    public Boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public Boolean containsName(String name) {
        return this.name.contains(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) ;
    }

}
