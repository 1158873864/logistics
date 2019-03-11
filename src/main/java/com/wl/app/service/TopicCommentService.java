package com.wl.app.service;

import com.wl.app.domain.Topic;
import com.wl.app.domain.TopicComment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TopicComment.
 */
public interface TopicCommentService {

    /**
     * Save a topicComment.
     *
     * @param topicComment the entity to save
     * @return the persisted entity
     */
    TopicComment save(TopicComment topicComment);

    /**
     * Get all the topicComments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TopicComment> findAll(Pageable pageable);


    /**
     * Get the "id" topicComment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TopicComment> findOne(Long id);

    /**
     * Delete the "id" topicComment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the topicComment corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TopicComment> search(String query, Pageable pageable);
    Page<TopicComment> findbyTopic(Pageable pageable, Topic topic);
    TopicComment findTopicCommentsById(long id);
}
