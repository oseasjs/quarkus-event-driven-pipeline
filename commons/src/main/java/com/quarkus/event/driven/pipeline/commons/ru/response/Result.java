package com.quarkus.event.driven.pipeline.commons.ru.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    private String userId;
    private String proposalId;

    private String gender;
    private Name name;
    private Location location;
    private String email;
    private Login login;
    private Dob dob;
    private Registered registered;
    private String phone;
    private String cell;
    private Id id;
    private Picture picture;
    private String nat;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
