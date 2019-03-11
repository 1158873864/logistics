package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.GoodsSource;
import com.wl.app.domain.enumeration.GoodsSourceProperty;
import com.wl.app.service.GoodsSourceService;
import com.wl.app.web.rest.errors.BadRequestAlertException;
import com.wl.app.web.rest.errors.Result;
import com.wl.app.web.rest.errors.ResultGenerator;
import com.wl.app.web.rest.util.HeaderUtil;
import com.wl.app.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing GoodsSource.
 */
@RestController
@RequestMapping("/api")
public class GoodsSourceResource {

    private final Logger log = LoggerFactory.getLogger(GoodsSourceResource.class);

    private static final String ENTITY_NAME = "goodsSource";

    private final GoodsSourceService goodsSourceService;

    public GoodsSourceResource(GoodsSourceService goodsSourceService) {
        this.goodsSourceService = goodsSourceService;
    }

    /**
     * POST  /goods-sources : Create a new goodsSource.
     *
     * @param goodsSource the goodsSource to create
     * @return the ResponseEntity with status 201 (Created) and with body the new goodsSource, or with status 400 (Bad Request) if the goodsSource has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/goods-sources")
    @Timed
    public ResponseEntity<GoodsSource> createGoodsSource(@Valid @RequestBody GoodsSource goodsSource) throws URISyntaxException {
        log.debug("REST request to save GoodsSource : {}", goodsSource);
        if (goodsSource.getId() != null) {
            throw new BadRequestAlertException("A new goodsSource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GoodsSource result = goodsSourceService.save(goodsSource);
        return ResponseEntity.created(new URI("/api/goods-sources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /goods-sources : Updates an existing goodsSource.
     *
     * @param goodsSource the goodsSource to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated goodsSource,
     * or with status 400 (Bad Request) if the goodsSource is not valid,
     * or with status 500 (Internal Server Error) if the goodsSource couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/goods-sources")
    @Timed
    public ResponseEntity<GoodsSource> updateGoodsSource(@Valid @RequestBody GoodsSource goodsSource) throws URISyntaxException {
        log.debug("REST request to update GoodsSource : {}", goodsSource);
        if (goodsSource.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GoodsSource result = goodsSourceService.save(goodsSource);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, goodsSource.getId().toString()))
            .body(result);
    }

    /**
     * GET  /goods-sources : get all the goodsSources.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of goodsSources in body
     */
    @GetMapping("/goods-sources")
    @Timed
    public ResponseEntity<List<GoodsSource>> getAllGoodsSources(Pageable pageable) {
        log.debug("REST request to get a page of GoodsSources");
        Page<GoodsSource> page = goodsSourceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/goods-sources");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /goods-sources/:id : get the "id" goodsSource.
     *
     * @param id the id of the goodsSource to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the goodsSource, or with status 404 (Not Found)
     */
    @GetMapping("/goods-sources/{id}")
    @Timed
    public ResponseEntity<GoodsSource> getGoodsSource(@PathVariable Long id) {
        log.debug("REST request to get GoodsSource : {}", id);
        Optional<GoodsSource> goodsSource = goodsSourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(goodsSource);
    }

    /**
     * DELETE  /goods-sources/:id : delete the "id" goodsSource.
     *
     * @param id the id of the goodsSource to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/goods-sources/{id}")
    @Timed
    public ResponseEntity<Void> deleteGoodsSource(@PathVariable Long id) {
        log.debug("REST request to delete GoodsSource : {}", id);
        goodsSourceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/goods-sources?query=:query : search for the goodsSource corresponding
     * to the query.
     *
     * @param query the query of the goodsSource search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/goods-sources")
    @Timed
    public ResponseEntity<List<GoodsSource>> searchGoodsSources(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of GoodsSources for query {}", query);
        Page<GoodsSource> page = goodsSourceService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/goods-sources");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
