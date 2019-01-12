package com.wl.app.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wl.app.domain.LogisticsDdnPic;
import com.wl.app.domain.enumeration.Status;
import com.wl.app.repository.LogisticsDdnPicRepository;
import com.wl.app.repository.search.LogisticsDdnPicSearchRepository;
import com.wl.app.service.LogisticsDdnPicService;

/**
 * Service Implementation for managing LogisticsDdnPic.
 */
@Service
@Transactional
public class LogisticsDdnPicServiceImpl implements LogisticsDdnPicService {

    private final Logger log = LoggerFactory.getLogger(LogisticsDdnPicServiceImpl.class);

    private final LogisticsDdnPicRepository logisticsDdnPicRepository;

    private final LogisticsDdnPicSearchRepository logisticsDdnPicSearchRepository;

    public LogisticsDdnPicServiceImpl(LogisticsDdnPicRepository logisticsDdnPicRepository, LogisticsDdnPicSearchRepository logisticsDdnPicSearchRepository) {
        this.logisticsDdnPicRepository = logisticsDdnPicRepository;
        this.logisticsDdnPicSearchRepository = logisticsDdnPicSearchRepository;
    }

    /**
     * Save a logisticsDdnPic.
     *
     * @param logisticsDdnPic the entity to save
     * @return the persisted entity
     */
    @Override
    public LogisticsDdnPic save(LogisticsDdnPic logisticsDdnPic) {
        log.debug("Request to save LogisticsDdnPic : {}", logisticsDdnPic);        LogisticsDdnPic result = logisticsDdnPicRepository.save(logisticsDdnPic);
        logisticsDdnPicSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the logisticsDdnPics.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LogisticsDdnPic> findAll(Pageable pageable) {
        log.debug("Request to get all LogisticsDdnPics");
        return logisticsDdnPicRepository.findAll(pageable);
    }


    /**
     * Get one logisticsDdnPic by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LogisticsDdnPic> findOne(Long id) {
        log.debug("Request to get LogisticsDdnPic : {}", id);
        return logisticsDdnPicRepository.findById(id);
    }

    /**
     * Delete the logisticsDdnPic by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LogisticsDdnPic : {}", id);
        logisticsDdnPicRepository.deleteById(id);
        logisticsDdnPicSearchRepository.deleteById(id);
    }

    /**
     * Search for the logisticsDdnPic corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LogisticsDdnPic> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of LogisticsDdnPics for query {}", query);
        return logisticsDdnPicSearchRepository.search(queryStringQuery(query), pageable);    }

	@Override
	public List<LogisticsDdnPic> findAllByLogisticsDdnIdAndStatus(long logisticsDdnId, Status status) {
		return logisticsDdnPicRepository.findAllByLogisticsDdnIdAndStatus(logisticsDdnId, status);
	}
}
