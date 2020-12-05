package com.oracle.model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EmailBodyRequest {
    private String[] to;
    private String body;
    private String subject;
    private String apiKey;
    private String from;
}
