package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UsersApiTest extends ApiSupport {
    private String usersBaseUrl = "/users";
    @Inject
    UserRepository userRepository;

    @Test
    public void should_register_successfully() {
        Response response = post(usersBaseUrl, userJsonForTest(USER_NAME));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(usersBaseUrl));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));
    }

    @Test
    public void should_400_when_register_given_invalid_name() {
        Response response = post(usersBaseUrl, userJsonForTest(INVALID_USER_NAME));

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_get_one_user_successfully() {
        User user = prepareUser(userRepository);
        String getOneUrl = usersBaseUrl + "/" + user.getId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(200));
        Map userInfo = response.readEntity(Map.class);
        assertThat(userInfo.get("uri"), is(getOneUrl));
        assertThat(userInfo.get("name"), is(user.getName()));
        assertThat(Long.valueOf(userInfo.get("id").toString()), is(user.getId()));

    }
}
