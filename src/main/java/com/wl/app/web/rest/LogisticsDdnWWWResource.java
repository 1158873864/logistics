package com.wl.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.LogisticsDdnWWW;
import com.wl.app.service.LogisticsDdnWWWService;
import com.wl.app.service.StorageService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * 这个类是对物流专线网点的管理
 * REST controller for managing LogisticsDdnWWW.
 */
@RestController
@RequestMapping("/api")
public class LogisticsDdnWWWResource {

    private final Logger log = LoggerFactory.getLogger(LogisticsDdnWWWResource.class);

    private static final String ENTITY_NAME = "logisticsDdnWWW";

    private final LogisticsDdnWWWService logisticsDdnWWWService;
    
    private final StorageService storageService;


    public LogisticsDdnWWWResource(LogisticsDdnWWWService logisticsDdnWWWService, StorageService storageService) {
		super();
		this.logisticsDdnWWWService = logisticsDdnWWWService;
		this.storageService = storageService;
	}

	/**
     * POST  /logistics-ddn-wwws : Create a new logisticsDdnWWW.
     *
     * @param logisticsDdnWWW the logisticsDdnWWW to create
     * @return the ResponseEntity with status 201 (Created) and with body the new logisticsDdnWWW, or with status 400 (Bad Request) if the logisticsDdnWWW has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/logistics-ddn-wwws")
    @Timed
    public ResponseEntity<LogisticsDdnWWW> createLogisticsDdnWWW(@Valid @RequestBody LogisticsDdnWWW logisticsDdnWWW) throws URISyntaxException {
        log.debug("REST request to save LogisticsDdnWWW : {}", logisticsDdnWWW);
        if (logisticsDdnWWW.getId() != null) {
            throw new BadRequestAlertException("A new logisticsDdnWWW cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LogisticsDdnWWW result = logisticsDdnWWWService.save(logisticsDdnWWW);
        return ResponseEntity.created(new URI("/api/logistics-ddn-wwws/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /logistics-ddn-wwws : Updates an existing logisticsDdnWWW.
     *
     * @param logisticsDdnWWW the logisticsDdnWWW to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated logisticsDdnWWW,
     * or with status 400 (Bad Request) if the logisticsDdnWWW is not valid,
     * or with status 500 (Internal Server Error) if the logisticsDdnWWW couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/logistics-ddn-wwws")
    @Timed
    public ResponseEntity<LogisticsDdnWWW> updateLogisticsDdnWWW(@Valid @RequestBody LogisticsDdnWWW logisticsDdnWWW) throws URISyntaxException {
        log.debug("REST request to update LogisticsDdnWWW : {}", logisticsDdnWWW);
        if (logisticsDdnWWW.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LogisticsDdnWWW result = logisticsDdnWWWService.save(logisticsDdnWWW);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, logisticsDdnWWW.getId().toString()))
            .body(result);
    }

    /**
     * GET  /logistics-ddn-wwws : get all the logisticsDdnWWWS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of logisticsDdnWWWS in body
     */
    @GetMapping("/logistics-ddn-wwws")
    @Timed
    public ResponseEntity<List<LogisticsDdnWWW>> getAllLogisticsDdnWWWS(Pageable pageable) {
        log.debug("REST request to get a page of LogisticsDdnWWWS");
        Page<LogisticsDdnWWW> page = logisticsDdnWWWService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/logistics-ddn-wwws");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /logistics-ddn-wwws/:id : get the "id" logisticsDdnWWW.
     *
     * @param id the id of the logisticsDdnWWW to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the logisticsDdnWWW, or with status 404 (Not Found)
     */
    @GetMapping("/logistics-ddn-wwws/{id}")
    @Timed
    public ResponseEntity<LogisticsDdnWWW> getLogisticsDdnWWW(@PathVariable Long id) {
        log.debug("REST request to get LogisticsDdnWWW : {}", id);
        Optional<LogisticsDdnWWW> logisticsDdnWWW = logisticsDdnWWWService.findOne(id);
        return ResponseUtil.wrapOrNotFound(logisticsDdnWWW);
    }

    /**
     * DELETE  /logistics-ddn-wwws/:id : delete the "id" logisticsDdnWWW.
     *
     * @param id the id of the logisticsDdnWWW to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/logistics-ddn-wwws/{id}")
    @Timed
    public ResponseEntity<Void> deleteLogisticsDdnWWW(@PathVariable Long id) {
        log.debug("REST request to delete LogisticsDdnWWW : {}", id);
        logisticsDdnWWWService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/logistics-ddn-wwws?query=:query : search for the logisticsDdnWWW corresponding
     * to the query.
     *
     * @param query the query of the logisticsDdnWWW search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/logistics-ddn-wwws")
    @Timed
    public ResponseEntity<List<LogisticsDdnWWW>> searchLogisticsDdnWWWS(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of LogisticsDdnWWWS for query {}", query);
        Page<LogisticsDdnWWW> page = logisticsDdnWWWService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/logistics-ddn-wwws");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @PostMapping("/logistics-ddn-wwws/batch-import")
	@ResponseBody
	public ResponseEntity<Boolean> uploadUserAccounts(@RequestParam("file") MultipartFile file) {
    	Boolean isSuccess = null;

		String path = storageService.store("logistics-ddn-www",file);
		if (!path.equals("error")) {
			isSuccess = logisticsDdnWWWService.batchImportDDN(path);
		} 
		return ResponseUtil.wrapOrNotFound(Optional.of(isSuccess));
	}

}
