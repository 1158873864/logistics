package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.TopicViewed;
import com.wl.app.service.TopicViewedService;
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
 * REST controller for managing TopicViewed.
 */
@RestController
@RequestMapping("/api")
public class TopicViewedResource {

    private final Logger log = LoggerFactory.getLogger(TopicViewedResource.class);

    private static final String ENTITY_NAME = "topicViewed";

    private final TopicViewedService topicViewedService;

    public TopicViewedResource(TopicViewedService topicViewedService) {
        this.topicViewedService = topicViewedService;
    }

    /**
     * POST  /topic-vieweds : Create a new topicViewed.
     *
     * @param topicViewed the topicViewed to create
     * @return the ResponseEntity with status 201 (Created) and with body the new topicViewed, or with status 400 (Bad Request) if the topicViewed has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/topic-vieweds")
    @Timed
    public ResponseEntity<TopicViewed> createTopicViewed(@Valid @RequestBody TopicViewed topicViewed) throws URISyntaxException {
        log.debug("REST request to save TopicViewed : {}", topicViewed);
        if (topicViewed.getId() != null) {
            throw new BadRequestAlertException("A new topicViewed cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TopicViewed result = topicViewedService.save(topicViewed);
        return ResponseEntity.created(new URI("/api/topic-vieweds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /topic-vieweds : Updates an existing topicViewed.
     *
     * @param topicViewed the topicViewed to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated topicViewed,
     * or with status 400 (Bad Request) if the topicViewed is not valid,
     * or with status 500 (Internal Server Error) if the topicViewed couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/topic-vieweds")
    @Timed
    public ResponseEntity<TopicViewed> updateTopicViewed(@Valid @RequestBody TopicViewed topicViewed) throws URISyntaxException {
        log.debug("REST request to update TopicViewed : {}", topicViewed);
        if (topicViewed.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TopicViewed result = topicViewedService.save(topicViewed);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, topicViewed.getId().toString()))
            .body(result);
    }

    /**
     * GET  /topic-vieweds : get all the topicVieweds.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of topicVieweds in body
     */
    @GetMapping("/topic-vieweds")
    @Timed
    public ResponseEntity<List<TopicViewed>> getAllTopicVieweds(Pageable pageable) {
        log.debug("REST request to get a page of TopicVieweds");
        Page<TopicViewed> page = topicViewedService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/topic-vieweds");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /topic-vieweds/:id : get the "id" topicViewed.
     *
     * @param id the id of the topicViewed to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the topicViewed, or with status 404 (Not Found)
     */
    @GetMapping("/topic-vieweds/{id}")
    @Timed
    public ResponseEntity<TopicViewed> getTopicViewed(@PathVariable Long id) {
        log.debug("REST request to get TopicViewed : {}", id);
        Optional<TopicViewed> topicViewed = topicViewedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(topicViewed);
    }

    /**
     * DELETE  /topic-vieweds/:id : delete the "id" topicViewed.
     *
     * @param id the id of the topicViewed to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/topic-vieweds/{id}")
    @Timed
    public ResponseEntity<Void> deleteTopicViewed(@PathVariable Long id) {
        log.debug("REST request to delete TopicViewed : {}", id);
        topicViewedService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/topic-vieweds?query=:query : search for the topicViewed corresponding
     * to the query.
     *
     * @param query the query of the topicViewed search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/topic-vieweds")
    @Timed
    public ResponseEntity<List<TopicViewed>> searchTopicVieweds(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of TopicVieweds for query {}", query);
        Page<TopicViewed> page = topicViewedService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/topic-vieweds");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
