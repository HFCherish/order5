package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    Product findById(@Param("id") long id);

    void save(@Param("info") Map productInfo);

    List<Product> findAll();
}
