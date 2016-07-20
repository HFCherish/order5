package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrderApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    private String orderBaseUrl;
    private User user;
    private Product product;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        user = prepareUser(userRepository);
        product = prepareProduct(productRepository);
        orderBaseUrl = "/users/" + user.getId() + "/orders";
    }

    @Test
    public void should_create_order_successfully() {
        Response response = post(orderBaseUrl, orderJsonForTest(product));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(orderBaseUrl));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));
    }

    @Test
    public void should_400_when_create_order_given_input_without_name() {
        Map orderInfo = orderJsonForTest(product);

        //name empty
        orderInfo.remove("name");
        Response response = post(orderBaseUrl, orderInfo);
        assertThat(response.getStatus(), is(400));

    }

    @Test
    public void should_400_when_create_order_given_input_without_order_items() {
        Map orderInfo = orderJsonForTest(product);

        //orderItems empty
        orderInfo.remove("order_items");
        Response response = post(orderBaseUrl, orderInfo);
        assertThat(response.getStatus(), is(400));

    }

    @Test
    public void should_get_one_order() {
        Order order = prepareOrder(user, product);
        String getOneUrl = orderBaseUrl + "/" + order.getId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(200));
        Map orderInfo = response.readEntity(Map.class);
        verifyBasicOrderResponseInfo(order, orderInfo);

        List orderItems = (List)orderInfo.get("order_items");
        assertThat(orderItems.size(), is(1));
        Map fetchedOrderItem = (Map) orderItems.get(0);
        OrderItem item = order.getOrderItems().get(0);
        assertThat(Long.valueOf(fetchedOrderItem.get("product_id").toString()), is(item.getProductId()));
        assertThat((double)fetchedOrderItem.get("amount"), is(item.getAmount()));
        assertThat(fetchedOrderItem.get("quantity"), is(item.getQuantity()));

    }

    private void verifyBasicOrderResponseInfo(Order order, Map orderInfo) {
        assertThat(orderInfo.get("uri"), is(orderBaseUrl + "/" + order.getId()));
        assertThat(orderInfo.get("name"), is(order.getName()));
        assertThat(orderInfo.get("address"), is(order.getAddress()));
        assertThat(orderInfo.get("phone"), is(order.getPhone()));
        assertThat((double)orderInfo.get("total_price"), is(order.getTotalPrice()));
        assertThat(new DateTime(orderInfo.get("created_at")), is(order.getCreatedAt()));
    }

    @Test
    public void should_404_when_get_order_given_invalid_id() {

        Order order = prepareOrder(user, product);
        String getOneUrl = orderBaseUrl + "/1" + order.getId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_get_all_orders() {
        Order order = prepareOrder(user, product);

        Response response = get(orderBaseUrl);

        assertThat(response.getStatus(), is(200));
        List ordersInfo = response.readEntity(List.class);
        assertThat(ordersInfo.size(), is(1));
        verifyBasicOrderResponseInfo(order, (Map)ordersInfo.get(0));

    }


}
