package com.evg.order.service.impl;

import com.evg.order.dto.user.UserResponse;
import com.evg.order.dto.user.UserUpdateRequest;
import com.evg.order.service.UserService;
import com.evg.order.service.client.UserClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.evg.order.service.impl.AuthServiceImpl.USER_TOKEN;

@Service
@Slf4j(topic = "USER_SERVICE")
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserClient userClient;

    @Override
    public UserResponse increaseOrderCount(Long userId) {
        UserUpdateRequest.builder()
                .build();

        return userClient.increaseUserOrderCount(userId, UserUpdateRequest.builder()
                .build(),
                USER_TOKEN);
    }
}
