package com.acko.dao;


import com.acko.entities.TeamEntity;
import org.springframework.data.repository.CrudRepository;

public interface TeamDao extends CrudRepository<TeamEntity,Long> {

}
