package com.quarkus.event.driven.pipeline.commons.ru.response;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Results {

    private List<Result> results;
    private Info info;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
