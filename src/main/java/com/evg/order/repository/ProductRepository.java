package com.evg.order.repository;

import com.evg.order.entity.Product;
import com.evg.order.enums.ErrorMessageCode;
import com.evg.order.exception.BadRequestException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdIn(Iterable<Long> productIds);

    Optional<Product> findById(Long productId);

    default Product findByIdOrElseThrow(Long productId) {
        return findById(productId).orElseThrow(() -> new BadRequestException(ErrorMessageCode.DATA_NOT_FOUND.getCode(),
                String.format("Product with id = %s not found", productId)));
    }

}
