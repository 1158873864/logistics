package com.wl.app.service.impl;

import com.wl.app.domain.LogisticsDdn;
import com.wl.app.service.UserDdnFavoritesService;
import com.wl.app.domain.UserDdnFavorites;
import com.wl.app.repository.UserDdnFavoritesRepository;
import com.wl.app.repository.search.UserDdnFavoritesSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    @Transactional(readOnly = true)
    public List<UserDdnFavorites> findByUserId(long user_id) {
        log.debug("Request to get UserDdnFavorites : {}", user_id);
        return userDdnFavoritesRepository.getUserDdnFavoritesByUserInfoId(user_id);
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

    @Override
    public Page<UserDdnFavorites> find(String query, Pageable pageable) {
        List<UserDdnFavorites> userDdnFavorites=userDdnFavoritesRepository.findAll();
        List<UserDdnFavorites> sortedList=new ArrayList<>();
        for(int i=0;i<userDdnFavorites.size();i++){
            UserDdnFavorites userDdnFavorites1=userDdnFavorites.get(i);
            if(userDdnFavorites1.getDdn().getTitle().indexOf(query)!=(-1)||userDdnFavorites1.getUserInfo().getMobilePhone().indexOf(query)!=(-1)||String.valueOf(userDdnFavorites1.getId()).indexOf(query)!=(-1)){
                sortedList.add(userDdnFavorites1);
            }
        }
        return listConvertToPage(sortedList,pageable);
    }

    public <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }
}
