package com.oracle.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PartyResponseDto {
    private String message;
    private Map<String, String> links;
    private int statusCode = 200;
}
