package com.evg.order.dto.user;

import com.evg.order.dto.BaseResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends BaseResponse {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Long orderCount;

}
