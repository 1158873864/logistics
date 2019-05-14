package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.LogisticsDdn;
import com.wl.app.domain.UserDdnFavorites;
import com.wl.app.domain.UserInfo;
import com.wl.app.service.LogisticsDdnService;
import com.wl.app.service.UserDdnFavoritesService;
import com.wl.app.service.UserInfoService;
import com.wl.app.web.rest.errors.BadRequestAlertException;
import com.wl.app.web.rest.errors.ResultGenerator;
import com.wl.app.web.rest.util.HeaderUtil;
import com.wl.app.web.rest.util.PaginationUtil;
import com.wl.app.web.rest.vm.UserDdnFavoritesVM;
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
 * 对用户专线收藏的管理
 * REST controller for managing UserDdnFavorites.
 */
@RestController
@RequestMapping("/api")
public class UserDdnFavoritesResource {

    private final Logger log = LoggerFactory.getLogger(UserDdnFavoritesResource.class);

    private static final String ENTITY_NAME = "userDdnFavorites";

    private final UserDdnFavoritesService userDdnFavoritesService;

    public UserDdnFavoritesResource(UserDdnFavoritesService userDdnFavoritesService) {
        this.userDdnFavoritesService = userDdnFavoritesService;

    }

    /**
     * POST  /user-ddn-favorites : Create a new userDdnFavorites.
     *
     * @param userDdnFavorites the userDdnFavorites to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userDdnFavorites, or with status 400 (Bad Request) if the userDdnFavorites has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-ddn-favorites")
    @Timed
    public ResponseEntity<UserDdnFavorites> createUserDdnFavorites(@Valid @RequestBody UserDdnFavorites userDdnFavorites) throws URISyntaxException {
        log.debug("REST request to save UserDdnFavorites : {}", userDdnFavorites);
        if (userDdnFavorites.getId() != null) {
            throw new BadRequestAlertException("A new userDdnFavorites cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserDdnFavorites result = userDdnFavoritesService.save(userDdnFavorites);
        return ResponseEntity.created(new URI("/api/user-ddn-favorites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }



    /**
     * PUT  /user-ddn-favorites : Updates an existing userDdnFavorites.
     *
     * @param userDdnFavorites the userDdnFavorites to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userDdnFavorites,
     * or with status 400 (Bad Request) if the userDdnFavorites is not valid,
     * or with status 500 (Internal Server Error) if the userDdnFavorites couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-ddn-favorites")
    @Timed
    public ResponseEntity<UserDdnFavorites> updateUserDdnFavorites(@Valid @RequestBody UserDdnFavorites userDdnFavorites) throws URISyntaxException {
        log.debug("REST request to update UserDdnFavorites : {}", userDdnFavorites);
        if (userDdnFavorites.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserDdnFavorites result = userDdnFavoritesService.save(userDdnFavorites);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userDdnFavorites.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-ddn-favorites : get all the userDdnFavorites.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userDdnFavorites in body
     */
    @GetMapping("/user-ddn-favorites")
    @Timed
    public ResponseEntity<List<UserDdnFavorites>> getAllUserDdnFavorites(Pageable pageable) {
        log.debug("REST request to get a page of UserDdnFavorites");
        Page<UserDdnFavorites> page = userDdnFavoritesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-ddn-favorites");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-ddn-favorites/:id : get the "id" userDdnFavorites.
     *
     * @param id the id of the userDdnFavorites to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userDdnFavorites, or with status 404 (Not Found)
     */
    @GetMapping("/user-ddn-favorites/{id}")
    @Timed
    public ResponseEntity<UserDdnFavorites> getUserDdnFavorites(@PathVariable Long id) {
        log.debug("REST request to get UserDdnFavorites : {}", id);
        Optional<UserDdnFavorites> userDdnFavorites = userDdnFavoritesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userDdnFavorites);
    }

    /**
     * DELETE  /user-ddn-favorites/:id : delete the "id" userDdnFavorites.
     *
     * @param id the id of the userDdnFavorites to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-ddn-favorites/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserDdnFavorites(@PathVariable Long id) {
        log.debug("REST request to delete UserDdnFavorites : {}", id);
        userDdnFavoritesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/user-ddn-favorites?query=:query : search for the userDdnFavorites corresponding
     * to the query.
     *
     * @param query the query of the userDdnFavorites search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/user-ddn-favorites")
    @Timed
    public ResponseEntity<List<UserDdnFavorites>> searchUserDdnFavorites(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of UserDdnFavorites for query {}", query);
        Page<UserDdnFavorites> page = userDdnFavoritesService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/user-ddn-favorites");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
