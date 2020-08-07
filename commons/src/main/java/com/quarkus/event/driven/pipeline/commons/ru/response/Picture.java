package com.quarkus.event.driven.pipeline.commons.ru.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Picture {

    private String large;
    private String medium;
    private String thumbnail;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
