package com.evg.order.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @Builder.Default
    private Long orderCountToIncrease = 1L;

}
