package com.evg.order.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(schema = "order", name = "order")
@ToString(exclude = "products")
@EqualsAndHashCode(callSuper = true, exclude = {"products"})
public class Order extends EntityBase<Long> {

    @Column(name = "USER_ID", columnDefinition = "BIGINT")
    private Long userId;
    @Column(name = "ORDER_KEY", columnDefinition = "VARCHAR")
    private String orderKey;
    @Column(name = "TOTAL_PRICE", columnDefinition = "money")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderCompound> products;

}
