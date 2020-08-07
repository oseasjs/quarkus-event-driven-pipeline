package com.quarkus.event.driven.pipeline.core.proposal.camel;

import com.quarkus.event.driven.pipeline.commons.camel.BaseProcessor;
import com.quarkus.event.driven.pipeline.commons.mapper.CustomObjectMapper;
import com.quarkus.event.driven.pipeline.commons.ru.response.Result;
import com.quarkus.event.driven.pipeline.core.proposal.service.ProposalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class ProposalProcessor extends BaseProcessor {

    @Inject
    ProposalService proposalService;

    protected void processEvent(Exchange exchange) {

        String json = exchange.getMessage().getBody().toString();

        Result result = CustomObjectMapper.readValue(json, Result.class);

        proposalService.save(result);

        // O atributo proposalId foi atualizado e precisa ser passado para a geração do customer
        String updatedJson = CustomObjectMapper.writeValueAsString(result);

        exchange.getIn().setBody(updatedJson);

    }

    protected String getModuleName() {
        return "PROPOSAL";
    }

}
