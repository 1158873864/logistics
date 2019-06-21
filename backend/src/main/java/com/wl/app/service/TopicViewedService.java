package com.wl.app.service;

import com.wl.app.domain.TopicViewed;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TopicViewed.
 */
public interface TopicViewedService {

    /**
     * Save a topicViewed.
     *
     * @param topicViewed the entity to save
     * @return the persisted entity
     */
    TopicViewed save(TopicViewed topicViewed);

    /**
     * Get all the topicVieweds.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TopicViewed> findAll(Pageable pageable);


    /**
     * Get the "id" topicViewed.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TopicViewed> findOne(Long id);

    /**
     * Delete the "id" topicViewed.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the topicViewed corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TopicViewed> search(String query, Pageable pageable);
}
