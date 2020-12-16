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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Id
    private String name;
}
