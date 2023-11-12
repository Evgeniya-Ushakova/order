package com.evg.order.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(schema = "evg", name = "product")
@EqualsAndHashCode(callSuper = true)
public class Product extends EntityBase<Long> {

    @Column(name = "NAME", columnDefinition = "VARCHAR(50)")
    private String name;
    @Column(name = "DESCRIPTION", columnDefinition = "text")
    private String description;
    @Column(name = "COST", columnDefinition = "decimal")
    private BigDecimal cost;

    @OneToMany(mappedBy = "product")
    private List<OrderCompound> orders;

}
