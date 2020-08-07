package com.quarkus.event.driven.pipeline.commons.camel;

import org.apache.camel.builder.RouteBuilder;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;

import javax.inject.Inject;
import java.util.List;

public abstract class BaseRouteBuilder extends RouteBuilder {

    @Inject
    SqsClient sqsClient;

    public void configure() {

        createQueuesIfNotExists();

        onException(Exception.class)
                .maximumRedeliveries(1)
                .log("ERROR: Fail on message processing")
                .to("aws2-sqs://" + getDlqQueueName())
                .log("Sending message to DLQ")
                .handled(true);

        configureRoute();;

    }

    private void createQueuesIfNotExists() {

        List<String> existingQueueList = sqsClient.listQueues().queueUrls();
        List<String> queueList = getQueueNameList();

        queueList.stream()
                .filter(queueName -> existingQueueList
                        .stream()
                        .filter(existingQueue -> existingQueue.contains(queueName))
                        .findAny()
                        .isEmpty())
                .forEach(queueName -> {

                    log.info("### Creating required queue: {}", queueName);

                    sqsClient.createQueue(CreateQueueRequest
                            .builder()
                            .queueName(queueName)
                            .build());

                });

    }

    protected abstract void configureRoute();
    protected abstract List<String> getQueueNameList();
    protected abstract String getDlqQueueName();

}
