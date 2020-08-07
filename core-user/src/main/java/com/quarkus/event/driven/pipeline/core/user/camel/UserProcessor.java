package com.quarkus.event.driven.pipeline.core.user.camel;

import com.quarkus.event.driven.pipeline.commons.camel.BaseProcessor;
import com.quarkus.event.driven.pipeline.commons.mapper.CustomObjectMapper;
import com.quarkus.event.driven.pipeline.commons.ru.response.Result;
import com.quarkus.event.driven.pipeline.core.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class UserProcessor extends BaseProcessor {

    @Inject
    UserService userService;

    protected void processEvent(Exchange exchange) {

        String json = exchange.getMessage().getBody().toString();

        Result result = CustomObjectMapper.readValue(json, Result.class);

        userService.save(result);

        // O atributo proposalId foi atualizado e precisa ser passado para a geração do customer
        String updatedJson = CustomObjectMapper.writeValueAsString(result);

        exchange.getIn().setBody(updatedJson);

    }

    protected String getModuleName() {
        return "USER";
    }

}
