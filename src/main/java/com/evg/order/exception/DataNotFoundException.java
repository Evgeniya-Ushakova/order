package com.evg.order.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class DataNotFoundException extends RuntimeException implements OrderException {

    private int code;
    private String message;

    public DataNotFoundException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }



}
