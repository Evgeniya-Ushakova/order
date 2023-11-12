package com.evg.order.service;

public interface AuthService {

    void checkAuth(String authToken, Long userIdFromRequest);

}
