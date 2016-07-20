package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

import java.util.HashMap;

import static com.thoughtworks.ketsu.support.TestHelper.PRODUCT_DESC;
import static com.thoughtworks.ketsu.support.TestHelper.PRODUCT_NAME;
import static com.thoughtworks.ketsu.support.TestHelper.productJsonForTest;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductApiTest extends ApiSupport {
    private String productBaseUrl = "/products";

    @Test
    public void should_create_product() {
        Response response = post(productBaseUrl, productJsonForTest());

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(productBaseUrl));
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
}
