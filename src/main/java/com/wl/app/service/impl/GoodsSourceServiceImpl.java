package com.wl.app.service.impl;

import com.wl.app.service.GoodsSourceService;
import com.wl.app.domain.GoodsSource;
import com.wl.app.repository.GoodsSourceRepository;
import com.wl.app.repository.search.GoodsSourceSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing GoodsSource.
 */
@Service
@Transactional
public class GoodsSourceServiceImpl implements GoodsSourceService {

    private final Logger log = LoggerFactory.getLogger(GoodsSourceServiceImpl.class);

    private final GoodsSourceRepository goodsSourceRepository;

    private final GoodsSourceSearchRepository goodsSourceSearchRepository;

    public GoodsSourceServiceImpl(GoodsSourceRepository goodsSourceRepository, GoodsSourceSearchRepository goodsSourceSearchRepository) {
        this.goodsSourceRepository = goodsSourceRepository;
        this.goodsSourceSearchRepository = goodsSourceSearchRepository;
    }

    /**
     * Save a goodsSource.
     *
     * @param goodsSource the entity to save
     * @return the persisted entity
     */
    @Override
    public GoodsSource save(GoodsSource goodsSource) {
        log.debug("Request to save GoodsSource : {}", goodsSource);        GoodsSource result = goodsSourceRepository.save(goodsSource);
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
        return goodsSourceRepository.findAll(pageable);
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
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GoodsSource> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of GoodsSources for query {}", query);
        return goodsSourceSearchRepository.search(queryStringQuery(query), pageable);    }
}
