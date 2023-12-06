package com.evg.order.dto.order.request;

import com.evg.order.dto.order.ProductDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @NotEmpty
    private List<ProductDto> products;
    @NotNull
    private BigDecimal totalPrice;
    @NotNull
    private Long userId;
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime timeTo;
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime timeFrom;
    @NotEmpty
    private String address;

}
