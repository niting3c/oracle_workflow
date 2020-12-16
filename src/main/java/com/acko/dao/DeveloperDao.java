package com.acko.dao;

import com.acko.entities.DeveloperEntity;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperDao extends CrudRepository<DeveloperEntity,Long> {
    DeveloperEntity findOnebyTeamId(String teamId);
}
