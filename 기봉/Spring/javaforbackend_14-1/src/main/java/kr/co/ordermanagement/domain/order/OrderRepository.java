package kr.co.ordermanagement.domain.order;

import java.util.List;

public interface OrderRepository {
    Order add(Order order);
    Order findById(Long orderId);
    List<Order> findByState(State state);
}
