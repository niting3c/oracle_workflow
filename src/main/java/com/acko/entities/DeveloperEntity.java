package com.acko.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "developer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class DeveloperEntity implements Serializable {
    @EmbeddedId
    private DeveloperCompositeEntity key;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
