package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    void save(@Param("info") Map orderInfo, @Param("userId") long id);

    Order findById(@Param("id") long id);

    List<Order> findAll(@Param("userId") long userId);

    void pay(@Param("info") Map payInfo, @Param("orderId") long orderId);

    Payment findPayment(@Param("orderId") long orderId);
}
