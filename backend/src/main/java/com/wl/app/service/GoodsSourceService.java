package com.wl.app.service;

import com.wl.app.domain.GoodsSource;

import com.wl.app.domain.enumeration.GoodsSourceProperty;
import com.wl.app.domain.enumeration.Status;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing GoodsSource.
 */
public interface GoodsSourceService {

    /**
     * Save a goodsSource.
     *
     * @param goodsSource the entity to save
     * @return the persisted entity
     */
    GoodsSource save(GoodsSource goodsSource);
    GoodsSource saveGoodsSource(GoodsSource goodsSource);

    /**
     * Get all the goodsSources.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GoodsSource> findAll(Pageable pageable);

    Page<GoodsSource> findAllGoodsSource(int size,int page);


    /**
     * Get the "id" goodsSource.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GoodsSource> findOne(Long id);

    /**
     * Delete the "id" goodsSource.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the goodsSource corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GoodsSource> search(String query, Pageable pageable);
    Page<GoodsSource> find(String query, Pageable pageable);

    Page<GoodsSource> findall( Pageable pageable,GoodsSource goodsSource);
    //GoodsSource findByCondition(Pageable pageable,String end,String Strat);
    public List<GoodsSource> findAllById(Long id);
    public List<GoodsSource> findGoodsSourceByCondition(String start, String end, GoodsSourceProperty goodsSourceProperty);
    public List<GoodsSource> findGoodsSourceByConditionTwo(String start, String end, GoodsSourceProperty goodsSourceProperty, Pageable pageable);
    public List<GoodsSource> findGoodsSourceByConditionThree(String start, String end, GoodsSourceProperty goodsSourceProperty, Pageable pageable);
    public List<GoodsSource> findGoodsSoourceByIdAndStatus(Status status,Long id);
    public List<GoodsSource> findGoodsSourceByConditionNEXT(String start, String end, GoodsSourceProperty goodsSourceProperty, Pageable pageable);
    public List<GoodsSource> findGoodsSourceBytime(String start, String end, GoodsSourceProperty goodsSourceProperty,int size,int page);
    public List<GoodsSource> findGoodsSourceByConditionNEXTone(String start, String end, GoodsSourceProperty goodsSourceProperty);
    }
