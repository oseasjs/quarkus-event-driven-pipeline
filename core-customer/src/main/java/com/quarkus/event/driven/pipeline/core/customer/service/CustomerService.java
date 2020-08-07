package com.quarkus.event.driven.pipeline.core.customer.service;

import com.quarkus.event.driven.pipeline.commons.ru.response.Result;
import com.quarkus.event.driven.pipeline.core.customer.domain.Customer;
import com.quarkus.event.driven.pipeline.core.customer.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
@Slf4j
public class CustomerService {

    @Inject
    EntityManager entityManager;

    @Inject
    CustomerMapper customerMapper;

    @Transactional
    public void save(Result result) {

        Customer customer = customerMapper.toDomain(result);
        entityManager.persist(customer);

    }

}
