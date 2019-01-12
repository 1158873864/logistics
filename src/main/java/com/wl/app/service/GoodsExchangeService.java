package com.wl.app.service;

import com.wl.app.domain.GoodsExchange;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing GoodsExchange.
 */
public interface GoodsExchangeService {

    /**
     * Save a goodsExchange.
     *
     * @param goodsExchange the entity to save
     * @return the persisted entity
     */
    GoodsExchange save(GoodsExchange goodsExchange);

    /**
     * Get all the goodsExchanges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GoodsExchange> findAll(Pageable pageable);


    /**
     * Get the "id" goodsExchange.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GoodsExchange> findOne(Long id);

    /**
     * Delete the "id" goodsExchange.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the goodsExchange corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GoodsExchange> search(String query, Pageable pageable);
}
