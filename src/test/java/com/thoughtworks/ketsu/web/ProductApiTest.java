package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductApiTest extends ApiSupport {
    private String productBaseUrl = "/products";

    @Inject
    ProductRepository productRepository;

    @Test
    public void should_create_product() {
        Response response = post(productBaseUrl, productJsonForTest());

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(productBaseUrl));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));
    }

    @Test
    public void should_400_when_create_product_given_incomplete_param() {
        //empty name
        Response response = post(productBaseUrl, new HashMap() {{
            put("description", PRODUCT_DESC);
            put("price", 1.1);
        }});

        assertThat(response.getStatus(), is(400));

        //empty description
        response = post(productBaseUrl, new HashMap() {{
            put("name", PRODUCT_NAME);
            put("price", 1.1);
        }});

        assertThat(response.getStatus(), is(400));

        //empty price
        response = post(productBaseUrl, new HashMap() {{
            put("name", PRODUCT_NAME);
            put("description", PRODUCT_DESC);
        }});

        assertThat(response.getStatus(), is(400));

    }

    @Test
    public void should_get_one_product_successfully() {
        Product product = prepareProduct(productRepository);

        String getOneUrl = productBaseUrl + "/" + product.getId();
        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(200));
        Map prodInfo = response.readEntity(Map.class);
        verifyProdResponseInfo(product, prodInfo);
    }

    private void verifyProdResponseInfo(Product product, Map prodInfo) {
        assertThat(prodInfo.get("uri"), is(productBaseUrl + "/" + product.getId()));
        assertThat(prodInfo.get("name"), is(product.getName()));
        assertThat(prodInfo.get("description"), is(product.getDescription()));
        assertThat((double)prodInfo.get("price"), is(product.getPrice()));
    }

    @Test
    public void should_404_when_get_one_product_given_invalid_id() {
        Product product = prepareProduct(productRepository);

        String getOneUrl = productBaseUrl + "/1" + product.getId();
        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(404));

    }

    @Test
    public void should_get_all_products() {
        Product product = prepareProduct(productRepository);

        Response response = get(productBaseUrl);

        assertThat(response.getStatus(), is(200));
        List prods = response.readEntity(List.class);
        assertThat(prods.size(), is(1));
        verifyProdResponseInfo(product, (Map)prods.get(0));
    }
}
