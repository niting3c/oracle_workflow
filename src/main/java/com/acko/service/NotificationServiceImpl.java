package com.acko.service;

import com.acko.customException.ApplicationException;
import com.acko.dao.DeveloperDao;
import com.acko.entities.DeveloperEntity;
import com.acko.model.NotificationRequest;
import com.acko.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class.getCanonicalName());

    @Autowired
    private DeveloperDao developerDao;

    @Value("${notify.url}")
    private String notifyUrl;

    public void alert(NotificationRequest notificationRequest) throws ApplicationException {
        String teamId = notificationRequest.getTeamId();
        try {
            List<DeveloperEntity> developers = developerDao.findOneByKeyTeamId(teamId);
            if (CollectionUtils.isEmpty(developers)) {
                throw new ApplicationException("No developers Available from the Team id provided");
            }
            DeveloperEntity developer = developers.get(new Random().nextInt(developers.size()));
            LOG.info("Found developer->{} with PhoneNumber->{}",developer.getName(),developer.getKey().getPhoneNumber());
            String response = Util.MakeHttpPostCall(notifyUrl, developer);
            LOG.info("Response->{}", response);
        } catch (ApplicationException e) {
            LOG.error("Failed to find a developer for the Team id->{}", teamId);
            throw e;
        }

    }
}
