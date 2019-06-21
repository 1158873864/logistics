package com.wl.app.service.impl;

import com.wl.app.domain.LogisticsDdn;
import com.wl.app.domain.UserInfo;
import com.wl.app.repository.UserInfoRepository;
import com.wl.app.service.SysRecruitmentInformationService;
import com.wl.app.domain.SysRecruitmentInformation;
import com.wl.app.repository.SysRecruitmentInformationRepository;
import com.wl.app.repository.search.SysRecruitmentInformationSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing SysRecruitmentInformation.
 */
@Service
@Transactional
public class SysRecruitmentInformationServiceImpl implements SysRecruitmentInformationService {

    private final Logger log = LoggerFactory.getLogger(SysRecruitmentInformationServiceImpl.class);

    private final SysRecruitmentInformationRepository sysRecruitmentInformationRepository;

    private final SysRecruitmentInformationSearchRepository sysRecruitmentInformationSearchRepository;
    private final UserInfoRepository userInfoRepository;

    public SysRecruitmentInformationServiceImpl(SysRecruitmentInformationRepository sysRecruitmentInformationRepository, SysRecruitmentInformationSearchRepository sysRecruitmentInformationSearchRepository, UserInfoRepository userInfoRepository) {
        this.sysRecruitmentInformationRepository = sysRecruitmentInformationRepository;
        this.sysRecruitmentInformationSearchRepository = sysRecruitmentInformationSearchRepository;
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * Save a sysRecruitmentInformation.
     *
     * @param sysRecruitmentInformation the entity to save
     * @return the persisted entity
     */
    @Override
    public SysRecruitmentInformation save(SysRecruitmentInformation sysRecruitmentInformation) {
        log.debug("Request to save SysRecruitmentInformation : {}", sysRecruitmentInformation);
        SysRecruitmentInformation result = sysRecruitmentInformationRepository.save(sysRecruitmentInformation);
        // sysRecruitmentInformationSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the sysRecruitmentInformations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysRecruitmentInformation> findAll(Pageable pageable) {
        //log.debug("Request to get all SysRecruitmentInformations");
        return sysRecruitmentInformationRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysRecruitmentInformation> findAllSys() {
        //log.debug("Request to get all SysRecruitmentInformations");
        return sysRecruitmentInformationRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));

    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysRecruitmentInformation> findAllByTIME(int size,int page) {
        //log.debug("Request to get all SysRecruitmentInformations");
        return sysRecruitmentInformationRepository.findAll(PageRequest.of(size,page, Sort.Direction.DESC,"id"));
    }

    /**
     * Get one sysRecruitmentInformation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysRecruitmentInformation> findOne(Long id) {
        log.debug("Request to get SysRecruitmentInformation : {}", id);
        System.out.println("findOne=id====" + id);
        return sysRecruitmentInformationRepository.findById(id);
    }

    /**
     * Delete the sysRecruitmentInformation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysRecruitmentInformation : {}", id);
        sysRecruitmentInformationRepository.deleteById(id);
        sysRecruitmentInformationSearchRepository.deleteById(id);
    }

    /**
     * Search for the sysRecruitmentInformation corresponding to the query.
     *
     * @param query    the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysRecruitmentInformation> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of SysRecruitmentInformations for query {}", query);
        return sysRecruitmentInformationSearchRepository.search(queryStringQuery(query), pageable);
    }

    @Override
    public Page<SysRecruitmentInformation> find(String query, Pageable pageable) {
        List<SysRecruitmentInformation> sysRecruitmentInformations=sysRecruitmentInformationRepository.findAll();
        List<SysRecruitmentInformation> sortedList=new ArrayList<>();
        for(int i=0;i<sysRecruitmentInformations.size();i++){
            SysRecruitmentInformation sysRecruitmentInformation=sysRecruitmentInformations.get(i);
            if(sysRecruitmentInformation.getAddrCity().indexOf(query)!=(-1)||sysRecruitmentInformation.getCategory().indexOf(query)!=(-1)||sysRecruitmentInformation.getCategoryName().indexOf(query)!=(-1)||sysRecruitmentInformation.getDescription().indexOf(query)!=(-1)||sysRecruitmentInformation.getEducation().indexOf(query)!=(-1)||sysRecruitmentInformation.getExperience().indexOf(query)!=(-1)||sysRecruitmentInformation.getNature().indexOf(query)!=(-1)||sysRecruitmentInformation.getSalaryEnd().indexOf(query)!=(-1)||sysRecruitmentInformation.getSalaryStart().indexOf(query)!=(-1)||String.valueOf(sysRecruitmentInformation.getId()).indexOf(query)!=(-1)){
                sortedList.add(sysRecruitmentInformation);
            }
        }
        return listConvertToPage(sortedList,pageable);
    }


    /**
     * 查找全部招聘通过ID
     *
     * @param UserInfoId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysRecruitmentInformation> QuereySysRecruitemByUserID(Long UserInfoId,Pageable pageable) {
        Optional<UserInfo> userInfo = userInfoRepository.findById(UserInfoId);
        System.out.println("===userinfo==="+userInfo.get()+"=========");
        List<SysRecruitmentInformation> AllSysRecruitement=sysRecruitmentInformationRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        System.out.println("===AllSysRecruitement==="+AllSysRecruitement.size()+"=========");
        List<SysRecruitmentInformation> SysOne = new ArrayList<>();
        List<SysRecruitmentInformation> SysTwo = new ArrayList<>();
        List<SysRecruitmentInformation> result = new ArrayList<>();

        for (int i = 0; i < AllSysRecruitement.size(); i++){
            SysRecruitmentInformation sysRecruitmentInformation = AllSysRecruitement.get(i);
            System.out.println("=====sysRecruitmentInformation===="+sysRecruitmentInformation);
            System.out.println(userInfo.get().getId().equals(sysRecruitmentInformation.getPeopleCount().longValue()));
            if(userInfo.get().getId().equals(sysRecruitmentInformation.getPeopleCount().longValue())){
              SysOne.add(sysRecruitmentInformation);
              System.out.println("===SysOne====="+SysOne);
            }
        }

        for (int i = 0; i < SysOne.size(); i++) {
           System.out.println("mmm==" + SysOne.get(i).getId() != null + "=======0000");
            if (SysOne.get(i).getId() != null || SysOne.get(i).getCategory() != null ) {
                SysTwo.add(SysOne.get(i));
                System.out.println("sortedList------" + SysTwo.size() + "---------sortedList");
            }
        }

        int pageNum = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int max = (SysTwo.size() < (pageNum * pageSize)) ? SysTwo.size() : pageNum * pageSize;
        for (int i = (pageNum - 1) * pageSize; i < max; i++) {
            result.add(SysTwo.get(i));
            System.out.println("result===" + result.size());
        }
        return result;

    }


    /**
     * 查找全部招聘通过ID,找出来进行排序
     *
     * @param UserInfoId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysRecruitmentInformation> QuereySysRecruitemByUserIDAndIDdesc(Long UserInfoId,int size,int page) {
        Optional<UserInfo> userInfo = userInfoRepository.findById(UserInfoId);

        List<SysRecruitmentInformation> AllSysRecruitement=sysRecruitmentInformationRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));

        List<SysRecruitmentInformation> SysOne = new ArrayList<>();
        List<SysRecruitmentInformation> SysTwo = new ArrayList<>();
        List<SysRecruitmentInformation> result = new ArrayList<>();

        for (int i = 0; i < AllSysRecruitement.size(); i++){
            SysRecruitmentInformation sysRecruitmentInformation = AllSysRecruitement.get(i);

            if(userInfo.get().getId()==(sysRecruitmentInformation.getPeopleCount().longValue())){
                SysOne.add(sysRecruitmentInformation);
            }
        }

        for (int i = 0; i < SysOne.size(); i++) {

            if (SysOne.get(i).getId() != null || SysOne.get(i).getCategory() != null ) {
                SysTwo.add(SysOne.get(i));

            }
        }
        int pageNum = page;
        int pageSize = size;
        System.out.println("pageNum:"+pageNum);
        System.out.println("pageSize:"+pageSize);
        int max = (SysTwo.size() < (pageNum * pageSize)) ? SysTwo.size() : pageNum * pageSize;
        System.out.println("Max:"+max);
        for (int i = (pageNum - 1) * pageSize; i < max; i++) {
            result.add(SysTwo.get(i));
        }
        System.out.println(result.size());
        return result;

    }


    public <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }


}