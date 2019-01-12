package com.wl.app.service.impl;

import com.wl.app.service.SysRecruitmentInformationService;
import com.wl.app.domain.SysRecruitmentInformation;
import com.wl.app.repository.SysRecruitmentInformationRepository;
import com.wl.app.repository.search.SysRecruitmentInformationSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing SysRecruitmentInformation.
 */
@Service
@Transactional
public class SysRecruitmentInformationServiceImpl implements SysRecruitmentInformationService {

    private final Logger log = LoggerFactory.getLogger(SysRecruitmentInformationServiceImpl.class);

    private final SysRecruitmentInformationRepository sysRecruitmentInformationRepository;

    private final SysRecruitmentInformationSearchRepository sysRecruitmentInformationSearchRepository;

    public SysRecruitmentInformationServiceImpl(SysRecruitmentInformationRepository sysRecruitmentInformationRepository, SysRecruitmentInformationSearchRepository sysRecruitmentInformationSearchRepository) {
        this.sysRecruitmentInformationRepository = sysRecruitmentInformationRepository;
        this.sysRecruitmentInformationSearchRepository = sysRecruitmentInformationSearchRepository;
    }

    /**
     * Save a sysRecruitmentInformation.
     *
     * @param sysRecruitmentInformation the entity to save
     * @return the persisted entity
     */
    @Override
    public SysRecruitmentInformation save(SysRecruitmentInformation sysRecruitmentInformation) {
        log.debug("Request to save SysRecruitmentInformation : {}", sysRecruitmentInformation);        SysRecruitmentInformation result = sysRecruitmentInformationRepository.save(sysRecruitmentInformation);
        sysRecruitmentInformationSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the sysRecruitmentInformations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysRecruitmentInformation> findAll(Pageable pageable) {
        log.debug("Request to get all SysRecruitmentInformations");
        return sysRecruitmentInformationRepository.findAll(pageable);
    }


    /**
     * Get one sysRecruitmentInformation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysRecruitmentInformation> findOne(Long id) {
        log.debug("Request to get SysRecruitmentInformation : {}", id);
        return sysRecruitmentInformationRepository.findById(id);
    }

    /**
     * Delete the sysRecruitmentInformation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysRecruitmentInformation : {}", id);
        sysRecruitmentInformationRepository.deleteById(id);
        sysRecruitmentInformationSearchRepository.deleteById(id);
    }

    /**
     * Search for the sysRecruitmentInformation corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysRecruitmentInformation> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of SysRecruitmentInformations for query {}", query);
        return sysRecruitmentInformationSearchRepository.search(queryStringQuery(query), pageable);    }
}
