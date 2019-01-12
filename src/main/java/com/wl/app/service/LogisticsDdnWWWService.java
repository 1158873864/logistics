package com.wl.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wl.app.domain.LogisticsDdnWWW;
import com.wl.app.domain.enumeration.Status;

/**
 * Service Interface for managing LogisticsDdnWWW.
 */
public interface LogisticsDdnWWWService {

    /**
     * Save a logisticsDdnWWW.
     *
     * @param logisticsDdnWWW the entity to save
     * @return the persisted entity
     */
    LogisticsDdnWWW save(LogisticsDdnWWW logisticsDdnWWW);

    /**
     * Get all the logisticsDdnWWWS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LogisticsDdnWWW> findAll(Pageable pageable);


    /**
     * Get the "id" logisticsDdnWWW.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LogisticsDdnWWW> findOne(Long id);

    /**
     * Delete the "id" logisticsDdnWWW.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the logisticsDdnWWW corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LogisticsDdnWWW> search(String query, Pageable pageable);
    
    List<LogisticsDdnWWW> findAllByLogisticsDdnIdAndStatus(long logisticsDdnId,Status status);

    public boolean batchImportDDN(String path);
}
