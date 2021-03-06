package com.wl.app.service;

import com.wl.app.domain.SysRecruitmentInformation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysRecruitmentInformation.
 */
public interface SysRecruitmentInformationService {

    /**
     * Save a sysRecruitmentInformation.
     *
     * @param sysRecruitmentInformation the entity to save
     * @return the persisted entity
     */
    SysRecruitmentInformation save(SysRecruitmentInformation sysRecruitmentInformation);

    /**
     * Get all the sysRecruitmentInformations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SysRecruitmentInformation> findAll(Pageable pageable);
    Page<SysRecruitmentInformation> findAllByTIME(int size,int page);
    public List<SysRecruitmentInformation> findAllSys();

    /**
     * Get the "id" sysRecruitmentInformation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysRecruitmentInformation> findOne(Long id);

    /**
     * Delete the "id" sysRecruitmentInformation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the sysRecruitmentInformation corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SysRecruitmentInformation> search(String query, Pageable pageable);


    Page<SysRecruitmentInformation> find(String query, Pageable pageable);

    //通过ID查询出所有的招聘信息
    List<SysRecruitmentInformation> QuereySysRecruitemByUserID(Long UserInfoId,Pageable pageable);
    List<SysRecruitmentInformation> QuereySysRecruitemByUserIDAndIDdesc(Long UserInfoId,int size,int page);
}
