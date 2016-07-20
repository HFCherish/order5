package com.thoughtworks.ketsu.infrastructure.validators;

import java.util.Map;

public class ProductValidator implements Validator {
    @Override
    public boolean validate(Map<String, Object> info) {
        if( info.get("name") == null )
            throw new IllegalArgumentException("must contains name");
        if( info.get("description") == null )
            throw new IllegalArgumentException("must contains description");
        if( info.get("price") == null )
            throw new IllegalArgumentException("must contains price");
        return true;
    }
}
