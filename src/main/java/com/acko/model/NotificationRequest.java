package com.acko.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationRequest {
    @JsonProperty("team_id")
    private String teamId;
    private String message;
}
