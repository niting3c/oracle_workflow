package com.acko.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Developer {
    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
