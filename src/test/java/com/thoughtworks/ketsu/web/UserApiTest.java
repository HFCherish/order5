package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

import static com.thoughtworks.ketsu.support.TestHelper.userJsonForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UserApiTest extends ApiSupport {
    private String usersBaseUrl = "/users";

    @Test
    public void should_register_successfully() {
        Response response = post(usersBaseUrl, userJsonForTest());

        assertThat(response.getStatus(), is(201));
    }
}
