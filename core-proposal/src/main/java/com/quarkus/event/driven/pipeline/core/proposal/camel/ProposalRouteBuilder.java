package com.quarkus.event.driven.pipeline.core.proposal.camel;

import com.quarkus.event.driven.pipeline.commons.camel.BaseRouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.sqs.SqsClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.apache.camel.support.processor.idempotent.MemoryIdempotentRepository.memoryIdempotentRepository;

@ApplicationScoped
public class ProposalRouteBuilder extends BaseRouteBuilder {

    @Inject
    SqsClient sqsClient;

    @ConfigProperty(name = "queue.name.proposal")
    String queueNameProposal;

    @ConfigProperty(name = "queue.name.customer")
    String queueNameCustomer;

    @ConfigProperty(name = "queue.name.dlq")
    String queueNameDlq;

    @Inject
    ProposalProcessor proposalProcessor;

    protected void configureRoute() {

        from("aws2-sqs://" + queueNameProposal + "?delay=0&maxMessagesPerPoll=10")
                .idempotentConsumer(
                        body(),
                        memoryIdempotentRepository(200)
                )
                .threads(10)
                .process(proposalProcessor)
                .log("Proposal saved successful")
                .to("aws2-sqs://" + queueNameCustomer);

    }
    protected List<String> getQueueNameList() {
        return Arrays.asList(queueNameProposal, queueNameCustomer, queueNameDlq);
    }
    protected String getDlqQueueName() {
        return queueNameDlq;
    }

}
