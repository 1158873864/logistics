package com.wl.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wl.app.domain.LogisticsDdnPic;
import com.wl.app.domain.enumeration.Status;

/**
 * Service Interface for managing LogisticsDdnPic.
 */
public interface LogisticsDdnPicService {

    /**
     * Save a logisticsDdnPic.
     *
     * @param logisticsDdnPic the entity to save
     * @return the persisted entity
     */
    LogisticsDdnPic save(LogisticsDdnPic logisticsDdnPic);

    /**
     * Get all the logisticsDdnPics.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LogisticsDdnPic> findAll(Pageable pageable);


    /**
     * Get the "id" logisticsDdnPic.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LogisticsDdnPic> findOne(Long id);

    /**
     * Delete the "id" logisticsDdnPic.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the logisticsDdnPic corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LogisticsDdnPic> search(String query, Pageable pageable);
    
    List<LogisticsDdnPic> findAllByLogisticsDdnIdAndStatus(long logisticsDdnId,Status status);

    Page<LogisticsDdnPic> find(String query, Pageable pageable);
}
