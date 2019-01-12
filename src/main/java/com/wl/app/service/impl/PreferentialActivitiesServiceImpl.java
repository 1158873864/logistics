package com.wl.app.service.impl;

import com.wl.app.service.PreferentialActivitiesService;
import com.wl.app.domain.PreferentialActivities;
import com.wl.app.repository.PreferentialActivitiesRepository;
import com.wl.app.repository.search.PreferentialActivitiesSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PreferentialActivities.
 */
@Service
@Transactional
public class PreferentialActivitiesServiceImpl implements PreferentialActivitiesService {

    private final Logger log = LoggerFactory.getLogger(PreferentialActivitiesServiceImpl.class);

    private final PreferentialActivitiesRepository preferentialActivitiesRepository;

    private final PreferentialActivitiesSearchRepository preferentialActivitiesSearchRepository;

    public PreferentialActivitiesServiceImpl(PreferentialActivitiesRepository preferentialActivitiesRepository, PreferentialActivitiesSearchRepository preferentialActivitiesSearchRepository) {
        this.preferentialActivitiesRepository = preferentialActivitiesRepository;
        this.preferentialActivitiesSearchRepository = preferentialActivitiesSearchRepository;
    }

    /**
     * Save a preferentialActivities.
     *
     * @param preferentialActivities the entity to save
     * @return the persisted entity
     */
    @Override
    public PreferentialActivities save(PreferentialActivities preferentialActivities) {
        log.debug("Request to save PreferentialActivities : {}", preferentialActivities);        PreferentialActivities result = preferentialActivitiesRepository.save(preferentialActivities);
        preferentialActivitiesSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the preferentialActivities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PreferentialActivities> findAll(Pageable pageable) {
        log.debug("Request to get all PreferentialActivities");
        return preferentialActivitiesRepository.findAll(pageable);
    }


    /**
     * Get one preferentialActivities by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PreferentialActivities> findOne(Long id) {
        log.debug("Request to get PreferentialActivities : {}", id);
        return preferentialActivitiesRepository.findById(id);
    }

    /**
     * Delete the preferentialActivities by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PreferentialActivities : {}", id);
        preferentialActivitiesRepository.deleteById(id);
        preferentialActivitiesSearchRepository.deleteById(id);
    }

    /**
     * Search for the preferentialActivities corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PreferentialActivities> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PreferentialActivities for query {}", query);
        return preferentialActivitiesSearchRepository.search(queryStringQuery(query), pageable);    }
}
