package com.quarkus.event.driven.pipeline.commons.ru.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Location {

    private String street;
    private String city;
    private String state;
    private String postcode;
    private Coordinates coordinates;
    private Timezone timezone;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
