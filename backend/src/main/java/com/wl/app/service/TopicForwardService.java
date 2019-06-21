package com.wl.app.service;

import com.wl.app.domain.TopicForward;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TopicForward.
 */
public interface TopicForwardService {

    /**
     * Save a topicForward.
     *
     * @param topicForward the entity to save
     * @return the persisted entity
     */
    TopicForward save(TopicForward topicForward);

    /**
     * Get all the topicForwards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TopicForward> findAll(Pageable pageable);


    /**
     * Get the "id" topicForward.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TopicForward> findOne(Long id);

    /**
     * Delete the "id" topicForward.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the topicForward corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TopicForward> search(String query, Pageable pageable);
}
