package com.quarkus.event.driven.pipeline.commons.ru.response;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Data
public class Name {

    private String title;
    private String first;
    private String last;
    private Map<String, Object> additionalProperties = new HashMap<>();

    public String getFullName() {
        return first.concat(StringUtils.SPACE).concat(last);
    }

}
