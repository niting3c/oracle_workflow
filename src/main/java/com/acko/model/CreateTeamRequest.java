package com.acko.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateTeamRequest {

    private Team team;
    private List<Developer> developers;
}
