package com.acko.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DeveloperCompositeEntity implements Serializable {

    @Column(name = "team_id")
    private String teamId;
    @Column(name = "phone_number")
    private String phoneNumber;
}
