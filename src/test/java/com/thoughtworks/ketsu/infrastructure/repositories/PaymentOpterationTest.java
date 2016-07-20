package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Map;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class PaymentOpterationTest {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    private Order order;

    @Before
    public void setUp() {
        order = prepareOrder(prepareUser(userRepository), prepareProduct(productRepository));
    }

    @Test
    public void should_pay_and_get_payment() {
        Map payInfo = paymentJsonForTest();

        order.pay(payInfo);
        Optional<Payment> fetched = order.getPayment(7l);

        assertThat(fetched.isPresent(), is(true));

    }
}
