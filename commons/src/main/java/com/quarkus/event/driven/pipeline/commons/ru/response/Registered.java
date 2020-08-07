package com.quarkus.event.driven.pipeline.commons.ru.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Registered {

    private String date;
    private Integer age;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
