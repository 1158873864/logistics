package com.wl.app.service.impl;

import com.wl.app.domain.LogisticsDdn;
import com.wl.app.service.UserInfoService;
import com.wl.app.domain.UserInfo;
import com.wl.app.repository.UserInfoRepository;
import com.wl.app.repository.search.UserInfoSearchRepository;
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
 * Service Implementation for managing UserInfo.
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    private final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    private final UserInfoRepository userInfoRepository;

    private final UserInfoSearchRepository userInfoSearchRepository;

    public UserInfoServiceImpl(UserInfoRepository userInfoRepository, UserInfoSearchRepository userInfoSearchRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userInfoSearchRepository = userInfoSearchRepository;
    }

    /**
     * Save a userInfo.
     *
     * @param userInfo the entity to save
     * @return the persisted entity
     */
    @Override
    public UserInfo save(UserInfo userInfo) {
        log.debug("Request to save UserInfo : {}", userInfo);        UserInfo result = userInfoRepository.save(userInfo);
        userInfoSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the userInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserInfo> findAll(Pageable pageable) {
        log.debug("Request to get all UserInfos");
        return userInfoRepository.findAll(pageable);
    }


    /**
     * Get one userInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserInfo> findOne(Long id) {
        log.debug("Request to get UserInfo : {}", id);

        return userInfoRepository.findById(id);
    }

    @Override
    public Optional<UserInfo> findOneByMobilePhone(String mobilePhone) {
        log.debug("Request to get UserInfo : {}", mobilePhone);
        return userInfoRepository.findUserInfoByMobilePhone(mobilePhone);
    }


    /**
     * Delete the userInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserInfo : {}", id);
        userInfoRepository.deleteById(id);
        userInfoSearchRepository.deleteById(id);
    }

    /**
     * Search for the userInfo corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserInfo> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UserInfos for query {}", query);
        return userInfoSearchRepository.search(queryStringQuery(query), pageable);
    }

    @Override
    public Page<UserInfo> find(String query, Pageable pageable) {
        List<UserInfo> userInfos=userInfoRepository.findAll();
        List<UserInfo> sortedList=new ArrayList<>();
        for(int i=0;i<userInfos.size();i++){
            UserInfo userInfo=userInfos.get(i);
            if(userInfo.getFullname().indexOf(query)!=(-1)||userInfo.getMobilePhone().indexOf(query)!=(-1)||userInfo.getNickName().indexOf(query)!=(-1)||userInfo.getOpenId().indexOf(query)!=(-1)||userInfo.getPhoto().indexOf(query)!=(-1)||userInfo.getRegisterSum().indexOf(query)!=(-1)||userInfo.getLastLoginedDate().toString().indexOf(query)!=(-1)||userInfo.getStatus().toString().indexOf(query)!=(-1)||String.valueOf(userInfo.getId()).indexOf(query)!=(-1)){
                sortedList.add(userInfo);
            }
        }
        return listConvertToPage(sortedList,pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserInfo> FindUserInfoBynickName(String nickName) {
        return   userInfoRepository.findAllByNickName(nickName);
    }
    public <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }

}
