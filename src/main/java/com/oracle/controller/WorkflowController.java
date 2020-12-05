package com.oracle.controller;

import com.oracle.customException.ApplicationException;
import com.oracle.customException.ValidationException;
import com.oracle.model.PartyRequest;
import com.oracle.model.PartyResponseDto;
import com.oracle.service.WorkflowService;
import com.oracle.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkflowController {

    private static final Logger LOG = LoggerFactory.getLogger(WorkflowController.class.getCanonicalName());

    @Autowired
    private WorkflowService workflowService;

    @RequestMapping(value = "/createParty", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createParty(@RequestBody PartyRequest partyRequest) {
        PartyResponseDto dto = new PartyResponseDto();
        try {
            Util.validatePartyRequest(partyRequest);
            LOG.info("Validation Successfull");
            LOG.info("Processing Address now");

            partyRequest = workflowService.getSanitizedAddress(partyRequest); //address will be modified if needed
            return new ResponseEntity<>(workflowService.processRequest(partyRequest), HttpStatus.OK);
        } catch (ValidationException ve) {
            //checking all exceptions, can check if some specific one is needed
            LOG.error("Validation Error -> {}", ve.getMessage());
            dto.setMessage(ve.getMessage());
            dto.setStatusCode(400);
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        } catch (ApplicationException ae) {
            dto.setMessage(ae.getMessage());
            dto.setStatusCode(501);
            LOG.error("Error Occured while creating an party entry with Error -> {}", ae.getMessage());
            return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/approval", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> approval(@RequestParam String id, @RequestParam String status, @RequestHeader(value = "cookie", required = false) String cookie) {
        PartyResponseDto dto = new PartyResponseDto();
        try {
            //read some authToken from the cookie and get Username approving the same
            String approver = Util.getUserNameFromCookie(cookie); // for some authentication and authorization purpose
            workflowService.approval(id, status, approver);
            return new ResponseEntity<>("Successfully Processed the Approval", HttpStatus.OK);
        } catch (ValidationException ve) {
            //checking all exceptions, can check if some specific one is needed
            dto.setMessage(ve.getMessage());
            dto.setStatusCode(400);
            LOG.error("Validation Error -> {}", ve.getMessage());
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        } catch (ApplicationException ae) {
            dto.setMessage(ae.getMessage());
            dto.setStatusCode(501);
            LOG.error("Error Occured while Approving Party Request Error -> {}", ae.getMessage());
            return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
