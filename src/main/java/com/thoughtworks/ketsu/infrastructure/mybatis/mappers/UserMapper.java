package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {
    void save(@Param("info") Map userInfo);

    User findById(@Param("id") long id);
}
