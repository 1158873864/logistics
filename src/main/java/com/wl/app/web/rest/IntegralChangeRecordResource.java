package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.IntegralChangeRecord;
import com.wl.app.service.IntegralChangeRecordService;
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
 * REST controller for managing IntegralChangeRecord.
 */
@RestController
@RequestMapping("/api")
public class IntegralChangeRecordResource {

    private final Logger log = LoggerFactory.getLogger(IntegralChangeRecordResource.class);

    private static final String ENTITY_NAME = "integralChangeRecord";

    private final IntegralChangeRecordService integralChangeRecordService;

    public IntegralChangeRecordResource(IntegralChangeRecordService integralChangeRecordService) {
        this.integralChangeRecordService = integralChangeRecordService;
    }

    /**
     * POST  /integral-change-records : Create a new integralChangeRecord.
     *
     * @param integralChangeRecord the integralChangeRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new integralChangeRecord, or with status 400 (Bad Request) if the integralChangeRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/integral-change-records")
    @Timed
    public ResponseEntity<IntegralChangeRecord> createIntegralChangeRecord(@Valid @RequestBody IntegralChangeRecord integralChangeRecord) throws URISyntaxException {
        log.debug("REST request to save IntegralChangeRecord : {}", integralChangeRecord);
        if (integralChangeRecord.getId() != null) {
            throw new BadRequestAlertException("A new integralChangeRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IntegralChangeRecord result = integralChangeRecordService.save(integralChangeRecord);
        return ResponseEntity.created(new URI("/api/integral-change-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /integral-change-records : Updates an existing integralChangeRecord.
     *
     * @param integralChangeRecord the integralChangeRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated integralChangeRecord,
     * or with status 400 (Bad Request) if the integralChangeRecord is not valid,
     * or with status 500 (Internal Server Error) if the integralChangeRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/integral-change-records")
    @Timed
    public ResponseEntity<IntegralChangeRecord> updateIntegralChangeRecord(@Valid @RequestBody IntegralChangeRecord integralChangeRecord) throws URISyntaxException {
        log.debug("REST request to update IntegralChangeRecord : {}", integralChangeRecord);
        if (integralChangeRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IntegralChangeRecord result = integralChangeRecordService.save(integralChangeRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, integralChangeRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /integral-change-records : get all the integralChangeRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of integralChangeRecords in body
     */
    @GetMapping("/integral-change-records")
    @Timed
    public ResponseEntity<List<IntegralChangeRecord>> getAllIntegralChangeRecords(Pageable pageable) {
        log.debug("REST request to get a page of IntegralChangeRecords");
        Page<IntegralChangeRecord> page = integralChangeRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/integral-change-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /integral-change-records/:id : get the "id" integralChangeRecord.
     *
     * @param id the id of the integralChangeRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the integralChangeRecord, or with status 404 (Not Found)
     */
    @GetMapping("/integral-change-records/{id}")
    @Timed
    public ResponseEntity<IntegralChangeRecord> getIntegralChangeRecord(@PathVariable Long id) {
        log.debug("REST request to get IntegralChangeRecord : {}", id);
        Optional<IntegralChangeRecord> integralChangeRecord = integralChangeRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(integralChangeRecord);
    }

    /**
     * DELETE  /integral-change-records/:id : delete the "id" integralChangeRecord.
     *
     * @param id the id of the integralChangeRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/integral-change-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteIntegralChangeRecord(@PathVariable Long id) {
        log.debug("REST request to delete IntegralChangeRecord : {}", id);
        integralChangeRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/integral-change-records?query=:query : search for the integralChangeRecord corresponding
     * to the query.
     *
     * @param query the query of the integralChangeRecord search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/integral-change-records")
    @Timed
    public ResponseEntity<List<IntegralChangeRecord>> searchIntegralChangeRecords(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of IntegralChangeRecords for query {}", query);
        Page<IntegralChangeRecord> page = integralChangeRecordService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/integral-change-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
