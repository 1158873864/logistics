package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.SysRecruitmentInformation;
import com.wl.app.service.SysRecruitmentInformationService;
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
 * REST controller for managing SysRecruitmentInformation.
 */
@RestController
@RequestMapping("/api")
public class SysRecruitmentInformationResource {

    private final Logger log = LoggerFactory.getLogger(SysRecruitmentInformationResource.class);

    private static final String ENTITY_NAME = "sysRecruitmentInformation";

    private final SysRecruitmentInformationService sysRecruitmentInformationService;

    public SysRecruitmentInformationResource(SysRecruitmentInformationService sysRecruitmentInformationService) {
        this.sysRecruitmentInformationService = sysRecruitmentInformationService;
    }

    /**
     * POST  /sys-recruitment-informations : Create a new sysRecruitmentInformation.
     *
     * @param sysRecruitmentInformation the sysRecruitmentInformation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysRecruitmentInformation, or with status 400 (Bad Request) if the sysRecruitmentInformation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-recruitment-informations")
    @Timed
    public ResponseEntity<SysRecruitmentInformation> createSysRecruitmentInformation(@Valid @RequestBody SysRecruitmentInformation sysRecruitmentInformation) throws URISyntaxException {
        log.debug("REST request to save SysRecruitmentInformation : {}", sysRecruitmentInformation);
        if (sysRecruitmentInformation.getId() != null) {
            throw new BadRequestAlertException("A new sysRecruitmentInformation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRecruitmentInformation result = sysRecruitmentInformationService.save(sysRecruitmentInformation);
        return ResponseEntity.created(new URI("/api/sys-recruitment-informations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-recruitment-informations : Updates an existing sysRecruitmentInformation.
     *
     * @param sysRecruitmentInformation the sysRecruitmentInformation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysRecruitmentInformation,
     * or with status 400 (Bad Request) if the sysRecruitmentInformation is not valid,
     * or with status 500 (Internal Server Error) if the sysRecruitmentInformation couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-recruitment-informations")
    @Timed
    public ResponseEntity<SysRecruitmentInformation> updateSysRecruitmentInformation(@Valid @RequestBody SysRecruitmentInformation sysRecruitmentInformation) throws URISyntaxException {
        log.debug("REST request to update SysRecruitmentInformation : {}", sysRecruitmentInformation);
        if (sysRecruitmentInformation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysRecruitmentInformation result = sysRecruitmentInformationService.save(sysRecruitmentInformation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysRecruitmentInformation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-recruitment-informations : get all the sysRecruitmentInformations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sysRecruitmentInformations in body
     */
    @GetMapping("/sys-recruitment-informations")
    @Timed
    public ResponseEntity<List<SysRecruitmentInformation>> getAllSysRecruitmentInformations(Pageable pageable) {
        log.debug("REST request to get a page of SysRecruitmentInformations");
        Page<SysRecruitmentInformation> page = sysRecruitmentInformationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sys-recruitment-informations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sys-recruitment-informations/:id : get the "id" sysRecruitmentInformation.
     *
     * @param id the id of the sysRecruitmentInformation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysRecruitmentInformation, or with status 404 (Not Found)
     */
    @GetMapping("/sys-recruitment-informations/{id}")
    @Timed
    public ResponseEntity<SysRecruitmentInformation> getSysRecruitmentInformation(@PathVariable Long id) {
        log.debug("REST request to get SysRecruitmentInformation : {}", id);
        Optional<SysRecruitmentInformation> sysRecruitmentInformation = sysRecruitmentInformationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysRecruitmentInformation);
    }

    /**
     * DELETE  /sys-recruitment-informations/:id : delete the "id" sysRecruitmentInformation.
     *
     * @param id the id of the sysRecruitmentInformation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-recruitment-informations/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysRecruitmentInformation(@PathVariable Long id) {
        log.debug("REST request to delete SysRecruitmentInformation : {}", id);
        sysRecruitmentInformationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sys-recruitment-informations?query=:query : search for the sysRecruitmentInformation corresponding
     * to the query.
     *
     * @param query the query of the sysRecruitmentInformation search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/sys-recruitment-informations")
    @Timed
    public ResponseEntity<List<SysRecruitmentInformation>> searchSysRecruitmentInformations(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of SysRecruitmentInformations for query {}", query);
        Page<SysRecruitmentInformation> page = sysRecruitmentInformationService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/sys-recruitment-informations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
