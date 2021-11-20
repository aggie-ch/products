package io.github.aggie.orders;

import io.github.aggie.common.validator.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order add(@Validate(exception = InvalidOrderException.class) Order order) {
        return orderRepository.save(order);
    }

    public Order getBy(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }

    public void update(Order order) {
        orderRepository.save(order);
    }
}
