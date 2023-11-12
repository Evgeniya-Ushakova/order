package com.evg.order.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderProductKey implements Serializable {

    @Column(name = "PRODUCT_ID", columnDefinition = "BIGINT")
    private Long productId;
    @Column(name = "ORDER_ID", columnDefinition = "BIGINT")
    private Long orderId;

}
