package com.oracle.util;

import com.oracle.customException.ValidationException;
import com.oracle.entities.PartyStatusEntity;
import com.oracle.model.EmailBodyRequest;
import com.oracle.model.PartyRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public class Util {
    public static void validatePartyRequest(PartyRequest partyRequest) throws ValidationException {
        if (StringUtils.isEmpty(partyRequest.getAddress())) {
            throw new ValidationException("Invalid Address");
        }
        if (StringUtils.isEmpty(partyRequest.getUserName())) {
            throw new ValidationException("Invalid Username");
        }
        if (StringUtils.isEmpty(partyRequest.getCompanyName())) {
            throw new ValidationException("Invalid Company Name");
        }
    }


    public static String getUserNameFromCookie(String cookie) {
        return "nitin1494gupta@gmail.com";
    }

    public static EmailBodyRequest generateStatusEmail(PartyStatusEntity entity) {
        EmailBodyRequest req = new EmailBodyRequest();
        req.setApiKey("dummy");
        req.setBody("Request has been " + entity.getStatus());
        req.setFrom("no-reply@oracle.com");
        req.setSubject("Update on the new Party Request");
        String[] to = {entity.getPartyCompositeId().getUserName(), entity.getApprover()};
        req.setTo(to);
        return req;
    }

    public static EmailBodyRequest generateApprovalEmailTemplate(PartyStatusEntity entity, Map<String, String> links) {
        EmailBodyRequest req = new EmailBodyRequest();
        req.setApiKey("dummy");
        req.setBody("Email Template with proper Link and button goes here");
        req.setFrom("no-reply@oracle.com");
        req.setSubject("Party Request Approval Needed");
        req.setTo(entity.getAuthorizedApprovers().split(","));
        return req;
    }

    public static Pageable getPageableObject(String page, String size) {
        return PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
    }
}
