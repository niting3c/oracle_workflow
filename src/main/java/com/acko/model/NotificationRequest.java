package com.acko.model;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationRequest {
    private String teamId;
    private String message;
}
