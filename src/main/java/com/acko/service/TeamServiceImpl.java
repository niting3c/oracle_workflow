package com.acko.service;

import com.acko.customException.ApplicationException;
import com.acko.dao.TeamDao;
import com.acko.dao.DeveloperDao;
import com.acko.entities.DeveloperCompositeEntity;
import com.acko.entities.DeveloperEntity;
import com.acko.entities.TeamEntity;
import com.acko.model.CreateTeamRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private static final Logger LOG = LoggerFactory.getLogger(TeamService.class.getCanonicalName());

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private DeveloperDao developerDao;

    @Override
    public void createTeam(CreateTeamRequest request) throws ApplicationException {
        try {
            TeamEntity teamEntity = new TeamEntity();
            final String teamId = request.getTeam().getName();
            teamEntity.setName(teamId);
            teamDao.save(teamEntity); // Upsert

            List<DeveloperEntity> developers =
                    request.getDevelopers().stream().filter(Objects::nonNull).map(dev -> {
                        DeveloperEntity entity = new DeveloperEntity();
                        DeveloperCompositeEntity compositeEntity = new DeveloperCompositeEntity();
                        entity.setName(dev.getName());
                        compositeEntity.setPhoneNumber(dev.getPhoneNumber());
                        compositeEntity.setTeamId(teamId);
                        entity.setDeveloperCompositeEntity(compositeEntity);
                        return entity;
                    }).collect(Collectors.toList());
            LOG.info("Upserting all Developers");
            developerDao.saveAll(developers);
        } catch (Exception e) {
            LOG.error("Error creating entries in database->{}",e.getMessage());
            throw new ApplicationException(e.getLocalizedMessage());
        }
    }
}
