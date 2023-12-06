package com.evg.order.repository;

import com.evg.order.entity.OrderCompound;
import com.evg.order.entity.OrderProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCompoundsRepository extends JpaRepository<OrderCompound, OrderProductKey> {
}
