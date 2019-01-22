package com.wl.app.service;

import com.wl.app.domain.UserInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing UserInfo.
 */
public interface UserInfoService {

    /**
     * Save a userInfo.
     *
     * @param userInfo the entity to save
     * @return the persisted entity
     */
    UserInfo save(UserInfo userInfo);

    /**
     * Get all the userInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserInfo> findAll(Pageable pageable);


    /**
     * Get the "id" userInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UserInfo> findOne(Long id);


    /**
     * Get the "mobilephone" userInfo.
     *
     * @param mobilePhone the mobilePhone of the entity
     * @return the entity
     */
    Optional<UserInfo> findOneByMobilePhone(String mobilePhone);


    /**
     * Delete the "id" userInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the userInfo corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserInfo> search(String query, Pageable pageable);
}
