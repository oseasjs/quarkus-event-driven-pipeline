package com.quarkus.event.driven.pipeline.core.user.camel;

import com.quarkus.event.driven.pipeline.commons.camel.BaseRouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.sqs.SqsClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.apache.camel.support.processor.idempotent.MemoryIdempotentRepository.memoryIdempotentRepository;

@ApplicationScoped
public class UserRouteBuilder extends BaseRouteBuilder {

    @Inject
    SqsClient sqsClient;

    @ConfigProperty(name = "queue.name.user")
    String queueNameUser;

    @ConfigProperty(name = "queue.name.proposal")
    String queueNameProposal;

    @ConfigProperty(name = "queue.name.dlq")
    String queueNameDlq;

    @Inject
    UserProcessor userProcessor;

    protected void configureRoute() {

        from("aws2-sqs://" + queueNameUser + "?delay=0&maxMessagesPerPoll=10")
                .idempotentConsumer(
                        body(),
                        memoryIdempotentRepository(200)
                )
                .threads(10)
                .process(userProcessor)
                .log("User saved successful")
                .to("aws2-sqs://" + queueNameProposal);

    }
    protected List<String> getQueueNameList() {
        return Arrays.asList(queueNameUser, queueNameProposal, queueNameDlq);
    }

    protected String getDlqQueueName() {
        return queueNameDlq;
    }

}
