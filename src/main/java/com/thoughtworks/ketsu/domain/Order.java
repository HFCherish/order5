package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.*;

public class Order implements Record{
    private long id;
    private long userId;
    private String name;
    private String address;
    private String phone;
    private List<OrderItem> orderItems;
    private DateTime createdAt;

    @Inject
    OrderMapper orderMapper;

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public long getUserId() {
        return userId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        List items = new ArrayList<>();
        for(OrderItem orderItem: orderItems) {
            items.add(orderItem.toJson(routes));
        }

        return new HashMap<String, Object>() {{
            put("uri", routes.orderUrl(getUserId(), getId()));
            put("name", getName());
            put("address", getAddress());
            put("phone", getPhone());
            put("created_at", getCreatedAt());
            put("total_price", getTotalPrice());
            put("order_items", items);
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for(OrderItem item: orderItems) {
            totalPrice += item.getAmount() * item.getQuantity();
        }
        return totalPrice;
    }

    public void pay(Map payInfo) {
        orderMapper.pay(payInfo, getId());
    }

    public Optional<Payment> getPayment() {
        return Optional.ofNullable(orderMapper.findPayment(getId()));
    }
}
