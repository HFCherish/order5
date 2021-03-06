package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrderOperationTest {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    private User user;
    private Product product;

    @Before
    public void setUp() {
        user = prepareUser(userRepository);
        product = prepareProduct(productRepository);
    }

    @Test
    public void should_save_and_get_order() {
        Map orderInfo = orderJsonForTest(product);

        user.placeOrder(orderInfo);
        long id = Long.valueOf(orderInfo.get("id").toString());
        Optional<Order> order = user.getOrderById(id);

        assertThat(order.isPresent(), is(true));
        assertThat(order.get().getId(), is(id));
    }

    @Test
    public void should_get_all_orders() {
        Order order = prepareOrder(user, product);

        List<Order> fetched = user.getAllOrders();

        assertThat(fetched.size(), is(1));
        assertThat(fetched.get(0).getId(), is(order.getId()));
    }
}
