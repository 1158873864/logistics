package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.TopicForward;
import com.wl.app.service.TopicForwardService;
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
 * REST controller for managing TopicForward.
 */
@RestController
@RequestMapping("/api")
public class TopicForwardResource {

    private final Logger log = LoggerFactory.getLogger(TopicForwardResource.class);

    private static final String ENTITY_NAME = "topicForward";

    private final TopicForwardService topicForwardService;

    public TopicForwardResource(TopicForwardService topicForwardService) {
        this.topicForwardService = topicForwardService;
    }

    /**
     * POST  /topic-forwards : Create a new topicForward.
     *
     * @param topicForward the topicForward to create
     * @return the ResponseEntity with status 201 (Created) and with body the new topicForward, or with status 400 (Bad Request) if the topicForward has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/topic-forwards")
    @Timed
    public ResponseEntity<TopicForward> createTopicForward(@Valid @RequestBody TopicForward topicForward) throws URISyntaxException {
        log.debug("REST request to save TopicForward : {}", topicForward);
        if (topicForward.getId() != null) {
            throw new BadRequestAlertException("A new topicForward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TopicForward result = topicForwardService.save(topicForward);
        return ResponseEntity.created(new URI("/api/topic-forwards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /topic-forwards : Updates an existing topicForward.
     *
     * @param topicForward the topicForward to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated topicForward,
     * or with status 400 (Bad Request) if the topicForward is not valid,
     * or with status 500 (Internal Server Error) if the topicForward couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/topic-forwards")
    @Timed
    public ResponseEntity<TopicForward> updateTopicForward(@Valid @RequestBody TopicForward topicForward) throws URISyntaxException {
        log.debug("REST request to update TopicForward : {}", topicForward);
        if (topicForward.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TopicForward result = topicForwardService.save(topicForward);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, topicForward.getId().toString()))
            .body(result);
    }

    /**
     * GET  /topic-forwards : get all the topicForwards.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of topicForwards in body
     */
    @GetMapping("/topic-forwards")
    @Timed
    public ResponseEntity<List<TopicForward>> getAllTopicForwards(Pageable pageable) {
        log.debug("REST request to get a page of TopicForwards");
        Page<TopicForward> page = topicForwardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/topic-forwards");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /topic-forwards/:id : get the "id" topicForward.
     *
     * @param id the id of the topicForward to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the topicForward, or with status 404 (Not Found)
     */
    @GetMapping("/topic-forwards/{id}")
    @Timed
    public ResponseEntity<TopicForward> getTopicForward(@PathVariable Long id) {
        log.debug("REST request to get TopicForward : {}", id);
        Optional<TopicForward> topicForward = topicForwardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(topicForward);
    }

    /**
     * DELETE  /topic-forwards/:id : delete the "id" topicForward.
     *
     * @param id the id of the topicForward to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/topic-forwards/{id}")
    @Timed
    public ResponseEntity<Void> deleteTopicForward(@PathVariable Long id) {
        log.debug("REST request to delete TopicForward : {}", id);
        topicForwardService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/topic-forwards?query=:query : search for the topicForward corresponding
     * to the query.
     *
     * @param query the query of the topicForward search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/topic-forwards")
    @Timed
    public ResponseEntity<List<TopicForward>> searchTopicForwards(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of TopicForwards for query {}", query);
        Page<TopicForward> page = topicForwardService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/topic-forwards");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
