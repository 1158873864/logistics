package com.wl.app.service.impl;

import com.wl.app.service.UserDdnFavoritesService;
import com.wl.app.domain.UserDdnFavorites;
import com.wl.app.repository.UserDdnFavoritesRepository;
import com.wl.app.repository.search.UserDdnFavoritesSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing UserDdnFavorites.
 */
@Service
@Transactional
public class UserDdnFavoritesServiceImpl implements UserDdnFavoritesService {

    private final Logger log = LoggerFactory.getLogger(UserDdnFavoritesServiceImpl.class);

    private final UserDdnFavoritesRepository userDdnFavoritesRepository;

    private final UserDdnFavoritesSearchRepository userDdnFavoritesSearchRepository;

    public UserDdnFavoritesServiceImpl(UserDdnFavoritesRepository userDdnFavoritesRepository, UserDdnFavoritesSearchRepository userDdnFavoritesSearchRepository) {
        this.userDdnFavoritesRepository = userDdnFavoritesRepository;
        this.userDdnFavoritesSearchRepository = userDdnFavoritesSearchRepository;
    }

    /**
     * Save a userDdnFavorites.
     *
     * @param userDdnFavorites the entity to save
     * @return the persisted entity
     */
    @Override
    public UserDdnFavorites save(UserDdnFavorites userDdnFavorites) {
        log.debug("Request to save UserDdnFavorites : {}", userDdnFavorites);        UserDdnFavorites result = userDdnFavoritesRepository.save(userDdnFavorites);
        userDdnFavoritesSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the userDdnFavorites.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserDdnFavorites> findAll(Pageable pageable) {
        log.debug("Request to get all UserDdnFavorites");
        return userDdnFavoritesRepository.findAll(pageable);
    }


    /**
     * Get one userDdnFavorites by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserDdnFavorites> findOne(Long id) {
        log.debug("Request to get UserDdnFavorites : {}", id);
        return userDdnFavoritesRepository.findById(id);
    }

    /**
     * Delete the userDdnFavorites by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserDdnFavorites : {}", id);
        userDdnFavoritesRepository.deleteById(id);
        userDdnFavoritesSearchRepository.deleteById(id);
    }

    /**
     * Search for the userDdnFavorites corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserDdnFavorites> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UserDdnFavorites for query {}", query);
        return userDdnFavoritesSearchRepository.search(queryStringQuery(query), pageable);    }
}
