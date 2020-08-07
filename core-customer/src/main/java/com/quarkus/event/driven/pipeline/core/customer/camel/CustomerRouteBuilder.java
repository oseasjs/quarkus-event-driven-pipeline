package com.quarkus.event.driven.pipeline.core.customer.camel;

import com.quarkus.event.driven.pipeline.commons.camel.BaseRouteBuilder;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.apache.camel.support.processor.idempotent.MemoryIdempotentRepository.memoryIdempotentRepository;

@Slf4j
@ApplicationScoped
public class CustomerRouteBuilder extends BaseRouteBuilder {

    @ConfigProperty(name = "queue.name.customer")
    String queueNameCustomer;

    @ConfigProperty(name = "queue.name.dlq")
    String queueNameDlq;

    @Inject
    CustomerProcessor customerProcessor;

    protected void configureRoute() {

        from("aws2-sqs://" + queueNameCustomer + "?delay=0&maxMessagesPerPoll=10")
                .idempotentConsumer(
                        body(),
                        memoryIdempotentRepository(200)
                )
                .threads(10)
                .process(customerProcessor)
                .log("Customer saved successful");

    }
    protected List<String> getQueueNameList() {
        return Arrays.asList(queueNameCustomer, queueNameDlq);
    }
    protected String getDlqQueueName() {
        return queueNameDlq;
    }

}
