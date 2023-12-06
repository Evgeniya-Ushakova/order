package com.evg.order.entity;

import com.evg.order.enums.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(schema = "s_order", name = "order")
@ToString(exclude = "products")
@EqualsAndHashCode(callSuper = true, exclude = {"products"})
public class Order extends EntityBase<Long> {

    @Column(name = "USER_ID", columnDefinition = "BIGINT")
    private Long userId;
    @Column(name = "ORDER_KEY", columnDefinition = "VARCHAR")
    private String orderKey;
    @Column(name = "TOTAL_PRICE", columnDefinition = "money")
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", columnDefinition = "varchar")
    private OrderStatus status = OrderStatus.NEW;

    @OneToMany(mappedBy = "order")
    private List<OrderCompound> products = new ArrayList<>();

}
