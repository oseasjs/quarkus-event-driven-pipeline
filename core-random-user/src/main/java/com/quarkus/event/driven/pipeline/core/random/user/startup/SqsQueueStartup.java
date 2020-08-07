package com.quarkus.event.driven.pipeline.core.random.user.startup;

import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Startup
public class SqsQueueStartup {

    @Inject
    SqsClient sqsClient;

    @ConfigProperty(name = "queue.name.user")
    String queueNameUser;

    @ConfigProperty(name = "queue.name.dlq")
    String queueNameDlq;

    void onStart(@Observes StartupEvent ev) {

        List<String> existingQueueList = sqsClient.listQueues().queueUrls();
        List<String> queueList = Arrays.asList(queueNameUser, queueNameDlq);

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

}

