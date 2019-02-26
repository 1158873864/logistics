package com.wl.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.LogisticsDdn;
import com.wl.app.service.LogisticsDdnService;
import com.wl.app.service.StorageService;
import com.wl.app.web.rest.errors.BadRequestAlertException;
import com.wl.app.web.rest.util.HeaderUtil;
import com.wl.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing LogisticsDdn.
 */
@RestController
@RequestMapping("/api")
public class LogisticsDdnResource {

    private final Logger log = LoggerFactory.getLogger(LogisticsDdnResource.class);

    private static final String ENTITY_NAME = "logisticsDdn";

    private final LogisticsDdnService logisticsDdnService;
    
    private final StorageService storageService;

    public LogisticsDdnResource(LogisticsDdnService logisticsDdnService,StorageService storageService) {
        this.logisticsDdnService = logisticsDdnService;
        this.storageService = storageService;
    }

    /**
     * POST  /logistics-ddns : Create a new logisticsDdn.
     *
     * @param logisticsDdn the logisticsDdn to create
     * @return the ResponseEntity with status 201 (Created) and with body the new logisticsDdn, or with status 400 (Bad Request) if the logisticsDdn has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/logistics-ddns")
    @Timed
    public ResponseEntity<LogisticsDdn> createLogisticsDdn(@Valid @RequestBody LogisticsDdn logisticsDdn) throws URISyntaxException {
        log.debug("REST request to save LogisticsDdn : {}", logisticsDdn);
        if (logisticsDdn.getId() != null) {
            throw new BadRequestAlertException("A new logisticsDdn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LogisticsDdn result = logisticsDdnService.save(logisticsDdn);
        return ResponseEntity.created(new URI("/api/logistics-ddns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /logistics-ddns : Updates an existing logisticsDdn.
     *
     * @param logisticsDdn the logisticsDdn to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated logisticsDdn,
     * or with status 400 (Bad Request) if the logisticsDdn is not valid,
     * or with status 500 (Internal Server Error) if the logisticsDdn couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/logistics-ddns")
    @Timed
    public ResponseEntity<LogisticsDdn> updateLogisticsDdn(@Valid @RequestBody LogisticsDdn logisticsDdn) throws URISyntaxException {
        log.debug("REST request to update LogisticsDdn : {}", logisticsDdn);
        if (logisticsDdn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LogisticsDdn result = logisticsDdnService.save(logisticsDdn);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, logisticsDdn.getId().toString()))
            .body(result);
    }

    /**
     * GET  /logistics-ddns : get all the logisticsDdns.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of logisticsDdns in body
     */
    @GetMapping("/logistics-ddns")
    @Timed
    public ResponseEntity<List<LogisticsDdn>> getAllLogisticsDdns(Pageable pageable) {
        log.debug("REST request to get a page of LogisticsDdns");
        Page<LogisticsDdn> page = logisticsDdnService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/logistics-ddns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /logistics-ddns/:id : get the "id" logisticsDdn.
     *
     * @param id the id of the logisticsDdn to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the logisticsDdn, or with status 404 (Not Found)
     */
    @GetMapping("/logistics-ddns/{id}")
    @Timed
    public ResponseEntity<LogisticsDdn> getLogisticsDdn(@PathVariable Long id) {
        log.debug("REST request to get LogisticsDdn : {}", id);
        Optional<LogisticsDdn> logisticsDdn = logisticsDdnService.findOne(id);
        return ResponseUtil.wrapOrNotFound(logisticsDdn);
    }

    /**
     * DELETE  /logistics-ddns/:id : delete the "id" logisticsDdn.
     *
     * @param id the id of the logisticsDdn to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/logistics-ddns/{id}")
    @Timed
    public ResponseEntity<Void> deleteLogisticsDdn(@PathVariable Long id) {
        log.debug("REST request to delete LogisticsDdn : {}", id);
        logisticsDdnService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/logistics-ddns?query=:query : search for the logisticsDdn corresponding
     * to the query.
     *
     * @param query the query of the logisticsDdn search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/logistics-ddns")
    @Timed
    public ResponseEntity<List<LogisticsDdn>> searchLogisticsDdns(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of LogisticsDdns for query {}", query);
        Page<LogisticsDdn> page = logisticsDdnService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/logistics-ddns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @PostMapping("/logistics-ddns/batch-import")
	@ResponseBody
	public ResponseEntity<Boolean> uploadBatchImport(@RequestParam("file") MultipartFile file) {
    	Boolean isSuccess = null;

		String path = storageService.store("logistics-ddns",file);
		System.out.println(path);
		if (!path.equals("error")) {
			isSuccess = logisticsDdnService.batchImportDDN(path);
		} 
		return ResponseUtil.wrapOrNotFound(Optional.of(isSuccess));
	}
    
    @PostMapping("/logistics-ddns/batch-import-pics")
	@ResponseBody
	public ResponseEntity<Boolean> uploadBatchImportPics(@RequestParam("file") MultipartFile[] files) {
    	Boolean isSuccess = false;

//		String path = storageService.store("logistics-ddns",file);
//		if (!path.equals("error")) {
//			isSuccess = logisticsDdnService.batchImportDDN(path);
//		} 
		
		
		Map<String,Object> result =  new HashMap<>();
		
		List<String> pics = new ArrayList<>();
		for(MultipartFile file : files){
			//String fileName = file.getOriginalFilename();
			String path = storageService.store("logistics-ddn-pics",file);
			pics.add(path);
			//logisticsDdnService.importDDNPics("","",path,false);
		}
		
        if(!pics.isEmpty()) {
        	isSuccess = true;
        }else {
        	isSuccess = false;
        }
		
		return ResponseUtil.wrapOrNotFound(Optional.of(isSuccess));
	}

}
