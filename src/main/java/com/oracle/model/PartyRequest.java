package com.oracle.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PartyRequest {
    private String userName;
    private String companyName;
    private int companyId;
    private String address;
}
