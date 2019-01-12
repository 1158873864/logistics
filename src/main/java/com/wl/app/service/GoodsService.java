package com.wl.app.service;

import com.wl.app.domain.Goods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Goods.
 */
public interface GoodsService {

    /**
     * Save a goods.
     *
     * @param goods the entity to save
     * @return the persisted entity
     */
    Goods save(Goods goods);

    /**
     * Get all the goods.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Goods> findAll(Pageable pageable);


    /**
     * Get the "id" goods.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Goods> findOne(Long id);

    /**
     * Delete the "id" goods.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the goods corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Goods> search(String query, Pageable pageable);
}
