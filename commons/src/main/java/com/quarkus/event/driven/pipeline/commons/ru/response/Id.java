package com.quarkus.event.driven.pipeline.commons.ru.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Id {

    private String name;
    private String value;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
