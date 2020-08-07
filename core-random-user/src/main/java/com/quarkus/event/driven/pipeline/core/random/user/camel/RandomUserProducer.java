package com.quarkus.event.driven.pipeline.core.random.user.camel;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quarkus.event.driven.pipeline.commons.ru.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchRequestEntry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@ApplicationScoped
public class RandomUserProducer {

    @Inject
    SqsAsyncClient asyncClient;

    @ConfigProperty(name = "quarkus.sqs.endpoint-override")
    String queueUrl;

    @ConfigProperty(name = "queue.name.user")
    String queueNameUser;

    ObjectMapper mapper = new ObjectMapper();

    public void sendMessageToUserQueue(List<Result> list) {

        try {

            List<SendMessageBatchRequestEntry> entries = new ArrayList<>();
            list.forEach(dto -> entries.add(SendMessageBatchRequestEntry
                    .builder()
                    .id(UUID.randomUUID().toString())
                    .messageBody(toJson(dto))
                    .build()));

            asyncClient.sendMessageBatch(SendMessageBatchRequest.builder()
                    .queueUrl(queueUrl.concat(queueNameUser))
                    .entries(entries)
                    .build());

            Thread.sleep(500);

            log.info("Batch user process of RU successful: {}", list.size());

        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String toJson(Object obj) {
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
            return mapper.writeValueAsString(obj);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
