package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.GoodsExchange;
import com.wl.app.service.GoodsExchangeService;
import com.wl.app.web.rest.errors.BadRequestAlertException;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing GoodsExchange.
 */
@RestController
@RequestMapping("/api")
public class GoodsExchangeResource {

    private final Logger log = LoggerFactory.getLogger(GoodsExchangeResource.class);

    private static final String ENTITY_NAME = "goodsExchange";

    private final GoodsExchangeService goodsExchangeService;

    public GoodsExchangeResource(GoodsExchangeService goodsExchangeService) {
        this.goodsExchangeService = goodsExchangeService;
    }

    /**
     * POST  /goods-exchanges : Create a new goodsExchange.
     *
     * @param goodsExchange the goodsExchange to create
     * @return the ResponseEntity with status 201 (Created) and with body the new goodsExchange, or with status 400 (Bad Request) if the goodsExchange has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/goods-exchanges")
    @Timed
    public ResponseEntity<GoodsExchange> createGoodsExchange(@Valid @RequestBody GoodsExchange goodsExchange) throws URISyntaxException {
        log.debug("REST request to save GoodsExchange : {}", goodsExchange);
        if (goodsExchange.getId() != null) {
            throw new BadRequestAlertException("A new goodsExchange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GoodsExchange result = goodsExchangeService.save(goodsExchange);
        return ResponseEntity.created(new URI("/api/goods-exchanges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /goods-exchanges : Updates an existing goodsExchange.
     *
     * @param goodsExchange the goodsExchange to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated goodsExchange,
     * or with status 400 (Bad Request) if the goodsExchange is not valid,
     * or with status 500 (Internal Server Error) if the goodsExchange couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/goods-exchanges")
    @Timed
    public ResponseEntity<GoodsExchange> updateGoodsExchange(@Valid @RequestBody GoodsExchange goodsExchange) throws URISyntaxException {
        log.debug("REST request to update GoodsExchange : {}", goodsExchange);
        if (goodsExchange.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GoodsExchange result = goodsExchangeService.save(goodsExchange);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, goodsExchange.getId().toString()))
            .body(result);
    }

    /**
     * GET  /goods-exchanges : get all the goodsExchanges.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of goodsExchanges in body
     */
    @GetMapping("/goods-exchanges")
    @Timed
    public ResponseEntity<List<GoodsExchange>> getAllGoodsExchanges(Pageable pageable) {
        log.debug("REST request to get a page of GoodsExchanges");
        Page<GoodsExchange> page = goodsExchangeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/goods-exchanges");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /goods-exchanges/:id : get the "id" goodsExchange.
     *
     * @param id the id of the goodsExchange to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the goodsExchange, or with status 404 (Not Found)
     */
    @GetMapping("/goods-exchanges/{id}")
    @Timed
    public ResponseEntity<GoodsExchange> getGoodsExchange(@PathVariable Long id) {
        log.debug("REST request to get GoodsExchange : {}", id);
        Optional<GoodsExchange> goodsExchange = goodsExchangeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(goodsExchange);
    }

    /**
     * DELETE  /goods-exchanges/:id : delete the "id" goodsExchange.
     *
     * @param id the id of the goodsExchange to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/goods-exchanges/{id}")
    @Timed
    public ResponseEntity<Void> deleteGoodsExchange(@PathVariable Long id) {
        log.debug("REST request to delete GoodsExchange : {}", id);
        goodsExchangeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/goods-exchanges?query=:query : search for the goodsExchange corresponding
     * to the query.
     *
     * @param query the query of the goodsExchange search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/goods-exchanges")
    @Timed
    public ResponseEntity<List<GoodsExchange>> searchGoodsExchanges(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of GoodsExchanges for query {}", query);
        Page<GoodsExchange> page = goodsExchangeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/goods-exchanges");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
