package com.thoughtworks.ketsu.infrastructure.repositories.Impl;

import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.UserMapper;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Inject
    UserMapper userMapper;

    @Override
    public void save(Map userInfo) {
        userMapper.save(userInfo);
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(userMapper.findById(id));
    }
}
