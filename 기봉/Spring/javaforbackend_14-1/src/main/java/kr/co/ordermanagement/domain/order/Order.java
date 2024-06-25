package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.exception.CanNotCancellableStateException;
import kr.co.ordermanagement.domain.product.Product;

import java.util.List;

public class Order {
    private Long id;
    private List<OrderedProduct> orderedProducts;
    private Integer totalPrice;
    private State state;

    public Order(List<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
        this.totalPrice = calculateTotalPrice(orderedProducts);
        this.state = State.CREATED;
    }

    public Long getId() {
        return id;
    }

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public State getState() {
        return state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public void changeStateForce(State state) {
        this.state = state;
    }

    private Integer calculateTotalPrice(List<OrderedProduct> orderedProducts) {
        return orderedProducts
                .stream()
                .mapToInt(orderedProduct -> orderedProduct.getPrice() * orderedProduct.getAmount())
                .sum();
    }

    public boolean sameState(State state) {
        return this.state.equals(state);
    }

    public void cancel() {
        this.state.checkCancellable();
        this.state = State.CANCELED;
    }
}
