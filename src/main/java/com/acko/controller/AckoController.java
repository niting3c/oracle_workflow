package com.acko.controller;

import com.acko.customException.ApplicationException;
import com.acko.customException.ValidationException;
import com.acko.model.CreateTeamRequest;
import com.acko.model.NotificationRequest;
import com.acko.service.NotificationService;
import com.acko.service.TeamService;
import com.acko.util.Util;
import com.acko.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AckoController {
    private static final Logger LOG = LoggerFactory.getLogger(AckoController.class.getCanonicalName());
    @Autowired
    private TeamService teamService;

    @Autowired
    private NotificationService notifyService;

    @RequestMapping(value = "/createTeam", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createTeam(@RequestBody CreateTeamRequest teamRequest) {
        try {
            ValidationUtil.validateCreateTeam(teamRequest);
            LOG.info("Successfully Validated incoming request");
            teamService.createTeam(teamRequest);
            LOG.info("Successfully Created Team for incoming request");
            return new ResponseEntity<>("Team Entry Created Successfully", HttpStatus.OK);
        } catch (ValidationException ve) {
            return new ResponseEntity<>(ve.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ApplicationException ae) {
            return new ResponseEntity<>(ae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/alert", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> alert(@RequestBody NotificationRequest request) {
        try {
            ValidationUtil.validateNotificationRequest(request);
            notifyService.alert(request);
            return new ResponseEntity<>("Notification Sent Successfully", HttpStatus.OK);
        } catch (ValidationException ve) {
            return new ResponseEntity<>(ve.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ApplicationException ae) {
            LOG.error("Error Occured while Approving Party Request Error -> {}", ae.getMessage());
            return new ResponseEntity<>(ae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
