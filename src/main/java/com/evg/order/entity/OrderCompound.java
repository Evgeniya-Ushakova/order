package com.evg.order.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "evg", name = "ORDER_COMPOUND")
public class OrderCompound {

    @EmbeddedId
    private OrderProductKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "COUNT", columnDefinition = "numeric")
    private Long count;

}
