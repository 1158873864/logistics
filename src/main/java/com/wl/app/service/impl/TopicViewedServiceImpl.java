package com.wl.app.service.impl;

import com.wl.app.service.TopicViewedService;
import com.wl.app.domain.TopicViewed;
import com.wl.app.repository.TopicViewedRepository;
import com.wl.app.repository.search.TopicViewedSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing TopicViewed.
 */
@Service
@Transactional
public class TopicViewedServiceImpl implements TopicViewedService {

    private final Logger log = LoggerFactory.getLogger(TopicViewedServiceImpl.class);

    private final TopicViewedRepository topicViewedRepository;

    private final TopicViewedSearchRepository topicViewedSearchRepository;

    public TopicViewedServiceImpl(TopicViewedRepository topicViewedRepository, TopicViewedSearchRepository topicViewedSearchRepository) {
        this.topicViewedRepository = topicViewedRepository;
        this.topicViewedSearchRepository = topicViewedSearchRepository;
    }

    /**
     * Save a topicViewed.
     *
     * @param topicViewed the entity to save
     * @return the persisted entity
     */
    @Override
    public TopicViewed save(TopicViewed topicViewed) {
        log.debug("Request to save TopicViewed : {}", topicViewed);        TopicViewed result = topicViewedRepository.save(topicViewed);
        topicViewedSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the topicVieweds.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TopicViewed> findAll(Pageable pageable) {
        log.debug("Request to get all TopicVieweds");
        return topicViewedRepository.findAll(pageable);
    }


    /**
     * Get one topicViewed by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TopicViewed> findOne(Long id) {
        log.debug("Request to get TopicViewed : {}", id);
        return topicViewedRepository.findById(id);
    }

    /**
     * Delete the topicViewed by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TopicViewed : {}", id);
        topicViewedRepository.deleteById(id);
        topicViewedSearchRepository.deleteById(id);
    }

    /**
     * Search for the topicViewed corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TopicViewed> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TopicVieweds for query {}", query);
        return topicViewedSearchRepository.search(queryStringQuery(query), pageable);    }
}
