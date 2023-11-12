package com.evg.order.service;

import com.evg.order.dto.user.UserResponse;

public interface UserService {

    UserResponse increaseOrderCount(Long userId);

}
