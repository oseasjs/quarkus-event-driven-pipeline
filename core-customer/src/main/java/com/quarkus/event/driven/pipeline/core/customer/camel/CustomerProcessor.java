package com.quarkus.event.driven.pipeline.core.customer.camel;

import com.quarkus.event.driven.pipeline.commons.camel.BaseProcessor;
import com.quarkus.event.driven.pipeline.commons.mapper.CustomObjectMapper;
import com.quarkus.event.driven.pipeline.commons.ru.response.Result;
import com.quarkus.event.driven.pipeline.core.customer.service.CustomerService;
import org.apache.camel.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CustomerProcessor extends BaseProcessor {

    @Inject
    CustomerService customerService;

    protected void processEvent(Exchange exchange) {

        String json = exchange.getMessage().getBody().toString();
        Result result = CustomObjectMapper.readValue(json, Result.class);
        customerService.save(result);

    }

    protected String getModuleName() {
        return "CUSTOMER";
    }

}
