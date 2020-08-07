package com.quarkus.event.driven.pipeline.commons.ru.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Timezone {

    private String offset;
    private String description;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
