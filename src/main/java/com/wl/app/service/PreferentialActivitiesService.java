package com.wl.app.service;

import com.wl.app.domain.PreferentialActivities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PreferentialActivities.
 */
public interface PreferentialActivitiesService {

    /**
     * Save a preferentialActivities.
     *
     * @param preferentialActivities the entity to save
     * @return the persisted entity
     */
    PreferentialActivities save(PreferentialActivities preferentialActivities);

    /**
     * Get all the preferentialActivities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PreferentialActivities> findAll(Pageable pageable);


    /**
     * Get the "id" preferentialActivities.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PreferentialActivities> findOne(Long id);

    /**
     * Delete the "id" preferentialActivities.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the preferentialActivities corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PreferentialActivities> search(String query, Pageable pageable);
}
