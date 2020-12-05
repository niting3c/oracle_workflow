package com.oracle.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "party_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PartyStatusEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private PartyCompositeId partyCompositeId;
    private String authorizedApprovers;
    private int companyId;
    private String address;
    private String status;
    private String approver;
    private Date createdDate = new Date();
    private Date updatedDate = new Date();
    private String updatedBy;
    private String createdBy;
}
