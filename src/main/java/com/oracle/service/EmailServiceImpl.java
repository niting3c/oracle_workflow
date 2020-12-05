package com.oracle.service;

import com.oracle.customException.ApplicationException;
import com.oracle.entities.PartyStatusEntity;
import com.oracle.model.EmailBodyRequest;
import com.oracle.util.Constants;
import com.oracle.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class.getCanonicalName());
    @Value("${env.url}")
    private String envUrl;

    @Override
    public Map<String, String> communicateLink(PartyStatusEntity entity) throws ApplicationException {
        //create an email template here and generate URLs to approve or Deny
        try {
            Map<String, String> links = new HashMap<>();

            links.put("APPROVE", envUrl + "/approval?id=" + entity.getId() + "&status="+ Constants.APPROVED);
            links.put("REJECT", envUrl + "/approval?id=" + entity.getId() + "&status="+Constants.REJECTED);
            EmailBodyRequest req = Util.generateApprovalEmailTemplate(entity, links);
            communicate(req);
            return links;
        } catch (Exception e) {
            LOG.error("Error Communicating->{}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void communicateApprovalStatus(PartyStatusEntity entity) throws ApplicationException {
        EmailBodyRequest req = Util.generateStatusEmail(entity);
        communicate(req);
    }

    private void communicate(EmailBodyRequest req) {
        //Write API Call here
    }


}
