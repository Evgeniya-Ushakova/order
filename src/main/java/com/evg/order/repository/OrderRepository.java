package com.evg.order.repository;

import com.evg.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByUserIdAndOrderKey(Long userId, String idempotencyKey);


}