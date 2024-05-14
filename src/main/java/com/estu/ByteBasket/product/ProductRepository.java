package com.estu.ByteBasket.product;

import com.estu.ByteBasket.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findBySellerId(String sellerId);
    Optional<Product> findByName(String name);
}

