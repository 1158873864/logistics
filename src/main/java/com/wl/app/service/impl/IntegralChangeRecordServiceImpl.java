package com.wl.app.service.impl;

import com.wl.app.service.IntegralChangeRecordService;
import com.wl.app.domain.IntegralChangeRecord;
import com.wl.app.repository.IntegralChangeRecordRepository;
import com.wl.app.repository.search.IntegralChangeRecordSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing IntegralChangeRecord.
 */
@Service
@Transactional
public class IntegralChangeRecordServiceImpl implements IntegralChangeRecordService {

    private final Logger log = LoggerFactory.getLogger(IntegralChangeRecordServiceImpl.class);

    private final IntegralChangeRecordRepository integralChangeRecordRepository;

    private final IntegralChangeRecordSearchRepository integralChangeRecordSearchRepository;

    public IntegralChangeRecordServiceImpl(IntegralChangeRecordRepository integralChangeRecordRepository, IntegralChangeRecordSearchRepository integralChangeRecordSearchRepository) {
        this.integralChangeRecordRepository = integralChangeRecordRepository;
        this.integralChangeRecordSearchRepository = integralChangeRecordSearchRepository;
    }

    /**
     * Save a integralChangeRecord.
     *
     * @param integralChangeRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public IntegralChangeRecord save(IntegralChangeRecord integralChangeRecord) {
        log.debug("Request to save IntegralChangeRecord : {}", integralChangeRecord);        IntegralChangeRecord result = integralChangeRecordRepository.save(integralChangeRecord);
        integralChangeRecordSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the integralChangeRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IntegralChangeRecord> findAll(Pageable pageable) {
        log.debug("Request to get all IntegralChangeRecords");
        return integralChangeRecordRepository.findAll(pageable);
    }


    /**
     * Get one integralChangeRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IntegralChangeRecord> findOne(Long id) {
        log.debug("Request to get IntegralChangeRecord : {}", id);
        return integralChangeRecordRepository.findById(id);
    }

    /**
     * Delete the integralChangeRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IntegralChangeRecord : {}", id);
        integralChangeRecordRepository.deleteById(id);
        integralChangeRecordSearchRepository.deleteById(id);
    }

    /**
     * Search for the integralChangeRecord corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IntegralChangeRecord> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of IntegralChangeRecords for query {}", query);
        return integralChangeRecordSearchRepository.search(queryStringQuery(query), pageable);    }
}
