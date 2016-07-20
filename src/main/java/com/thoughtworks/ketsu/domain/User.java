package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class User implements Record{
    @Inject
    OrderMapper orderMapper;

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("uri", routes.userUrl(String.valueOf(getId())));
            put("id", getId());
            put("name", getName());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

    public void placeOrder(Map orderInfo) {
        orderMapper.save(orderInfo, getId());
    }

    public Optional<Order> getOrderById(long id) {
        return Optional.ofNullable(orderMapper.findById(id));
    }

    public List<Order> getAllOrders() {
        return orderMapper.findAll(getId());
    }
}
