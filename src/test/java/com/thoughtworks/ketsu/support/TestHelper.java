package com.thoughtworks.ketsu.support;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {

    public static final String PRODUCT_NAME = "Imran";
    public static final String PRODUCT_DESC = "teacher";

    public static Map<String, Object> productJsonForTest() {
        return new HashMap() {{
            put("name", PRODUCT_NAME);
            put("description", PRODUCT_DESC);
            put("price", 1.1);
        }};
    }
}
