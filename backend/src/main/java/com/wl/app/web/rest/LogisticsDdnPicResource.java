package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.LogisticsDdnPic;
import com.wl.app.service.LogisticsDdnPicService;
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
 * 这个类写的是对物流专线图片的管理
 * REST controller for managing LogisticsDdnPic.
 */
@RestController
@RequestMapping("/api")
public class LogisticsDdnPicResource {

    private final Logger log = LoggerFactory.getLogger(LogisticsDdnPicResource.class);

    private static final String ENTITY_NAME = "logisticsDdnPic";

    private final LogisticsDdnPicService logisticsDdnPicService;

    public LogisticsDdnPicResource(LogisticsDdnPicService logisticsDdnPicService) {
        this.logisticsDdnPicService = logisticsDdnPicService;
    }

    /**
     * POST  /logistics-ddn-pics : Create a new logisticsDdnPic.
     *
     * @param logisticsDdnPic the logisticsDdnPic to create
     * @return the ResponseEntity with status 201 (Created) and with body the new logisticsDdnPic, or with status 400 (Bad Request) if the logisticsDdnPic has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/logistics-ddn-pics")
    @Timed
    public ResponseEntity<LogisticsDdnPic> createLogisticsDdnPic(@Valid @RequestBody LogisticsDdnPic logisticsDdnPic) throws URISyntaxException {
        log.debug("REST request to save LogisticsDdnPic : {}", logisticsDdnPic);
        if (logisticsDdnPic.getId() != null) {
            throw new BadRequestAlertException("A new logisticsDdnPic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LogisticsDdnPic result = logisticsDdnPicService.save(logisticsDdnPic);
        return ResponseEntity.created(new URI("/api/logistics-ddn-pics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /logistics-ddn-pics : Updates an existing logisticsDdnPic.
     *
     * @param logisticsDdnPic the logisticsDdnPic to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated logisticsDdnPic,
     * or with status 400 (Bad Request) if the logisticsDdnPic is not valid,
     * or with status 500 (Internal Server Error) if the logisticsDdnPic couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/logistics-ddn-pics")
    @Timed
    public ResponseEntity<LogisticsDdnPic> updateLogisticsDdnPic(@Valid @RequestBody LogisticsDdnPic logisticsDdnPic) throws URISyntaxException {
        log.debug("REST request to update LogisticsDdnPic : {}", logisticsDdnPic);
        if (logisticsDdnPic.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LogisticsDdnPic result = logisticsDdnPicService.save(logisticsDdnPic);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, logisticsDdnPic.getId().toString()))
            .body(result);
    }

    /**
     * GET  /logistics-ddn-pics : get all the logisticsDdnPics.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of logisticsDdnPics in body
     */
    @GetMapping("/logistics-ddn-pics")
    @Timed
    public ResponseEntity<List<LogisticsDdnPic>> getAllLogisticsDdnPics(Pageable pageable) {
        log.debug("REST request to get a page of LogisticsDdnPics");
        Page<LogisticsDdnPic> page = logisticsDdnPicService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/logistics-ddn-pics");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /logistics-ddn-pics/:id : get the "id" logisticsDdnPic.
     *
     * @param id the id of the logisticsDdnPic to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the logisticsDdnPic, or with status 404 (Not Found)
     */
    @GetMapping("/logistics-ddn-pics/{id}")
    @Timed
    public ResponseEntity<LogisticsDdnPic> getLogisticsDdnPic(@PathVariable Long id) {
        log.debug("REST request to get LogisticsDdnPic : {}", id);
        Optional<LogisticsDdnPic> logisticsDdnPic = logisticsDdnPicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(logisticsDdnPic);
    }

    /**
     * DELETE  /logistics-ddn-pics/:id : delete the "id" logisticsDdnPic.
     *
     * @param id the id of the logisticsDdnPic to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/logistics-ddn-pics/{id}")
    @Timed
    public ResponseEntity<Void> deleteLogisticsDdnPic(@PathVariable Long id) {
        log.debug("REST request to delete LogisticsDdnPic : {}", id);
        logisticsDdnPicService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/logistics-ddn-pics?query=:query : search for the logisticsDdnPic corresponding
     * to the query.
     *
     * @param query the query of the logisticsDdnPic search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/logistics-ddn-pics")
    @Timed
    public ResponseEntity<List<LogisticsDdnPic>> searchLogisticsDdnPics(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of LogisticsDdnPics for query {}", query);
        Page<LogisticsDdnPic> page = logisticsDdnPicService.find(query,pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/logistics-ddn-pics");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
