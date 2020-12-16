package com.acko.service;

import com.acko.customException.ApplicationException;
import com.acko.model.CreateTeamRequest;

public interface TeamService {
    public void createTeam(CreateTeamRequest request)throws ApplicationException;
}
