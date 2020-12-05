package com.oracle.controller;

import com.oracle.customException.ApplicationException;
import com.oracle.model.PartyResponseDto;
import com.oracle.service.PartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PartyController {
    private static final Logger LOG = LoggerFactory.getLogger(PartyController.class.getCanonicalName());
    @Autowired
    private PartyService partyService;

    @RequestMapping(value = "/partyList", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> getPartyList(@RequestParam(defaultValue = "0") String page,
                                   @RequestParam(defaultValue = "10") String size) {

        try {
            return new ResponseEntity<>(partyService.getPartyList(page, size), HttpStatus.OK);
        } catch (ApplicationException ae) {
            LOG.error("Error Occured while Approving Party Request Error -> {}", ae.getMessage());
            PartyResponseDto dto = new PartyResponseDto();
            dto.setMessage(ae.getMessage());
            return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/partyStatusList", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> getPartyStatusList(@RequestParam(defaultValue = "0") String page,
                                         @RequestParam(defaultValue = "10") String size) {
        try {
            return new ResponseEntity<>(partyService.getPartyStatusList(page, size), HttpStatus.OK);
        } catch (ApplicationException ae) {
            LOG.error("Error Occured while Approving Party Request Error -> {}", ae.getMessage());
            PartyResponseDto dto = new PartyResponseDto();
            dto.setMessage(ae.getMessage());
            return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
