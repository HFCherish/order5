package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.USER_NAME;
import static com.thoughtworks.ketsu.support.TestHelper.userJsonForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UserRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Test
    public void should_save_and_get_user() {
        Map userInfo = userJsonForTest(USER_NAME);

        userRepository.save(userInfo);
        Long id = Long.valueOf(userInfo.get("id").toString());
        Optional<User> fetched = userRepository.findById(id);

        assertThat(fetched.isPresent(), is(true));
        assertThat(fetched.get().getId(), is(id));
    }
}
