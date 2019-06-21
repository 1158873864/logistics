package com.wl.app.repository;


import com.wl.app.domain.IOSQualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOSQualificationDao extends JpaRepository<IOSQualification,Integer> {
    IOSQualification getIOSQualificationById(int id);
}
