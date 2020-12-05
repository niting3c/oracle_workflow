package com.oracle.dao;

import com.oracle.entities.PartyStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Repository
@Transactional
@Service
public interface PartyStatusDao extends PagingAndSortingRepository<PartyStatusEntity, Long> {
    public PartyStatusEntity findOneById(long id);

    public Page<PartyStatusEntity> findAll(Pageable pageable);
}
