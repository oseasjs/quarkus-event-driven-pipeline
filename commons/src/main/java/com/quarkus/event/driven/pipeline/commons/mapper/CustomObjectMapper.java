package com.quarkus.event.driven.pipeline.commons.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class CustomObjectMapper {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
            return mapper.readValue(json, clazz);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String writeValueAsString(T obj) {
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
            return mapper.writeValueAsString(obj);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
