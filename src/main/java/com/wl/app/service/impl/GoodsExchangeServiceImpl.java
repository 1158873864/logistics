package com.wl.app.service.impl;

import com.wl.app.service.GoodsExchangeService;
import com.wl.app.domain.GoodsExchange;
import com.wl.app.repository.GoodsExchangeRepository;
import com.wl.app.repository.search.GoodsExchangeSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing GoodsExchange.
 */
@Service
@Transactional
public class GoodsExchangeServiceImpl implements GoodsExchangeService {

    private final Logger log = LoggerFactory.getLogger(GoodsExchangeServiceImpl.class);

    private final GoodsExchangeRepository goodsExchangeRepository;

    private final GoodsExchangeSearchRepository goodsExchangeSearchRepository;

    public GoodsExchangeServiceImpl(GoodsExchangeRepository goodsExchangeRepository, GoodsExchangeSearchRepository goodsExchangeSearchRepository) {
        this.goodsExchangeRepository = goodsExchangeRepository;
        this.goodsExchangeSearchRepository = goodsExchangeSearchRepository;
    }

    /**
     * Save a goodsExchange.
     *
     * @param goodsExchange the entity to save
     * @return the persisted entity
     */
    @Override
    public GoodsExchange save(GoodsExchange goodsExchange) {
        log.debug("Request to save GoodsExchange : {}", goodsExchange);        GoodsExchange result = goodsExchangeRepository.save(goodsExchange);
        goodsExchangeSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the goodsExchanges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GoodsExchange> findAll(Pageable pageable) {
        log.debug("Request to get all GoodsExchanges");
        return goodsExchangeRepository.findAll(pageable);
    }


    /**
     * Get one goodsExchange by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GoodsExchange> findOne(Long id) {
        log.debug("Request to get GoodsExchange : {}", id);
        return goodsExchangeRepository.findById(id);
    }

    /**
     * Delete the goodsExchange by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GoodsExchange : {}", id);
        goodsExchangeRepository.deleteById(id);
        goodsExchangeSearchRepository.deleteById(id);
    }

    /**
     * Search for the goodsExchange corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GoodsExchange> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of GoodsExchanges for query {}", query);
        return goodsExchangeSearchRepository.search(queryStringQuery(query), pageable);    }
}
