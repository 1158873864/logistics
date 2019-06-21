package com.wl.app.service;

import com.wl.app.domain.IntegralChangeRecord;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing IntegralChangeRecord.
 */
public interface IntegralChangeRecordService {

    /**
     * Save a integralChangeRecord.
     *
     * @param integralChangeRecord the entity to save
     * @return the persisted entity
     */
    IntegralChangeRecord save(IntegralChangeRecord integralChangeRecord);

    /**
     * Get all the integralChangeRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<IntegralChangeRecord> findAll(Pageable pageable);


    /**
     * Get the "id" integralChangeRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<IntegralChangeRecord> findOne(Long id);

    /**
     * Delete the "id" integralChangeRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the integralChangeRecord corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<IntegralChangeRecord> search(String query, Pageable pageable);
}
