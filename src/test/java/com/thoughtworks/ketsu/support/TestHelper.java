package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestHelper {

    public static final String PRODUCT_NAME = "Imran";
    public static final String PRODUCT_DESC = "teacher";
    public static final String USER_NAME = "Petrina";
    public static final String INVALID_USER_NAME = ";LH8";

    public static Map<String, Object> orderJsonForTest(Product product) {
        return new HashMap() {{
            put("name", USER_NAME);
            put("address", "beijing");
            put("phone", "678");
            put("order_items", Arrays.asList(new HashMap() {{
                put("product_id", product.getId());
                put("quantity", 2);
            }}));
        }};
    }

    public static Order prepareOrder(User user, Product product) {
        Map info = orderJsonForTest(product);
        user.placeOrder(info);
        return user.getOrderById(Long.valueOf(info.get("id").toString())).get();
    }

    public static Map<String, Object> userJsonForTest(String name) {
        return new HashMap() {{
            put("name", name);
        }};
    }

    public static User prepareUser(UserRepository userRepository) {
        Map userInfo = userJsonForTest(USER_NAME);
        userRepository.save(userInfo);
        return userRepository.findById(Long.valueOf(userInfo.get("id").toString())).get();
    }

    public static Map<String, Object> productJsonForTest() {
        return new HashMap() {{
            put("name", PRODUCT_NAME);
            put("description", PRODUCT_DESC);
            put("price", 1.1);
        }};
    }

    public static Product prepareProduct(ProductRepository productRepository) {
        Map info = productJsonForTest();
        productRepository.save(info);
        return productRepository.findById(Long.valueOf(info.get("id").toString())).get();
    }
}
