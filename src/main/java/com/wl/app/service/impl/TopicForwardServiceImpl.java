package com.wl.app.service.impl;

import com.wl.app.service.TopicForwardService;
import com.wl.app.domain.TopicForward;
import com.wl.app.repository.TopicForwardRepository;
import com.wl.app.repository.search.TopicForwardSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing TopicForward.
 */
@Service
@Transactional
public class TopicForwardServiceImpl implements TopicForwardService {

    private final Logger log = LoggerFactory.getLogger(TopicForwardServiceImpl.class);

    private final TopicForwardRepository topicForwardRepository;

    private final TopicForwardSearchRepository topicForwardSearchRepository;

    public TopicForwardServiceImpl(TopicForwardRepository topicForwardRepository, TopicForwardSearchRepository topicForwardSearchRepository) {
        this.topicForwardRepository = topicForwardRepository;
        this.topicForwardSearchRepository = topicForwardSearchRepository;
    }

    /**
     * Save a topicForward.
     *
     * @param topicForward the entity to save
     * @return the persisted entity
     */
    @Override
    public TopicForward save(TopicForward topicForward) {
        log.debug("Request to save TopicForward : {}", topicForward);        TopicForward result = topicForwardRepository.save(topicForward);
        topicForwardSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the topicForwards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TopicForward> findAll(Pageable pageable) {
        log.debug("Request to get all TopicForwards");
        return topicForwardRepository.findAll(pageable);
    }


    /**
     * Get one topicForward by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TopicForward> findOne(Long id) {
        log.debug("Request to get TopicForward : {}", id);
        return topicForwardRepository.findById(id);
    }

    /**
     * Delete the topicForward by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TopicForward : {}", id);
        topicForwardRepository.deleteById(id);
        topicForwardSearchRepository.deleteById(id);
    }

    /**
     * Search for the topicForward corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TopicForward> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TopicForwards for query {}", query);
        return topicForwardSearchRepository.search(queryStringQuery(query), pageable);    }
}
