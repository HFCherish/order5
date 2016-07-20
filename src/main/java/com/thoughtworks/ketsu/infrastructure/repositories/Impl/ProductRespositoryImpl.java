package com.thoughtworks.ketsu.infrastructure.repositories.Impl;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRespositoryImpl implements ProductRepository {
    @Inject
    ProductMapper productMapper;

    @Override
    public void save(Map productInfo) {
        productMapper.save(productInfo);
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(productMapper.findById(id));
    }

    @Override
    public List<Product> findAll() {
        return Arrays.asList(new Product());
    }
}
