package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Product;

import java.util.Map;
import java.util.Optional;

public interface ProductRepository {
    void save(Map productInfo);

    Optional<Product> findById(Object id);
}
