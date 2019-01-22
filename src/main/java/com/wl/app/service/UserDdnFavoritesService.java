package com.wl.app.service;

import com.wl.app.domain.UserDdnFavorites;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing UserDdnFavorites.
 */
public interface UserDdnFavoritesService {

    /**
     * Save a userDdnFavorites.
     *
     * @param userDdnFavorites the entity to save
     * @return the persisted entity
     */
    UserDdnFavorites save(UserDdnFavorites userDdnFavorites);

    /**
     * Get all the userDdnFavorites.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserDdnFavorites> findAll(Pageable pageable);


    /**
     * Get the "id" userDdnFavorites.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UserDdnFavorites> findOne(Long id);

    List<UserDdnFavorites> findByUserId(long user_id);
    /**
     * Delete the "id" userDdnFavorites.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the userDdnFavorites corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserDdnFavorites> search(String query, Pageable pageable);
}
