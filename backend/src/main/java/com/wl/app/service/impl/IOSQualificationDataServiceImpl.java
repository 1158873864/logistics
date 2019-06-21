package com.wl.app.service.impl;

import com.wl.app.domain.IOSQualification;
import com.wl.app.repository.IOSQualificationDao;
import com.wl.app.service.IOSQualificationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IOSQualificationDataServiceImpl implements IOSQualificationDataService {
    private final IOSQualificationDao iosQualificationDao;

    @Autowired
    public IOSQualificationDataServiceImpl(IOSQualificationDao iosQualificationDao) {
        this.iosQualificationDao = iosQualificationDao;
    }

    @Override
    public IOSQualification getIOSQualificationById(int id)  {
        return iosQualificationDao.getIOSQualificationById(id);
    }
}
