package com.wl.app.service;

import com.wl.app.domain.IntegralRule;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing IntegralRule.
 */
public interface IntegralRuleService {

    /**
     * Save a integralRule.
     *
     * @param integralRule the entity to save
     * @return the persisted entity
     */
    IntegralRule save(IntegralRule integralRule);

    /**
     * Get all the integralRules.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<IntegralRule> findAll(Pageable pageable);


    /**
     * Get the "id" integralRule.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<IntegralRule> findOne(Long id);

    /**
     * Delete the "id" integralRule.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the integralRule corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<IntegralRule> search(String query, Pageable pageable);
}
