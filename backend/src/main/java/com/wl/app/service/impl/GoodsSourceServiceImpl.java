package com.wl.app.service.impl;

import com.wl.app.domain.Area;
import com.wl.app.domain.GoodsSource;
import com.wl.app.domain.LogisticsDdn;
import com.wl.app.domain.UserInfo;
import com.wl.app.domain.enumeration.GoodsSourceProperty;
import com.wl.app.domain.enumeration.Status;
import com.wl.app.repository.AreaRepository;
import com.wl.app.repository.GoodsSourceRepository;
import com.wl.app.repository.UserInfoRepository;
import com.wl.app.repository.search.GoodsSourceSearchRepository;
import com.wl.app.repository.search.UserInfoSearchRepository;
import com.wl.app.service.GoodsSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing GoodsSource.
 */
@Service
@Transactional
public class GoodsSourceServiceImpl implements GoodsSourceService {

    private final Logger log = LoggerFactory.getLogger(GoodsSourceServiceImpl.class);

    private final GoodsSourceRepository goodsSourceRepository;

    private final GoodsSourceSearchRepository goodsSourceSearchRepository;
    private final AreaRepository areaRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserInfoSearchRepository userInfoSearchRepository;

    public GoodsSourceServiceImpl(GoodsSourceRepository goodsSourceRepository, GoodsSourceSearchRepository goodsSourceSearchRepository, AreaRepository areaRepository, UserInfoRepository userInfoRepository
            , UserInfoSearchRepository userInfoSearchRepository) {
        this.goodsSourceRepository = goodsSourceRepository;
        this.goodsSourceSearchRepository = goodsSourceSearchRepository;
        this.areaRepository = areaRepository;
        this.userInfoRepository = userInfoRepository;
        this.userInfoSearchRepository = userInfoSearchRepository;
    }

    /**
     * Save a goodsSource.
     *
     * @param goodsSource the entity to save
     * @return the persisted entity
     */
    @Override
    public GoodsSource save(GoodsSource goodsSource) {
        System.out.println("Goodsource" + goodsSource.getName() + "000000000000");
        Optional<UserInfo> userinfo = userInfoRepository.findById(goodsSource.getUserInfo().getId());
        Integer i = userinfo.get().getGoodsSourceCount();
        Integer j = i + 1;
        System.out.println("Integer" + j + "000000000000");
        userinfo.get().setGoodsSourceCount(j);
        System.out.println(" userinfo.get().getGoodsSourceCount();" + userinfo.get().getGoodsSourceCount());
       /* UserInfoService user=new UserInfoServiceImpl(userInfoRepository,userInfoSearchRepository);
        user.save(userinfo.get());*/
        userInfoRepository.save(userinfo.get());
        goodsSource.setUserInfo(userinfo.get());
        log.debug("Request to get GoodsSource : {}", goodsSource);
        GoodsSource result = goodsSourceRepository.save(goodsSource);
        goodsSourceSearchRepository.save(result);
        return result;
    }


    @Override
    public GoodsSource saveGoodsSource(GoodsSource goodsSource) {
        log.debug("Request to get GoodsSource : {}", goodsSource);
        GoodsSource result = goodsSourceRepository.save(goodsSource);
        goodsSourceSearchRepository.save(result);
        return result;
    }


    /**
     * Get all the goodsSources.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GoodsSource> findAll(Pageable pageable) {
        log.debug("Request to get all GoodsSources");
        return this.goodsSourceRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GoodsSource> findAllGoodsSource(int size,int page) {
        log.debug("Request to get all GoodsSources");
        return this.goodsSourceRepository.findAll(PageRequest.of(page,size,Sort.Direction.DESC, "effectiveDate"));
    }


    /**
     * Get one goodsSource by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GoodsSource> findOne(Long id) {
        log.debug("Request to get GoodsSource : {}", id);
        return goodsSourceRepository.findById(id);
    }

    /**
     * Delete the goodsSource by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GoodsSource : {}", id);
        goodsSourceRepository.deleteById(id);
        goodsSourceSearchRepository.deleteById(id);
    }

    /**
     * Search for the goodsSource corresponding to the query.
     *
     * @param query    the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GoodsSource> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of GoodsSources for query {}", query);
        return goodsSourceSearchRepository.search(queryStringQuery(query), pageable);
    }

    @Override
    public Page<GoodsSource> find(String query, Pageable pageable) {
        List<GoodsSource> goodsSources=goodsSourceRepository.findAll();
        List<GoodsSource> sortedList=new ArrayList<>();
        for(int i=0;i<goodsSources.size();i++){
            GoodsSource goodsSource=goodsSources.get(i);
            if(goodsSource.getGoodsSourceProperty().toString().indexOf(query)!=(-1)||goodsSource.getGoodsSourcePacking().toString().indexOf(query)!=(-1)||goodsSource.getEnd().indexOf(query)!=(-1)||goodsSource.getGoodsSourceFreight().indexOf(query)!=(-1)||goodsSource.getMobilePhone().indexOf(query)!=(-1)||goodsSource.getStart().indexOf(query)!=(-1)||goodsSource.getName().indexOf(query)!=(-1)||goodsSource.getTon().indexOf(query)!=(-1)||goodsSource.getVolume().indexOf(query)!=(-1)||String.valueOf(goodsSource.getId()).indexOf(query)!=(-1)){
                sortedList.add(goodsSource);
            }
        }
        return listConvertToPage(sortedList,pageable);
    }

    @Override
    public Page<GoodsSource> findall(Pageable pageable, GoodsSource goodsSource) {
        Example<GoodsSource> goodsSourceExample = Example.of(goodsSource);

        return goodsSourceRepository.findAll(goodsSourceExample, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GoodsSource> findAllById(Long id) {
        log.debug("Request to get GoodsSource : {}", id);
        return goodsSourceRepository.findAllByUserInfoId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GoodsSource> findGoodsSourceByCondition(String start, String end, GoodsSourceProperty goodsSourceProperty) {

        return goodsSourceRepository.findAllByStartAndEndOrGoodsSourceProperty(start, end, goodsSourceProperty);
    }


    @Override
    @Transactional(readOnly = true)
    public List<GoodsSource> findGoodsSourceByConditionTwo(String start, String end, GoodsSourceProperty goodsSourceProperty, Pageable pageable) {
        System.out.println("111111111" + start + "000000000" + end + "222222222" + goodsSourceProperty + "===effe=====" + pageable + "0000000000");
        List<GoodsSource> logisticsDdnList = new ArrayList<>();
        List<GoodsSource> sortedList = new ArrayList<>();
        String province = findProvinceByCity(end);
        System.out.println("province======" + province);
        List<GoodsSource> logisticsDdns = goodsSourceRepository.findAll();
        System.out.println("logisticsDdns====" + logisticsDdns.size() + "logisticsDdns");
        List<GoodsSource> result = new ArrayList<>();
   /* end = end.trim();
        if (end.trim().startsWith("北京") || end.trim().startsWith("天津") || end.trim().startsWith("重庆") || end.trim().startsWith("上海")) {
        end = end.substring(0, 2);
    }*/
        System.out.println("end====" + end + "========");
        for (int i = 0; i < logisticsDdns.size(); i++) {
            GoodsSource logisticsDdn = logisticsDdns.get(i);
            System.out.println("=====" + logisticsDdns.get(i) + "======");
            System.out.println("1111111111111====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
            System.out.println("222222====" + logisticsDdn.getStart().equals(start) + "==========");
            if (logisticsDdn.getStart().equals(start) && logisticsDdn.getEnd().equals(end) || logisticsDdn.getStart().indexOf(start) != (-1) || logisticsDdn.getEnd().indexOf(end) != (-1) || logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty)) {
                System.out.println("qqqqqqqqqqqqqqqq====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
                System.out.println("QQQQQQQ====" + logisticsDdn.getStart().equals(start) + "==========");
                logisticsDdnList.add(logisticsDdn);
                System.out.println("logisticsDdnList------" + logisticsDdnList.size() + "---------logisticsDdnList");
            }
        }
        for (int i = 0; i < logisticsDdnList.size(); i++) {
            System.out.println("000000==" + logisticsDdnList.get(i).getEnd() != null + "=======0000");
            System.out.println("000000==" + logisticsDdnList.get(i).getStart() != null + "=======0000");
            System.out.println("000000==" + logisticsDdnList.get(i).getGoodsSourceProperty() != null + "=======0000");
            if (logisticsDdnList.get(i).getEnd() != null || logisticsDdnList.get(i).getStart() != null || logisticsDdnList.get(i).getGoodsSourceProperty() != null) {
                sortedList.add(logisticsDdnList.get(i));
                System.out.println("sortedList------" + sortedList.size() + "---------sortedList");
            }
        }
        /*for (int i = 0; i < logisticsDdnList.size(); i++) {
        if (logisticsDdnList.get(i).getEnd().indexOf(end) == (-1)) {
            sortedList.add(logisticsDdnList.get(i));
            System.out.println("sortedList--00000----"+sortedList.size()+"-----00000----sortedList");
        }
    }*/
        int pageNum = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int max = (sortedList.size() < (pageNum * pageSize)) ? sortedList.size() : pageNum * pageSize;
        for (int i = (pageNum - 1) * pageSize; i < max; i++) {
            result.add(sortedList.get(i));
            System.out.println("result===" + result.size());
        }
        return result;

    }

    /**
     * 模拟猜测
     *
     * @param start
     * @param end
     * @param goodsSourceProperty
     * @param pageable
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoodsSource> findGoodsSourceByConditionThree(String start, String end, GoodsSourceProperty goodsSourceProperty, Pageable pageable) {
        System.out.println("111111111" + start + "000000000" + end + "222222222" + goodsSourceProperty + "===effe=====" + pageable + "0000000000");
        List<GoodsSource> logisticsDdnList = new ArrayList<>();
        List<GoodsSource> sortedList = new ArrayList<>();
        String province = findProvinceByCity(end);
        System.out.println("province======" + province);
        List<GoodsSource> logisticsDdns = goodsSourceRepository.findAll();
        System.out.println("logisticsDdns====" + logisticsDdns.size() + "logisticsDdns");
        List<GoodsSource> result = new ArrayList<>();
   /* end = end.trim();
        if (end.trim().startsWith("北京") || end.trim().startsWith("天津") || end.trim().startsWith("重庆") || end.trim().startsWith("上海")) {
        end = end.substring(0, 2);
    }*/
        System.out.println("end====" + end + "========");
        for (int i = 0; i < logisticsDdns.size(); i++) {
            GoodsSource logisticsDdn = logisticsDdns.get(i);
            System.out.println("=====" + logisticsDdns.get(i) + "======");
            System.out.println("1111111111111====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
            System.out.println("222222====" + logisticsDdn.getStart().equals(start) + "==========");
            if (logisticsDdn.getStart().indexOf(start) != (-1) && logisticsDdn.getEnd().indexOf(end) != (-1) || logisticsDdn.getGoodsSourceProperty() == (goodsSourceProperty) || logisticsDdn.getStart().indexOf(start) != (-1) && logisticsDdn.getEnd().indexOf(end) != (-1) && logisticsDdn.getGoodsSourceProperty() == (goodsSourceProperty)) {
                System.out.println("qqqqqqqqqqqqqqqq====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
                System.out.println("QQQQQQQ====" + logisticsDdn.getStart().equals(start) + "==========");
                logisticsDdnList.add(logisticsDdn);
                System.out.println("logisticsDdnList------" + logisticsDdnList.size() + "---------logisticsDdnList");
            }


        }
        for (int i = 0; i < logisticsDdnList.size(); i++) {
            System.out.println("kkk==" + logisticsDdnList.get(i).getEnd() != null + "=======0000");
            System.out.println("lll==" + logisticsDdnList.get(i).getStart() != null + "=======0000");
            System.out.println("mmm==" + logisticsDdnList.get(i).getGoodsSourceProperty() != null + "=======0000");
            if (logisticsDdnList.get(i).getEnd() != null || logisticsDdnList.get(i).getStart() != null || logisticsDdnList.get(i).getGoodsSourceProperty() != null) {
                sortedList.add(logisticsDdnList.get(i));
                System.out.println("sortedList------" + sortedList.size() + "---------sortedList");
            }
        }
        /*for (int i = 0; i < logisticsDdnList.size(); i++) {
        if (logisticsDdnList.get(i).getEnd().indexOf(end) == (-1)) {
            sortedList.add(logisticsDdnList.get(i));
            System.out.println("sortedList--00000----"+sortedList.size()+"-----00000----sortedList");
        }
    }*/
        int pageNum = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int max = (sortedList.size() < (pageNum * pageSize)) ? sortedList.size() : pageNum * pageSize;
        for (int i = (pageNum - 1) * pageSize; i < max; i++) {
            result.add(sortedList.get(i));
            System.out.println("result===" + result.size());
        }
        return result;

    }


    //*********************在进行对比************************************************************

    /**
     * 模拟猜测
     *
     * @param start
     * @param end
     * @param goodsSourceProperty
     * @param pageable
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoodsSource> findGoodsSourceByConditionNEXT(String start, String end, GoodsSourceProperty goodsSourceProperty, Pageable pageable) {
        System.out.println("111111111" + start + "000000000" + end + "222222222" + goodsSourceProperty + "===effe=====" + pageable + "0000000000");
        List<GoodsSource> logisticsDdnList = new ArrayList<>();
        List<GoodsSource> sortedList = new ArrayList<>();
        String province = findProvinceByCity(end);
        System.out.println("province======" + province);
        List<GoodsSource> logisticsDdns = goodsSourceRepository.findAll();
        System.out.println("logisticsDdns====" + logisticsDdns.size() + "logisticsDdns");
        List<GoodsSource> GoodsSourcePropertory = goodsSourceRepository.findAllByGoodsSourceProperty(goodsSourceProperty);
        System.out.println("====GoodsSourcePropertory====" + GoodsSourcePropertory.size() + "===============");
        List<GoodsSource> result = new ArrayList<>();

        if (start != null && end != null) {
            if (goodsSourceProperty != null) {
                for (int i = 0; i < logisticsDdns.size(); i++) {
                    GoodsSource logisticsDdn = logisticsDdns.get(i);
                    System.out.println("==logisticsDdn===" + logisticsDdn + "======");
                    System.out.println("==三者都不为空下====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
                    System.out.println("三者都不为空====" + logisticsDdn.getStart().equals(start) + "==========");
                    System.out.println("=====" + logisticsDdn.getGoodsSourceProperty() == goodsSourceProperty + "=======");
                    System.out.println("===Start==" + start == logisticsDdn.getStart() + "=======");
                    System.out.println("===end==" + end == logisticsDdn.getEnd() + "=======");
                    if (logisticsDdn.getStart().indexOf(start) != (-1) && logisticsDdn.getEnd().indexOf(end) != (-1) && logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty)) {
                        System.out.println("我进来判断");
                        logisticsDdnList.add(logisticsDdn);
                    }
                }
            } else {
                for (int i = 0; i < logisticsDdns.size(); i++) {
                    GoodsSource logisticsDdn = logisticsDdns.get(i);
                    System.out.println("==logisticsDdn===" + logisticsDdn + "======");
                    System.out.println("1111111111111====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
                    System.out.println("222222====" + logisticsDdn.getStart().equals(start) + "==========");
                    System.out.println("=====" + logisticsDdn.getGoodsSourceProperty() == goodsSourceProperty + "=======");

                    if (logisticsDdn.getStart().indexOf(start) != (-1) && logisticsDdn.getEnd().indexOf(end) != (-1)) {
                        System.out.println("qqqqqqqqqqqqqqqq====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
                        System.out.println("QQQQQQQ====" + logisticsDdn.getStart().equals(start) + "==========");
                        logisticsDdnList.add(logisticsDdn);
                        System.out.println("logisticsDdnList------" + logisticsDdnList.size() + "---------logisticsDdnList");
                    }
                }
            }
        } else {
            for (int i = 0; i < GoodsSourcePropertory.size(); i++) {
                GoodsSource logisticsDdnTwo = GoodsSourcePropertory.get(i);
                System.out.println("=====" + GoodsSourcePropertory.get(i) + "======");
                System.out.println("logisticsDdnTwo===" + logisticsDdnTwo + "=====");
                System.out.println("我进来了");
                logisticsDdnList.add(logisticsDdnTwo);
            }
        }

        for (int i = 0; i < logisticsDdnList.size(); i++) {
            System.out.println("kkk==" + logisticsDdnList.get(i).getEnd() != null + "=======0000");
            System.out.println("lll==" + logisticsDdnList.get(i).getStart() != null + "=======0000");
            System.out.println("mmm==" + logisticsDdnList.get(i).getGoodsSourceProperty() != null + "=======0000");
            if (logisticsDdnList.get(i).getEnd() != null || logisticsDdnList.get(i).getStart() != null || logisticsDdnList.get(i).getGoodsSourceProperty() != null) {
                sortedList.add(logisticsDdnList.get(i));
                System.out.println("sortedList------" + sortedList.size() + "---------sortedList");
            }
        }

        int pageNum = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int max = (sortedList.size() < (pageNum * pageSize)) ? sortedList.size() : pageNum * pageSize;
        for (int i = (pageNum - 1) * pageSize; i < max; i++) {
            result.add(sortedList.get(i));
            System.out.println("result===" + result.size());
        }
        return result;

    }


    //这是进行判断,通过市找省
    public String findProvinceByCity(String city) {
        String result = "";
        List<Area> areaList = areaRepository.findByParentId(0);
        for (int i = 0; i < areaList.size(); i++) {
            List<Area> cityList = areaRepository.findByParentId(areaList.get(i).getId().intValue());
            for (int j = 0; j < cityList.size(); j++) {
                if (cityList.get(j).getCity().equals(city)) {
                    return areaList.get(i).getCity();
                }
            }
        }
        return result;
    }

//**********************************************************************
    /**
     * 通过时间排序
     * @param start
     * @param end
     * @param goodsSourceProperty
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoodsSource> findGoodsSourceBytime(String start, String end, GoodsSourceProperty goodsSourceProperty,int size,int page) {
        System.out.println("111111111" + start + "000000000" + end + "222222222" + goodsSourceProperty + "===effe=====" +size+"==="+page+"===" + "0000000000");
        List<GoodsSource> logisticsDdnList = new ArrayList<>();
        List<GoodsSource> sortedList = new ArrayList<>();
        String province = findProvinceByCity(end);
        System.out.println("province======" + province);
        List<GoodsSource> logisticsDdns = goodsSourceRepository.findAll();
        Page<GoodsSource> GoodsSourceBytime=goodsSourceRepository.findAll(PageRequest.of(page,size, Sort.Direction.DESC,"effectiveDate"));
        System.out.println("logisticsDdns====" + GoodsSourceBytime.getContent().size() + "logisticsDdns");
        List<GoodsSource> GoodsSourcePropertory = goodsSourceRepository.findAllByGoodsSourceProperty(goodsSourceProperty);
        System.out.println("====GoodsSourcePropertory====" + GoodsSourcePropertory.size() + "===============");
        List<GoodsSource> result = new ArrayList<>();

        if (start != null && end != null) {
            if (goodsSourceProperty != null) {
                for (int i = 0; i <GoodsSourceBytime.getContent().size(); i++) {
                    GoodsSource logisticsDdn = GoodsSourceBytime.getContent().get(i);
                    System.out.println("==logisticsDdn===" + logisticsDdn + "======");
                    System.out.println("==三者都不为空下====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
                    System.out.println("三者都不为空====" + logisticsDdn.getStart().equals(start) + "==========");
                    System.out.println("=====" + logisticsDdn.getGoodsSourceProperty() == goodsSourceProperty + "=======");
                    System.out.println("===Start==" + start == logisticsDdn.getStart() + "=======");
                    System.out.println("===end==" + end == logisticsDdn.getEnd() + "=======");
                    if (logisticsDdn.getStart().indexOf(start) != (-1) && logisticsDdn.getEnd().indexOf(end) != (-1) && logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty)) {
                        System.out.println("我进来判断");
                        logisticsDdnList.add(logisticsDdn);
                    }
                }
            } else {
                for (int i = 0; i < GoodsSourceBytime.getContent().size(); i++) {
                    GoodsSource logisticsDdn = GoodsSourceBytime.getContent().get(i);
                    System.out.println("==logisticsDdn===" + logisticsDdn + "======");
                    System.out.println("1111111111111====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
                    System.out.println("222222====" + logisticsDdn.getStart().equals(start) + "==========");
                    System.out.println("=====" + logisticsDdn.getGoodsSourceProperty() == goodsSourceProperty + "=======");

                    if (logisticsDdn.getStart().indexOf(start) != (-1) && logisticsDdn.getEnd().indexOf(end) != (-1)) {
                        System.out.println("qqqqqqqqqqqqqqqq====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
                        System.out.println("QQQQQQQ====" + logisticsDdn.getStart().equals(start) + "==========");
                        logisticsDdnList.add(logisticsDdn);
                        System.out.println("logisticsDdnList------" + logisticsDdnList.size() + "---------logisticsDdnList");
                    }
                }
            }
        } else {
            for (int i = 0; i < GoodsSourcePropertory.size(); i++) {
                GoodsSource logisticsDdnTwo = GoodsSourcePropertory.get(i);
                System.out.println("=====" + GoodsSourcePropertory.get(i) + "======");
                System.out.println("logisticsDdnTwo===" + logisticsDdnTwo + "=====");
                System.out.println("我进来了");
                logisticsDdnList.add(logisticsDdnTwo);
            }
        }

        for (int i = 0; i < logisticsDdnList.size(); i++) {
            System.out.println("kkk==" + logisticsDdnList.get(i).getEnd() != null + "=======0000");
            System.out.println("lll==" + logisticsDdnList.get(i).getStart() != null + "=======0000");
            System.out.println("mmm==" + logisticsDdnList.get(i).getGoodsSourceProperty() != null + "=======0000");
            if (logisticsDdnList.get(i).getEnd() != null || logisticsDdnList.get(i).getStart() != null || logisticsDdnList.get(i).getGoodsSourceProperty() != null) {
                sortedList.add(logisticsDdnList.get(i));
                System.out.println("sortedList------" + sortedList.size() + "---------sortedList");
            }
        }

        int pageNum = page;
        int pageSize = size;
        int max = (sortedList.size() < (pageNum * pageSize)) ? sortedList.size() : pageNum * pageSize;
        for (int i = (pageNum - 1) * pageSize; i < max; i++) {
            result.add(sortedList.get(i));
            System.out.println("result===" + result.size());
        }
        return result;

    }

   //*******************************************

    /**
     * 普通测试
     * @param start
     * @param end
     * @param goodsSourceProperty
     * @return
     */@Override

   @Transactional(readOnly = true)
   public List<GoodsSource> findGoodsSourceByConditionNEXTone(String start, String end, GoodsSourceProperty goodsSourceProperty) {
       System.out.println("111111111" + start + "000000000" + end + "222222222" + goodsSourceProperty + "===effe=====" );
       List<GoodsSource> logisticsDdnList = new ArrayList<>();
       List<GoodsSource> sortedList = new ArrayList<>();
       String province = findProvinceByCity(end);
       System.out.println("province======" + province);
       List<GoodsSource> logisticsDdns = goodsSourceRepository.findAll();
       System.out.println("logisticsDdns====" + logisticsDdns.size() + "logisticsDdns");
       List<GoodsSource> GoodsSourcePropertory = goodsSourceRepository.findAllByGoodsSourceProperty(goodsSourceProperty);
       System.out.println("====GoodsSourcePropertory====" + GoodsSourcePropertory.size() + "===============");
       List<GoodsSource> result = new ArrayList<>();

       if (start != null && end != null) {
           if (goodsSourceProperty != null) {
               for (int i = 0; i < logisticsDdns.size(); i++) {
                   GoodsSource logisticsDdn = logisticsDdns.get(i);
                   System.out.println("==logisticsDdn===" + logisticsDdn + "======");
                   System.out.println("==三者都不为空下====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
                   System.out.println("三者都不为空====" + logisticsDdn.getStart().equals(start) + "==========");
                   System.out.println("=====" + logisticsDdn.getGoodsSourceProperty() == goodsSourceProperty + "=======");
                   System.out.println("===Start==" + start == logisticsDdn.getStart() + "=======");
                   System.out.println("===end==" + end == logisticsDdn.getEnd() + "=======");
                   if (logisticsDdn.getStart().indexOf(start) != (-1) && logisticsDdn.getEnd().indexOf(end) != (-1) && logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty)) {
                       System.out.println("我进来判断");
                       logisticsDdnList.add(logisticsDdn);
                   }
               }
           } else {
               for (int i = 0; i < logisticsDdns.size(); i++) {
                   GoodsSource logisticsDdn = logisticsDdns.get(i);
                   System.out.println("==logisticsDdn===" + logisticsDdn + "======");
                   System.out.println("1111111111111====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
                   System.out.println("222222====" + logisticsDdn.getStart().equals(start) + "==========");
                   System.out.println("=====" + logisticsDdn.getGoodsSourceProperty() == goodsSourceProperty + "=======");

                   if (logisticsDdn.getStart().indexOf(start) != (-1) && logisticsDdn.getEnd().indexOf(end) != (-1)) {
                       System.out.println("qqqqqqqqqqqqqqqq====" + logisticsDdn.getGoodsSourceProperty().equals(goodsSourceProperty) + "============");
                       System.out.println("QQQQQQQ====" + logisticsDdn.getStart().equals(start) + "==========");
                       logisticsDdnList.add(logisticsDdn);
                       System.out.println("logisticsDdnList------" + logisticsDdnList.size() + "---------logisticsDdnList");
                   }
               }
           }
       } else {
           for (int i = 0; i < GoodsSourcePropertory.size(); i++) {
               GoodsSource logisticsDdnTwo = GoodsSourcePropertory.get(i);
               System.out.println("=====" + GoodsSourcePropertory.get(i) + "======");
               System.out.println("logisticsDdnTwo===" + logisticsDdnTwo + "=====");
               System.out.println("我进来了");
               logisticsDdnList.add(logisticsDdnTwo);
           }
       }

       for (int i = 0; i < logisticsDdnList.size(); i++) {
           System.out.println("kkk==" + logisticsDdnList.get(i).getEnd() != null + "=======0000");
           System.out.println("lll==" + logisticsDdnList.get(i).getStart() != null + "=======0000");
           System.out.println("mmm==" + logisticsDdnList.get(i).getGoodsSourceProperty() != null + "=======0000");
           if (logisticsDdnList.get(i).getEnd() != null || logisticsDdnList.get(i).getStart() != null || logisticsDdnList.get(i).getGoodsSourceProperty() != null) {
               sortedList.add(logisticsDdnList.get(i));
               System.out.println("sortedList------" + sortedList.size() + "---------sortedList");
           }
       }
       System.out.println("==sortedList==="+sortedList.size());
       return sortedList;

   }

    //******************************************



//*********************************************************************

   /* @Overridel
    public GoodsSource findByCondition(Pageable pageable,String Strat,String end){
        GoodsSource SS=new GoodsSource();
        SS.setEnd(end);
        SS.setStart(Strat);
        Example goodsSourceExample = Example.of(SS);
        return goodsSourceRepository.findAll(goodsSourceExample, pageable);
        return null;
    }*/


    /*@Override
    @Transactional(readOnly = true)
    public Page<GoodsSource> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of GoodsSources for query {}", query);
        return goodsSourceSearchRepository.searchSimilar(queryStringQuery(query), pageable);
    }*/

    //客户查询我的发布里面的全部接单的
    public List<GoodsSource> findGoodsSoourceByIdAndStatus(Status status, Long id) {
        log.debug("Request to get GoodsSource : {}", id);
        return goodsSourceRepository.findAllByStatusAndUserInfoId(status, id);
    }

    public <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }
}
