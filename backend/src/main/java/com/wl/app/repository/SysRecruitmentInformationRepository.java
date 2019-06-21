package com.wl.app.repository;

import com.wl.app.domain.SysRecruitmentInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the SysRecruitmentInformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRecruitmentInformationRepository extends JpaRepository<SysRecruitmentInformation, Long> {
    //List<SysRecruitmentInformation> findAllByUserInfoId(Long UserInfoId);
   
}
