package com.oracle.service;

import com.oracle.customException.ApplicationException;
import com.oracle.dao.PartyDao;
import com.oracle.dao.PartyStatusDao;
import com.oracle.entities.PartyCompositeId;
import com.oracle.entities.PartyEntity;
import com.oracle.entities.PartyStatusEntity;
import com.oracle.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PartyService {

    private static final Logger LOG = LoggerFactory.getLogger(PartyService.class.getCanonicalName());

    @Autowired
    private PartyDao partyDao;

    @Autowired
    private PartyStatusDao partyStatusDao;

    public PartyEntity getPartyObject(String username, String companyName) throws ApplicationException {
        try {
            PartyCompositeId compositeId = new PartyCompositeId();
            compositeId.setCompanyName(companyName);
            compositeId.setUserName(username);
            return partyDao.findByPartyCompositeId(compositeId);
        } catch (Exception e) {
            LOG.error("Error fetching from DB->{}", e.getMessage());
            throw new ApplicationException("Failed to get Party Request");
        }
    }

    public PartyEntity save(PartyEntity entity) throws ApplicationException {
        try {
            return partyDao.save(entity);
        } catch (Exception e) {
            LOG.error("Error Storing in DB->{}", e.getMessage());
            throw new ApplicationException("Failed to store Party Object");
        }
    }

    public PartyStatusEntity save(PartyStatusEntity entity) throws ApplicationException {
        try {
            return partyStatusDao.save(entity);
        } catch (Exception e) {
            LOG.error("Error Storing in DB->{}", e.getMessage());
            throw new ApplicationException("Failed to store Party Object");
        }
    }

    public PartyStatusEntity findOnePartyStatus(long id) throws ApplicationException {
        try {
            return partyStatusDao.findOneById(id);
        } catch (Exception e) {
            LOG.error("Error fetching from DB->{}", e.getMessage());
            throw new ApplicationException("Failed to get Party Status Request");
        }
    }

    public Page<PartyEntity> getPartyList(String page, String size) throws ApplicationException {
        Pageable pageable = Util.getPageableObject(page, size);
        return partyDao.findAll(pageable);
    }

    public Page<PartyStatusEntity> getPartyStatusList(String page, String size) throws ApplicationException {
        Pageable pageable = Util.getPageableObject(page, size);
        return partyStatusDao.findAll(pageable);
    }
}
