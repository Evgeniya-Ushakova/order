package com.evg.order.service.client;

import com.evg.order.dto.user.UserResponse;
import com.evg.order.dto.user.UserUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user", url = "${user.server}", path = "/user")
public interface UserClient {

    @PutMapping("/{userId}")
    UserResponse increaseUserOrderCount(@PathVariable(value = "userId") Long userId,
                                        @RequestBody UserUpdateRequest request,
                                        @RequestHeader(value = "x-auth-token", required = false) String token);

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable(value = "userId") Long userId, @RequestHeader(value = "x-auth-token", required = false) String token);

}
