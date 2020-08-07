package com.quarkus.event.driven.pipeline.core.user.service;

import com.quarkus.event.driven.pipeline.commons.ru.response.Result;
import com.quarkus.event.driven.pipeline.core.user.domain.User;
import com.quarkus.event.driven.pipeline.core.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
@Slf4j
public class UserService {

    @Inject
    EntityManager entityManager;

    @Inject
    UserMapper userMapper;

    @Transactional
    public void save(Result result) {

        User user = userMapper.toDomain(result);
        entityManager.persist(user);
        result.setUserId(user.getId().toString());

    }

}
