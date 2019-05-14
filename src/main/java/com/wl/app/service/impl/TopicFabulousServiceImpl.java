package com.wl.app.service.impl;

import com.wl.app.domain.Topic;
import com.wl.app.domain.UserInfo;
import com.wl.app.service.TopicFabulousService;
import com.wl.app.domain.TopicFabulous;
import com.wl.app.repository.TopicFabulousRepository;
import com.wl.app.repository.search.TopicFabulousSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing TopicFabulous.
 */
@Service
@Transactional
public class TopicFabulousServiceImpl implements TopicFabulousService {

    private final Logger log = LoggerFactory.getLogger(TopicFabulousServiceImpl.class);

    private final TopicFabulousRepository topicFabulousRepository;

    private final TopicFabulousSearchRepository topicFabulousSearchRepository;

    public TopicFabulousServiceImpl(TopicFabulousRepository topicFabulousRepository, TopicFabulousSearchRepository topicFabulousSearchRepository) {
        this.topicFabulousRepository = topicFabulousRepository;
        this.topicFabulousSearchRepository = topicFabulousSearchRepository;
    }

    /**
     * Save a topicFabulous.
     *
     * @param topicFabulous the entity to save
     * @return the persisted entity
     */
    @Override
    public TopicFabulous save(TopicFabulous topicFabulous) {
        log.debug("Request to save TopicFabulous : {}", topicFabulous);        TopicFabulous result = topicFabulousRepository.save(topicFabulous);
        topicFabulousSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the topicFabulous.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TopicFabulous> findAll(Pageable pageable) {
        log.debug("Request to get all TopicFabulous");
        return topicFabulousRepository.findAll(pageable);
    }


    /**
     * Get one topicFabulous by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TopicFabulous> findOne(Long id) {
        log.debug("Request to get TopicFabulous : {}", id);
        return topicFabulousRepository.findById(id);
    }

    /**
     * Delete the topicFabulous by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TopicFabulous : {}", id);
        topicFabulousRepository.deleteById(id);
        topicFabulousSearchRepository.deleteById(id);
    }

    /**
     * Search for the topicFabulous corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TopicFabulous> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TopicFabulous for query {}", query);
        return topicFabulousSearchRepository.search(queryStringQuery(query), pageable);    }

    @Override
    public TopicFabulous findTopicFabulousByUserInfoAndTopic(UserInfo userInfo, Topic topic) {
        return topicFabulousRepository.findTopicFabulousByUserInfoAndTopic(userInfo,topic);

    }

    @Override
    public List<TopicFabulous> findTopicFabulousByUserinfoID(Long id) {
        return topicFabulousRepository.findAllByUserInfoId(id);
    }
}
