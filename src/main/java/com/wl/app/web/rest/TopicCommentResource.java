package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.TopicComment;
import com.wl.app.service.TopicCommentService;
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
 * REST controller for managing TopicComment.
 */
@RestController
@RequestMapping("/api")
public class TopicCommentResource {

    private final Logger log = LoggerFactory.getLogger(TopicCommentResource.class);

    private static final String ENTITY_NAME = "topicComment";

    private final TopicCommentService topicCommentService;

    public TopicCommentResource(TopicCommentService topicCommentService) {
        this.topicCommentService = topicCommentService;
    }

    /**
     * POST  /topic-comments : Create a new topicComment.
     *
     * @param topicComment the topicComment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new topicComment, or with status 400 (Bad Request) if the topicComment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/topic-comments")
    @Timed
    public ResponseEntity<TopicComment> createTopicComment(@Valid @RequestBody TopicComment topicComment) throws URISyntaxException {
        log.debug("REST request to save TopicComment : {}", topicComment);
        if (topicComment.getId() != null) {
            throw new BadRequestAlertException("A new topicComment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TopicComment result = topicCommentService.save(topicComment);
        return ResponseEntity.created(new URI("/api/topic-comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /topic-comments : Updates an existing topicComment.
     *
     * @param topicComment the topicComment to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated topicComment,
     * or with status 400 (Bad Request) if the topicComment is not valid,
     * or with status 500 (Internal Server Error) if the topicComment couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/topic-comments")
    @Timed
    public ResponseEntity<TopicComment> updateTopicComment(@Valid @RequestBody TopicComment topicComment) throws URISyntaxException {
        log.debug("REST request to update TopicComment : {}", topicComment);
        if (topicComment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TopicComment result = topicCommentService.save(topicComment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, topicComment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /topic-comments : get all the topicComments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of topicComments in body
     */
    @GetMapping("/topic-comments")
    @Timed
    public ResponseEntity<List<TopicComment>> getAllTopicComments(Pageable pageable) {
        log.debug("REST request to get a page of TopicComments");
        Page<TopicComment> page = topicCommentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/topic-comments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /topic-comments/:id : get the "id" topicComment.
     *
     * @param id the id of the topicComment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the topicComment, or with status 404 (Not Found)
     */
    @GetMapping("/topic-comments/{id}")
    @Timed
    public ResponseEntity<TopicComment> getTopicComment(@PathVariable Long id) {
        log.debug("REST request to get TopicComment : {}", id);
        Optional<TopicComment> topicComment = topicCommentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(topicComment);
    }

    /**
     * DELETE  /topic-comments/:id : delete the "id" topicComment.
     *
     * @param id the id of the topicComment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/topic-comments/{id}")
    @Timed
    public ResponseEntity<Void> deleteTopicComment(@PathVariable Long id) {
        log.debug("REST request to delete TopicComment : {}", id);
        topicCommentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/topic-comments?query=:query : search for the topicComment corresponding
     * to the query.
     *
     * @param query the query of the topicComment search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/topic-comments")
    @Timed
    public ResponseEntity<List<TopicComment>> searchTopicComments(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of TopicComments for query {}", query);
        Page<TopicComment> page = topicCommentService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/topic-comments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
