package com.acko.dao;

import com.acko.entities.DeveloperCompositeEntity;
import com.acko.entities.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeveloperDao extends JpaRepository<DeveloperEntity,Long> {

    //@Query("select * from DeveloperEntity dev where dev.team_id= ?1")
    List<DeveloperEntity> findOneByKeyTeamId(String teamId);
}
