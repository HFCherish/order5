package com.thoughtworks.ketsu.infrastructure.repositories.Impl;

import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;

import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public void save(Map userInfo) {

    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(new User());
    }
}
