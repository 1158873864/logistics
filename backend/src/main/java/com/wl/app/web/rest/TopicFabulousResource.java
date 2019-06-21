package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.TopicFabulous;
import com.wl.app.service.TopicFabulousService;
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
 * 对话题点赞的管理
 * REST controller for managing TopicFabulous.
 */
@RestController
@RequestMapping("/api")
public class TopicFabulousResource {

    private final Logger log = LoggerFactory.getLogger(TopicFabulousResource.class);

    private static final String ENTITY_NAME = "topicFabulous";

    private final TopicFabulousService topicFabulousService;

    public TopicFabulousResource(TopicFabulousService topicFabulousService) {
        this.topicFabulousService = topicFabulousService;
    }

    /**
     * POST  /topic-fabulous : Create a new topicFabulous.
     *
     * @param topicFabulous the topicFabulous to create
     * @return the ResponseEntity with status 201 (Created) and with body the new topicFabulous, or with status 400 (Bad Request) if the topicFabulous has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/topic-fabulous")
    @Timed
    public ResponseEntity<TopicFabulous> createTopicFabulous(@Valid @RequestBody TopicFabulous topicFabulous) throws URISyntaxException {
        log.debug("REST request to save TopicFabulous : {}", topicFabulous);
        if (topicFabulous.getId() != null) {
            throw new BadRequestAlertException("A new topicFabulous cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TopicFabulous result = topicFabulousService.save(topicFabulous);
        return ResponseEntity.created(new URI("/api/topic-fabulous/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /topic-fabulous : Updates an existing topicFabulous.
     *
     * @param topicFabulous the topicFabulous to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated topicFabulous,
     * or with status 400 (Bad Request) if the topicFabulous is not valid,
     * or with status 500 (Internal Server Error) if the topicFabulous couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/topic-fabulous")
    @Timed
    public ResponseEntity<TopicFabulous> updateTopicFabulous(@Valid @RequestBody TopicFabulous topicFabulous) throws URISyntaxException {
        log.debug("REST request to update TopicFabulous : {}", topicFabulous);
        if (topicFabulous.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TopicFabulous result = topicFabulousService.save(topicFabulous);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, topicFabulous.getId().toString()))
            .body(result);
    }

    /**
     * GET  /topic-fabulous : get all the topicFabulous.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of topicFabulous in body
     */
    @GetMapping("/topic-fabulous")
    @Timed
    public ResponseEntity<List<TopicFabulous>> getAllTopicFabulous(Pageable pageable) {
        log.debug("REST request to get a page of TopicFabulous");
        Page<TopicFabulous> page = topicFabulousService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/topic-fabulous");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /topic-fabulous/:id : get the "id" topicFabulous.
     *
     * @param id the id of the topicFabulous to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the topicFabulous, or with status 404 (Not Found)
     */
    @GetMapping("/topic-fabulous/{id}")
    @Timed
    public ResponseEntity<TopicFabulous> getTopicFabulous(@PathVariable Long id) {
        log.debug("REST request to get TopicFabulous : {}", id);
        Optional<TopicFabulous> topicFabulous = topicFabulousService.findOne(id);
        return ResponseUtil.wrapOrNotFound(topicFabulous);
    }

    /**
     * DELETE  /topic-fabulous/:id : delete the "id" topicFabulous.
     *
     * @param id the id of the topicFabulous to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/topic-fabulous/{id}")
    @Timed
    public ResponseEntity<Void> deleteTopicFabulous(@PathVariable Long id) {
        log.debug("REST request to delete TopicFabulous : {}", id);
        topicFabulousService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/topic-fabulous?query=:query : search for the topicFabulous corresponding
     * to the query.
     *
     * @param query the query of the topicFabulous search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/topic-fabulous")
    @Timed
    public ResponseEntity<List<TopicFabulous>> searchTopicFabulous(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of TopicFabulous for query {}", query);
        Page<TopicFabulous> page = topicFabulousService.find(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/topic-fabulous");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
