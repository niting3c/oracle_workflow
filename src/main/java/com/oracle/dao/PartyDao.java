package com.oracle.dao;

import com.oracle.entities.PartyCompositeId;
import com.oracle.entities.PartyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Repository
@Transactional
@Service
public interface PartyDao extends PagingAndSortingRepository<PartyEntity, String> {
    public PartyEntity findByPartyCompositeId(PartyCompositeId compositeId);
    public Page<PartyEntity> findAll(Pageable pageable);
}
