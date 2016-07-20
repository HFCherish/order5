package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.User;

import java.util.Map;
import java.util.Optional;

public interface UserRepository {
    void save(Map userInfo);

    Optional<User> findById(long id);
}
