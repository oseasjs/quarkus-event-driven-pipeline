package com.quarkus.event.driven.pipeline.commons.ru.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RandomUserDto {

    private Integer size;
    private List<String> natList = new ArrayList<>();
    private String gender;

}
