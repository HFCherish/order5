package com.thoughtworks.ketsu.infrastructure.repositories.Impl;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;

import java.util.Map;
import java.util.Optional;

public class ProductRespositoryImpl implements ProductRepository {
    @Override
    public void save(Map productInfo) {

    }

    @Override
    public Optional<Product> findById(Object id) {
        return Optional.ofNullable(new Product());
    }
}
