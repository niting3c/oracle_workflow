package com.oracle.service;

import com.oracle.customException.ApplicationException;
import com.oracle.customException.ValidationException;
import com.oracle.entities.PartyCompositeId;
import com.oracle.entities.PartyEntity;
import com.oracle.entities.PartyStatusEntity;
import com.oracle.model.PartyRequest;
import com.oracle.model.PartyResponseDto;
import com.oracle.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkflowService {


    private static final Logger LOG = LoggerFactory.getLogger(WorkflowService.class.getCanonicalName());
    @Autowired
    private EmailService emailService;
    @Autowired
    private PartyService partyService;

    public PartyRequest getSanitizedAddress(PartyRequest partyRequest) throws ApplicationException {
        // Make  a HTTP Call to needed 3rd party Service
        // Read the response and convert to PartyRequest Object
        return partyRequest;
    }

    public PartyResponseDto processRequest(PartyRequest partyRequest) throws ApplicationException {
        //find Duplicate
        try {
            PartyEntity entity = partyService.getPartyObject(partyRequest.getUserName(), partyRequest.getCompanyName());
            if (entity != null) {
                LOG.error("Duplicate Entity Exists->{}", entity.toString());
                throw new ValidationException("Duplicate Entity Exists"); //we can throw a validation exception or a normal OK as required
            }
            LOG.info("Go ahead create a new Party Request ");
            //Call a 3rd Party Service to populate more data as needed
            PartyEntity newForm = getMetaInformation(partyRequest);
            newForm = partyService.save(newForm);
            LOG.info("Successfully Store The new Party Request in the Party table");
            PartyStatusEntity statusEntity = createApprovalRequest(newForm);
            LOG.info("We have saved and got Request ID -> {}", statusEntity.getId());
            PartyResponseDto dto = new PartyResponseDto();
            dto.setLinks(emailService.communicateLink(statusEntity));
            dto.setMessage("Successfully Processed the Create Party Request");
            dto.setStatusCode(200);
            return dto;
        } catch (Exception e) {
            LOG.error("Failed to Process Request");
            throw new ApplicationException(e.getMessage());
        }
    }

    private PartyStatusEntity createApprovalRequest(PartyEntity newForm) throws ApplicationException {
        try {
            PartyStatusEntity statusEntity = new PartyStatusEntity();
            statusEntity.setCompanyId(newForm.getCompanyId());
            statusEntity.setAddress(newForm.getAddress());
            statusEntity.setPartyCompositeId(newForm.getPartyCompositeId());
            statusEntity.setAuthorizedApprovers(getApproversList().stream().collect(Collectors.joining(",")));
            statusEntity.setStatus(Constants.NON_APPROVED);
            statusEntity.setUpdatedBy(newForm.getPartyCompositeId().getUserName());
            statusEntity.setCreatedBy(newForm.getPartyCompositeId().getUserName());
            return partyService.save(statusEntity);
        } catch (Exception e) {
            LOG.error("Error occured while storing party status->{}", e.getMessage());
            throw new ApplicationException("Unable to create Party Status Entity for approvals");
        }
    }

    private List<String> getApproversList() {
        //Call a Different Service to get the List of Approvers, i am using a default list currently
        List<String> approvers = new ArrayList<>();
        approvers.add("nitin1494gupta@gmail.com");
        return approvers;
    }

    private PartyEntity getMetaInformation(PartyRequest request) {
        PartyEntity entity = new PartyEntity();
        entity.setAddress(request.getAddress());

        PartyCompositeId comp = new PartyCompositeId();
        comp.setCompanyName(request.getCompanyName());
        comp.setUserName(request.getUserName());
        entity.setPartyCompositeId(comp);
        //Get company ID from GTS and other MEta info
        entity.setCompanyId(7474);
        LOG.info("Dummy meta inserting , should be received by a 3rd Party service");
        return entity;
    }

    public void approval(String id, String status, String approver) throws ApplicationException, ValidationException {
        try {
            if (StringUtils.isEmpty(status)) {
                throw new ValidationException("Status Cannot be empty");
            }
            switch (status) {
                case Constants.APPROVED:
                case Constants.REJECTED:
                    break;
                default:
                    throw new ValidationException("Status should be -> " + Constants.APPROVED + " OR -> " + Constants.REJECTED);
            }

            PartyStatusEntity entity = partyService.findOnePartyStatus(Long.parseLong(id));
            List<String> authorized = Arrays.asList(entity.getAuthorizedApprovers().split(","));
            if (authorized.contains(approver)) {
                entity.setStatus(status);
                entity.setApprover(approver);
                entity.setUpdatedBy(approver);
                entity.setUpdatedBy(approver);
                entity.setUpdatedDate(new Date());
                partyService.save(entity);
            } else {
                throw new ApplicationException("You are not authorized to modify this request");
            }
            LOG.info("Successfully updated the Approval status");
            emailService.communicateApprovalStatus(entity);
        } catch (ValidationException e) {
            LOG.error("Error occured while Validation error->{}", e.getMessage());
            throw e;
        } catch (Exception e) {
            LOG.error("Error occured while approval error->{}", e.getMessage());
            throw new ApplicationException("Failed to Approve/Reject");
        }

    }

}
