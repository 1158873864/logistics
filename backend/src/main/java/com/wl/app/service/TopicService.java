package com.wl.app.service;

import com.wl.app.domain.Topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Topic.
 */
public interface TopicService {

    /**
     * Save a topic.
     *
     * @param topic the entity to save
     * @return the persisted entity
     */
    Topic save(Topic topic);

    /**
     * Get all the topics.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Topic> findAll(Pageable pageable);
    Page<Topic> findAllTopic(int size,int page);

    /**
     * Get the "id" topic.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Topic> findOne(Long id);

    /**
     * Delete the "id" topic.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    public void deleteone(Long id);
    public void deletetwo(Long id);
    public void deleteThree(Long id);
    public void deletefree(Long id);

    /**
     * Search for the topic corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Topic> search(String query, Pageable pageable);

    Page<Topic> find(String query, Pageable pageable);

    List<Topic> findAllTopicById(Long id);
}
