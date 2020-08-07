package com.quarkus.event.driven.pipeline.commons.ru.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Info {

    private String seed;
    private Integer results;
    private Integer page;
    private String version;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
