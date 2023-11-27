package com.evg.order.service.client;

import com.evg.order.dto.user.UserResponse;
import com.evg.order.dto.user.UserUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user", path = "/user")
public interface UserClient {

    @PutMapping("/{userId}")
    UserResponse increaseUserOrderCount(@PathVariable(value = "userId") Long userId,
                                        @RequestBody UserUpdateRequest request,
                                        @RequestHeader("x-auth-token") String token);

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable(value = "userId") Long userId, @RequestHeader("x-auth-token") String token);

}
