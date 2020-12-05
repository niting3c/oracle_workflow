package com.oracle.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "party")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PartyEntity implements Serializable {
    @EmbeddedId

    private PartyCompositeId partyCompositeId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int companyId;
    private String address;
    private String meta;
    private Date createdDate = new Date();
    private Date updatedDate = new Date();

}
