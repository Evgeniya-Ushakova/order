package com.evg.order.service.impl;

import com.evg.order.dto.order.request.CreateOrderRequest;
import com.evg.order.dto.order.response.CreateOrderResponse;
import com.evg.order.dto.order.ProductDto;
import com.evg.order.dto.user.UserResponse;
import com.evg.order.entity.Order;
import com.evg.order.entity.OrderCompound;
import com.evg.order.entity.OrderProductKey;
import com.evg.order.enums.ErrorMessageCode;
import com.evg.order.enums.OrderStatus;
import com.evg.order.exception.BadRequestException;
import com.evg.order.repository.OrderCompoundsRepository;
import com.evg.order.repository.OrderRepository;
import com.evg.order.service.CamundaService;
import com.evg.order.service.OrderService;
import com.evg.order.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "ORDER_SERVICE")
public class OrderServiceImpl implements OrderService {

    private final OrderCompoundsRepository orderCompoundsRepository;
    private final OrderRepository orderRepository;
    private final CamundaService camundaService;
    private final UserService userService;

    @Override
    @Transactional
    public CreateOrderResponse create(CreateOrderRequest request, String idempotencyKey) {
        if (orderRepository.existsByUserIdAndOrderKey(request.getUserId(), idempotencyKey)) {
            throw new BadRequestException(ErrorMessageCode.BAD_REQUEST.getCode(),
                    String.format("Order %s already exists for user %s", idempotencyKey, request.getUserId()));
        }

        Order order = new Order();
        order.setOrderKey(idempotencyKey);
        order.setTotalPrice(request.getTotalPrice());
        order.setUserId(request.getUserId());
        order.setStatus(OrderStatus.NEW);
        orderRepository.save(order);

        List<OrderCompound> orderCompounds = getProducts(request.getProducts(), order);
        order.setProducts(orderCompounds);
        orderRepository.save(order);

        //обновим количество заказов у юзера
        UserResponse userResponse = userService.increaseOrderCount(request.getUserId());

        LOGGER.info("Count of orders: {}, userId: {}", userResponse.getOrderCount(), userResponse.getId());
        return CreateOrderResponse.builder()
                .orderId(order.getId())
                .orderKey(idempotencyKey)
                .message("Order created succeed")
                .status(order.getStatus())
                .build();
    }

    @Override
    @Transactional
    public CreateOrderResponse removeOrder(String idempotencyKey) {
        orderRepository.findByOrderKey(idempotencyKey).ifPresent(order -> {
            orderRepository.deleteByOrderKey(idempotencyKey);
        });

        return CreateOrderResponse.builder()
                .orderKey(idempotencyKey)
                .message("Order not created")
                .code(ErrorMessageCode.INTERNAL_SERVER_ERROR.getCode())
                .build();
//        orderRepository.findByOrderKey(idempotencyKey).ifPresent(order -> {
//            orderCompoundsRepository.deleteAllById(or);
//        });
    }

    @Override
    @Transactional(readOnly = true)
    public CreateOrderResponse getOrder(String orderKey) {
        Order order = orderRepository.findByOrderKeyOrElseThrow(orderKey);
        return CreateOrderResponse.builder()
                .orderId(order.getId())
                .orderKey(order.getOrderKey())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .build();
    }

    private List<OrderCompound> getProducts(List<ProductDto> productDtos,
                                            Order order) {
        List<OrderCompound> orderCompounds = productDtos
                .stream()
                .map(productDto -> {
                    OrderProductKey orderProductKey = new OrderProductKey();
                    orderProductKey.setProductId(productDto.getId());
                    orderProductKey.setOrderId(order.getId());
                    OrderCompound orderCompound = new OrderCompound();
                    orderCompound.setId(orderProductKey);
                    orderCompound.setCount(productDto.getCount());
                    orderCompound.setOrder(order);
                    return orderCompound;
                })
                .collect(Collectors.toList());
        return orderCompoundsRepository.saveAll(orderCompounds);
    }

}
