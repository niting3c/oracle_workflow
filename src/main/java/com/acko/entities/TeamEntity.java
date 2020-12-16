package com.acko.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TeamEntity implements Serializable {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
