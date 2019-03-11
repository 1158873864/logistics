package com.wl.app.service;

import com.wl.app.domain.GoodsSource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing GoodsSource.
 */
public interface GoodsSourceService {

    /**
     * Save a goodsSource.
     *
     * @param goodsSource the entity to save
     * @return the persisted entity
     */
    GoodsSource save(GoodsSource goodsSource);

    /**
     * Get all the goodsSources.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GoodsSource> findAll(Pageable pageable);


    /**
     * Get the "id" goodsSource.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GoodsSource> findOne(Long id);

    /**
     * Delete the "id" goodsSource.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the goodsSource corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GoodsSource> search(String query, Pageable pageable);
    Page<GoodsSource> findall( Pageable pageable,GoodsSource goodsSource);
}
