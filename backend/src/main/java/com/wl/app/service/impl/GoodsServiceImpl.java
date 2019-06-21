package com.wl.app.service.impl;

import com.wl.app.domain.LogisticsDdn;
import com.wl.app.service.GoodsService;
import com.wl.app.domain.Goods;
import com.wl.app.repository.GoodsRepository;
import com.wl.app.repository.search.GoodsSearchRepository;
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
 * Service Implementation for managing Goods.
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    private final Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);

    private final GoodsRepository goodsRepository;

    private final GoodsSearchRepository goodsSearchRepository;

    public GoodsServiceImpl(GoodsRepository goodsRepository, GoodsSearchRepository goodsSearchRepository) {
        this.goodsRepository = goodsRepository;
        this.goodsSearchRepository = goodsSearchRepository;
    }

    /**
     * Save a goods.
     *
     * @param goods the entity to save
     * @return the persisted entity
     */
    @Override
    public Goods save(Goods goods) {
        log.debug("Request to save Goods : {}", goods);        Goods result = goodsRepository.save(goods);
        goodsSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the goods.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Goods> findAll(Pageable pageable) {
        log.debug("Request to get all Goods");
        return goodsRepository.findAll(pageable);
    }


    /**
     * Get one goods by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Goods> findOne(Long id) {
        log.debug("Request to get Goods : {}", id);
        return goodsRepository.findById(id);
    }

    /**
     * Delete the goods by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Goods : {}", id);
        goodsRepository.deleteById(id);
        goodsSearchRepository.deleteById(id);
    }

    /**
     * Search for the goods corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Goods> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Goods for query {}", query);
        return goodsSearchRepository.search(queryStringQuery(query), pageable);    }

    @Override
    public Page<Goods> find(String query, Pageable pageable) {
        List<Goods> goods=goodsRepository.findAll();
        List<Goods> sortedList=new ArrayList<>();
        for(int i=0;i<goods.size();i++){
            Goods good=goods.get(i);
            if(good.getCover().indexOf(query)!=(-1)||good.getIntroduce().indexOf(query)!=(-1)||good.getName().indexOf(query)!=(-1)||good.getPayment().indexOf(query)!=(-1)||good.getIntegral().toString().indexOf(query)!=(-1)||good.getPeopleCount().toString().indexOf(query)!=(-1)||good.getStatus().toString().indexOf(query)!=(-1)||good.getTotal().toString().indexOf(query)!=(-1)||String.valueOf(good.getId()).indexOf(query)!=(-1)){
                sortedList.add(good);
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
