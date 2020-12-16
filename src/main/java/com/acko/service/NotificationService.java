package com.acko.service;

import com.acko.customException.ApplicationException;
import com.acko.model.NotificationRequest;

public interface NotificationService {
    public void alert(NotificationRequest request)throws ApplicationException;
}
