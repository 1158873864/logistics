package com.wl.app.service.impl;

import com.wl.app.service.IntegralRuleService;
import com.wl.app.domain.IntegralRule;
import com.wl.app.repository.IntegralRuleRepository;
import com.wl.app.repository.search.IntegralRuleSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing IntegralRule.
 */
@Service
@Transactional
public class IntegralRuleServiceImpl implements IntegralRuleService {

    private final Logger log = LoggerFactory.getLogger(IntegralRuleServiceImpl.class);

    private final IntegralRuleRepository integralRuleRepository;

    private final IntegralRuleSearchRepository integralRuleSearchRepository;

    public IntegralRuleServiceImpl(IntegralRuleRepository integralRuleRepository, IntegralRuleSearchRepository integralRuleSearchRepository) {
        this.integralRuleRepository = integralRuleRepository;
        this.integralRuleSearchRepository = integralRuleSearchRepository;
    }

    /**
     * Save a integralRule.
     *
     * @param integralRule the entity to save
     * @return the persisted entity
     */
    @Override
    public IntegralRule save(IntegralRule integralRule) {
        log.debug("Request to save IntegralRule : {}", integralRule);        IntegralRule result = integralRuleRepository.save(integralRule);
        integralRuleSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the integralRules.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IntegralRule> findAll(Pageable pageable) {
        log.debug("Request to get all IntegralRules");
        return integralRuleRepository.findAll(pageable);
    }


    /**
     * Get one integralRule by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IntegralRule> findOne(Long id) {
        log.debug("Request to get IntegralRule : {}", id);
        return integralRuleRepository.findById(id);
    }

    /**
     * Delete the integralRule by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IntegralRule : {}", id);
        integralRuleRepository.deleteById(id);
        integralRuleSearchRepository.deleteById(id);
    }

    /**
     * Search for the integralRule corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IntegralRule> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of IntegralRules for query {}", query);
        return integralRuleSearchRepository.search(queryStringQuery(query), pageable);    }
}
