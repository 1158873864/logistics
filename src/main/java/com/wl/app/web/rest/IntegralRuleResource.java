package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.IntegralRule;
import com.wl.app.service.IntegralRuleService;
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
 * REST controller for managing IntegralRule.
 */
@RestController
@RequestMapping("/api")
public class IntegralRuleResource {

    private final Logger log = LoggerFactory.getLogger(IntegralRuleResource.class);

    private static final String ENTITY_NAME = "integralRule";

    private final IntegralRuleService integralRuleService;

    public IntegralRuleResource(IntegralRuleService integralRuleService) {
        this.integralRuleService = integralRuleService;
    }

    /**
     * POST  /integral-rules : Create a new integralRule.
     *
     * @param integralRule the integralRule to create
     * @return the ResponseEntity with status 201 (Created) and with body the new integralRule, or with status 400 (Bad Request) if the integralRule has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/integral-rules")
    @Timed
    public ResponseEntity<IntegralRule> createIntegralRule(@Valid @RequestBody IntegralRule integralRule) throws URISyntaxException {
        log.debug("REST request to save IntegralRule : {}", integralRule);
        if (integralRule.getId() != null) {
            throw new BadRequestAlertException("A new integralRule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IntegralRule result = integralRuleService.save(integralRule);
        return ResponseEntity.created(new URI("/api/integral-rules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /integral-rules : Updates an existing integralRule.
     *
     * @param integralRule the integralRule to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated integralRule,
     * or with status 400 (Bad Request) if the integralRule is not valid,
     * or with status 500 (Internal Server Error) if the integralRule couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/integral-rules")
    @Timed
    public ResponseEntity<IntegralRule> updateIntegralRule(@Valid @RequestBody IntegralRule integralRule) throws URISyntaxException {
        log.debug("REST request to update IntegralRule : {}", integralRule);
        if (integralRule.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IntegralRule result = integralRuleService.save(integralRule);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, integralRule.getId().toString()))
            .body(result);
    }

    /**
     * GET  /integral-rules : get all the integralRules.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of integralRules in body
     */
    @GetMapping("/integral-rules")
    @Timed
    public ResponseEntity<List<IntegralRule>> getAllIntegralRules(Pageable pageable) {
        log.debug("REST request to get a page of IntegralRules");
        Page<IntegralRule> page = integralRuleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/integral-rules");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /integral-rules/:id : get the "id" integralRule.
     *
     * @param id the id of the integralRule to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the integralRule, or with status 404 (Not Found)
     */
    @GetMapping("/integral-rules/{id}")
    @Timed
    public ResponseEntity<IntegralRule> getIntegralRule(@PathVariable Long id) {
        log.debug("REST request to get IntegralRule : {}", id);
        Optional<IntegralRule> integralRule = integralRuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(integralRule);
    }

    /**
     * DELETE  /integral-rules/:id : delete the "id" integralRule.
     *
     * @param id the id of the integralRule to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/integral-rules/{id}")
    @Timed
    public ResponseEntity<Void> deleteIntegralRule(@PathVariable Long id) {
        log.debug("REST request to delete IntegralRule : {}", id);
        integralRuleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/integral-rules?query=:query : search for the integralRule corresponding
     * to the query.
     *
     * @param query the query of the integralRule search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/integral-rules")
    @Timed
    public ResponseEntity<List<IntegralRule>> searchIntegralRules(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of IntegralRules for query {}", query);
        Page<IntegralRule> page = integralRuleService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/integral-rules");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
