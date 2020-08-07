package com.quarkus.event.driven.pipeline.commons.ru.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Login {

    private String uuid;
    private String username;
    private String password;
    private String salt;
    private String md5;
    private String sha1;
    private String sha256;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
