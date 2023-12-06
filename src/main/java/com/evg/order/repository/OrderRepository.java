package com.evg.order.repository;

import com.evg.order.entity.Order;
import com.evg.order.enums.ErrorMessageCode;
import com.evg.order.exception.BadRequestException;
import com.evg.order.exception.DataNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByUserIdAndOrderKey(Long userId, String idempotencyKey);

    void deleteByOrderKey(String orderKey);

    Optional<Order> findByOrderKey(String orderKey);

    default Order findByOrderKeyOrElseThrow(String orderKey) {
        return findByOrderKey(orderKey).orElseThrow(() -> new DataNotFoundException(ErrorMessageCode.DATA_NOT_FOUND.getCode(),
                String.format("Order with key %s was not found", orderKey)));
    }


}