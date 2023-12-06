package com.evg.order.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "s_order", name = "ORDER_COMPOUND")
public class OrderCompound {

    @EmbeddedId
    private OrderProductKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "COUNT", columnDefinition = "numeric")
    private Long count;

}
