package com.wl.app.service.impl;

import com.wl.app.service.IOSQualificationBlService;
import com.wl.app.service.IOSQualificationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IOSQualificationBlServiceImpl implements IOSQualificationBlService {
    private final IOSQualificationDataService iosQualificationDataService;

    @Autowired
    public IOSQualificationBlServiceImpl(IOSQualificationDataService iosQualificationDataService) {
        this.iosQualificationDataService = iosQualificationDataService;
    }

    @Override
    public boolean getIOSQualification() {
        return iosQualificationDataService.getIOSQualificationById(1).isStatus();
    }

    @Override
    public boolean getAppQualification() {
        return iosQualificationDataService.getIOSQualificationById(2).isStatus();
    }
}
