package com.oracle.service;

import com.oracle.customException.ApplicationException;
import com.oracle.entities.PartyStatusEntity;

import java.util.Map;

public interface EmailService {
    public Map<String, String> communicateLink(PartyStatusEntity entity) throws ApplicationException;

    public void communicateApprovalStatus(PartyStatusEntity entity) throws ApplicationException;
}
