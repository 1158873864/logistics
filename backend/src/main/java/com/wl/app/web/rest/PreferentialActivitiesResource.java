package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.PreferentialActivities;
import com.wl.app.service.PreferentialActivitiesService;
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
 * 对优惠活动的管理
 * REST controller for managing PreferentialActivities.
 */
@RestController
@RequestMapping("/api")
public class PreferentialActivitiesResource {

    private final Logger log = LoggerFactory.getLogger(PreferentialActivitiesResource.class);

    private static final String ENTITY_NAME = "preferentialActivities";

    private final PreferentialActivitiesService preferentialActivitiesService;

    public PreferentialActivitiesResource(PreferentialActivitiesService preferentialActivitiesService) {
        this.preferentialActivitiesService = preferentialActivitiesService;
    }

    /**
     * POST  /preferential-activities : Create a new preferentialActivities.
     *
     * @param preferentialActivities the preferentialActivities to create
     * @return the ResponseEntity with status 201 (Created) and with body the new preferentialActivities, or with status 400 (Bad Request) if the preferentialActivities has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/preferential-activities")
    @Timed
    public ResponseEntity<PreferentialActivities> createPreferentialActivities(@Valid @RequestBody PreferentialActivities preferentialActivities) throws URISyntaxException {
        log.debug("REST request to save PreferentialActivities : {}", preferentialActivities);
        if (preferentialActivities.getId() != null) {
            throw new BadRequestAlertException("A new preferentialActivities cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PreferentialActivities result = preferentialActivitiesService.save(preferentialActivities);
        return ResponseEntity.created(new URI("/api/preferential-activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /preferential-activities : Updates an existing preferentialActivities.
     *
     * @param preferentialActivities the preferentialActivities to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated preferentialActivities,
     * or with status 400 (Bad Request) if the preferentialActivities is not valid,
     * or with status 500 (Internal Server Error) if the preferentialActivities couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/preferential-activities")
    @Timed
    public ResponseEntity<PreferentialActivities> updatePreferentialActivities(@Valid @RequestBody PreferentialActivities preferentialActivities) throws URISyntaxException {
        log.debug("REST request to update PreferentialActivities : {}", preferentialActivities);
        if (preferentialActivities.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PreferentialActivities result = preferentialActivitiesService.save(preferentialActivities);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, preferentialActivities.getId().toString()))
            .body(result);
    }

    /**
     * GET  /preferential-activities : get all the preferentialActivities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of preferentialActivities in body
     */
    @GetMapping("/preferential-activities")
    @Timed
    public ResponseEntity<List<PreferentialActivities>> getAllPreferentialActivities(Pageable pageable) {
        log.debug("REST request to get a page of PreferentialActivities");
        Page<PreferentialActivities> page = preferentialActivitiesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/preferential-activities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /preferential-activities/:id : get the "id" preferentialActivities.
     *
     * @param id the id of the preferentialActivities to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the preferentialActivities, or with status 404 (Not Found)
     */
    @GetMapping("/preferential-activities/{id}")
    @Timed
    public ResponseEntity<PreferentialActivities> getPreferentialActivities(@PathVariable Long id) {
        log.debug("REST request to get PreferentialActivities : {}", id);
        Optional<PreferentialActivities> preferentialActivities = preferentialActivitiesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(preferentialActivities);
    }

    /**
     * DELETE  /preferential-activities/:id : delete the "id" preferentialActivities.
     *
     * @param id the id of the preferentialActivities to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/preferential-activities/{id}")
    @Timed
    public ResponseEntity<Void> deletePreferentialActivities(@PathVariable Long id) {
        log.debug("REST request to delete PreferentialActivities : {}", id);
        preferentialActivitiesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/preferential-activities?query=:query : search for the preferentialActivities corresponding
     * to the query.
     *
     * @param query the query of the preferentialActivities search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/preferential-activities")
    @Timed
    public ResponseEntity<List<PreferentialActivities>> searchPreferentialActivities(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PreferentialActivities for query {}", query);
        Page<PreferentialActivities> page = preferentialActivitiesService.find(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/preferential-activities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
