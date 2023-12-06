package com.evg.order.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessageCode {

    DATA_NOT_FOUND(1),
    DUPLICATE_DATA(2),
    BAD_REQUEST(3),
    UNKNOWN_EXCEPTION(98),
    INTERNAL_SERVER_ERROR(99);

    int code;

    ErrorMessageCode(int code) {
        this.code = code;
    }
}
