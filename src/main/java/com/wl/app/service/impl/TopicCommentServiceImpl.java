package com.wl.app.service.impl;

import com.wl.app.domain.Topic;
import com.wl.app.service.TopicCommentService;
import com.wl.app.domain.TopicComment;
import com.wl.app.repository.TopicCommentRepository;
import com.wl.app.repository.search.TopicCommentSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing TopicComment.
 */
@Service
@Transactional
public class TopicCommentServiceImpl implements TopicCommentService {

    private final Logger log = LoggerFactory.getLogger(TopicCommentServiceImpl.class);

    private final TopicCommentRepository topicCommentRepository;

    private final TopicCommentSearchRepository topicCommentSearchRepository;

    public TopicCommentServiceImpl(TopicCommentRepository topicCommentRepository, TopicCommentSearchRepository topicCommentSearchRepository) {
        this.topicCommentRepository = topicCommentRepository;
        this.topicCommentSearchRepository = topicCommentSearchRepository;
    }

    /**
     * Save a topicComment.
     *
     * @param topicComment the entity to save
     * @return the persisted entity
     */
    @Override
    public TopicComment save(TopicComment topicComment) {
        log.debug("Request to save TopicComment : {}", topicComment);        TopicComment result = topicCommentRepository.save(topicComment);
        topicCommentSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the topicComments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TopicComment> findAll(Pageable pageable) {
        log.debug("Request to get all TopicComments");
        return topicCommentRepository.findAll(pageable);
    }


    /**
     * Get one topicComment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TopicComment> findOne(Long id) {
        log.debug("Request to get TopicComment : {}", id);
        return topicCommentRepository.findById(id);
    }

    /**
     * Delete the topicComment by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TopicComment : {}", id);
        topicCommentRepository.deleteById(id);
        topicCommentSearchRepository.deleteById(id);
    }

    /**
     * Search for the topicComment corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TopicComment> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TopicComments for query {}", query);
        return topicCommentSearchRepository.search(queryStringQuery(query), pageable);    }

    @Override
    public Page<TopicComment> findbyTopic(Pageable pageable, Topic topic) {
        topicCommentRepository.findTopicCommentByTopic(topic,pageable);
        return null;
    }

    @Override
    public TopicComment findTopicCommentsById(long id) {
        topicCommentRepository.findTopicCommentsById(id);
        return null;
    }
}
