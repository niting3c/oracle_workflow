package com.acko.util;

import com.acko.customException.ValidationException;
import com.acko.model.CreateTeamRequest;
import com.acko.model.Developer;
import com.acko.model.NotificationRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ValidationUtil {
    public static void validateCreateTeam(CreateTeamRequest request) throws ValidationException {
        if (StringUtils.isEmpty(request.getTeam().getName())) {
            throw new ValidationException("Team Id is Mandatory");
        }
        if (CollectionUtils.isEmpty(request.getDevelopers())) {
            throw new ValidationException("No developers in the team");
        }
        List<Developer> invalid = request.getDevelopers().stream()
                .filter(Objects::nonNull)
                .filter(a -> StringUtils.isEmpty(a.getPhoneNumber()))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(invalid)) {
            String message = "Phone Number is Mandatory , please provide the same for the following developers";
            String nameList = invalid.stream()
                    .map(a -> a.getName())
                    .collect(Collectors.joining(","));
            message = message + " -> " + nameList;
            throw new ValidationException(message);
        }
    }

    public static void validateNotificationRequest(NotificationRequest request) throws ValidationException {
        if (StringUtils.isEmpty(request.getTeamId())) {
            throw new ValidationException("Team Id is Mandatory");
        }
        if (StringUtils.isEmpty(request.getMessage())) {
            throw new ValidationException("Alert message is Empty");
        }

    }
}
