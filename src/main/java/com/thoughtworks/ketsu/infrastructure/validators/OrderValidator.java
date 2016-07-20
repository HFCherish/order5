package com.thoughtworks.ketsu.infrastructure.validators;

import java.util.List;
import java.util.Map;

public class OrderValidator implements Validator {
    @Override
    public boolean validate(Map<String, Object> info) {
        if( info.get("name") == null )
            throw new IllegalArgumentException("must contains name");
        if( info.get("address") == null )
            throw new IllegalArgumentException("must contains address");
        if( info.get("phone") == null )
            throw new IllegalArgumentException("must contains phone");

        List orderItems = (List)info.get("order_items");
        if( orderItems == null || orderItems.size() == 0)
            throw new IllegalArgumentException("must contains orderItems");
        return true;

    }
}
