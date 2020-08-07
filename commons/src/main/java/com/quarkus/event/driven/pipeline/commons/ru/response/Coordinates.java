package com.quarkus.event.driven.pipeline.commons.ru.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Coordinates {

    private String latitude;
    private String longitude;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
