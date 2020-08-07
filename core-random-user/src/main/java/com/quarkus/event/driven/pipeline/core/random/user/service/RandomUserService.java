package com.quarkus.event.driven.pipeline.core.random.user.service;

import com.quarkus.event.driven.pipeline.commons.ru.request.RandomUserDto;
import com.quarkus.event.driven.pipeline.commons.ru.response.Results;
import com.quarkus.event.driven.pipeline.core.random.user.camel.RandomUserProducer;
import com.quarkus.event.driven.pipeline.core.random.user.client.RandomUserClient;
import org.apache.commons.collections4.ListUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@ApplicationScoped
public class RandomUserService {

    @RestClient
    RandomUserClient userRandomClient;

    @Inject
    RandomUserProducer producer;

    public Results findSingleUser() {
        RandomUserDto dto = new RandomUserDto();
        dto.setSize(1);
        return generate(dto);
    }

    public void generateRandomUser(RandomUserDto dto) throws Exception{

        ExecutorService resultsExecutor = Executors.newSingleThreadExecutor();
        ExecutorService resultThreadpool = Executors.newFixedThreadPool(5);

        Results results = generate(dto);

        // Split results to send a batch with 10 users to queue
        ListUtils
                .partition(results.getResults(), 10)
                .forEach(list -> resultThreadpool.execute(() -> producer.sendMessageToUserQueue(list)));

    }

    private Results generate(RandomUserDto dto) {

        // Find random users from: https://randomuser.me/documentation
        Results results = userRandomClient.getUsers(dto.getSize(),
                dto.getGender(),
                dto.getNatList()
                        .stream()
                        .collect(Collectors.joining(",")));

        // Generate randomic id to be used as document number
        results
                .getResults()
                .stream()
                .forEach(u -> {
                    u.getId().setValue("" + System.nanoTime());
                });

        return results;

    }

}
