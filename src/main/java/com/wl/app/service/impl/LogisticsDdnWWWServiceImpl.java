package com.wl.app.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wl.app.config.ApplicationProperties;
import com.wl.app.domain.LogisticsDdn;
import com.wl.app.domain.LogisticsDdnWWW;
import com.wl.app.domain.enumeration.Status;
import com.wl.app.repository.LogisticsDdnRepository;
import com.wl.app.repository.LogisticsDdnWWWRepository;
import com.wl.app.repository.search.LogisticsDdnWWWSearchRepository;
import com.wl.app.service.LogisticsDdnWWWService;
import com.wl.app.service.dto.LogisticsDdnWwwImportDTO;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;


/**
 * Service Implementation for managing LogisticsDdnWWW.
 */
@Service
public class LogisticsDdnWWWServiceImpl implements LogisticsDdnWWWService {

    private final Logger log = LoggerFactory.getLogger(LogisticsDdnWWWServiceImpl.class);

    private final LogisticsDdnWWWRepository logisticsDdnWWWRepository;
    
    private final ApplicationProperties applicationProperties;

    private final LogisticsDdnWWWSearchRepository logisticsDdnWWWSearchRepository;
    
    private final LogisticsDdnRepository logisticsDdnRepository;

    public LogisticsDdnWWWServiceImpl(LogisticsDdnRepository logisticsDdnRepository,ApplicationProperties applicationProperties,LogisticsDdnWWWRepository logisticsDdnWWWRepository, LogisticsDdnWWWSearchRepository logisticsDdnWWWSearchRepository) {
        this.logisticsDdnWWWRepository = logisticsDdnWWWRepository;
        this.logisticsDdnWWWSearchRepository = logisticsDdnWWWSearchRepository;
        this.applicationProperties = applicationProperties;
        this.logisticsDdnRepository = logisticsDdnRepository;
    }

    /**
     * Save a logisticsDdnWWW.
     *
     * @param logisticsDdnWWW the entity to save
     * @return the persisted entity
     */
    @Override
    public LogisticsDdnWWW save(LogisticsDdnWWW logisticsDdnWWW) {
        log.debug("Request to save LogisticsDdnWWW : {}", logisticsDdnWWW);        LogisticsDdnWWW result = logisticsDdnWWWRepository.save(logisticsDdnWWW);
        logisticsDdnWWWSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the logisticsDdnWWWS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LogisticsDdnWWW> findAll(Pageable pageable) {
        log.debug("Request to get all LogisticsDdnWWWS");
        return logisticsDdnWWWRepository.findAll(pageable);
    }


    /**
     * Get one logisticsDdnWWW by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LogisticsDdnWWW> findOne(Long id) {
        log.debug("Request to get LogisticsDdnWWW : {}", id);
        return logisticsDdnWWWRepository.findById(id);
    }

    /**
     * Delete the logisticsDdnWWW by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LogisticsDdnWWW : {}", id);
        logisticsDdnWWWRepository.deleteById(id);
        logisticsDdnWWWSearchRepository.deleteById(id);
    }

    /**
     * Search for the logisticsDdnWWW corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LogisticsDdnWWW> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of LogisticsDdnWWWS for query {}", query);
        return logisticsDdnWWWSearchRepository.search(queryStringQuery(query), pageable);    }

	@Override
	public List<LogisticsDdnWWW> findAllByLogisticsDdnIdAndStatus(long logisticsDdnId, Status status) {
		return logisticsDdnWWWRepository.findAllByLogisticsDdnIdAndStatus(logisticsDdnId, status);
	}

	@Override
	public boolean batchImportDDN(String path) {
		ImportParams params = new ImportParams();
		List<LogisticsDdnWwwImportDTO> logisticsDdnWwwImportList = ExcelImportUtil.importExcel(
				new File(applicationProperties.getStaticResourcePath() + "/" + path), LogisticsDdnWwwImportDTO.class,
				params);
		List<String> errors = new ArrayList<>();
		for (LogisticsDdnWwwImportDTO logisticsDdnWwwImportDTO : logisticsDdnWwwImportList) {
			if (logisticsDdnWwwImportDTO.getTitle() != null && logisticsDdnWwwImportDTO.getManagerFullname() != null) {
				try {
					List<LogisticsDdn> logisticsDdnList = logisticsDdnRepository
							.findAllByTitleAndManagerFullname(logisticsDdnWwwImportDTO.getTitle().trim(),logisticsDdnWwwImportDTO.getManagerFullname().trim());

					if (logisticsDdnList != null && !logisticsDdnList.isEmpty()) {
						LogisticsDdn logisticsDdn = logisticsDdnList.get(0);
						LogisticsDdnWWW logisticsDdnWWW = new LogisticsDdnWWW();
						logisticsDdnWWW.setLogisticsDdn(logisticsDdn);
						logisticsDdnWWW.setContacts(logisticsDdnWwwImportDTO.getContacts());
						logisticsDdnWWW.setMobilePhone(cNULL(logisticsDdnWwwImportDTO.getＭobilePhone()));
						logisticsDdnWWW.setName(cNULL(logisticsDdnWwwImportDTO.getName()));
						logisticsDdnWWW.setPhone(cNULL(logisticsDdnWwwImportDTO.getPhone()));
						logisticsDdnWWW.setRemark(cNULL(logisticsDdnWwwImportDTO.getRemark()));
						logisticsDdnWWW.setStatus(logisticsDdnWwwImportDTO.getStatusEnum());
						this.save(logisticsDdnWWW);
					}else {
						errors.add(logisticsDdnWwwImportDTO.getTitle().trim()+"_"+logisticsDdnWwwImportDTO.getManagerFullname().trim());
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		
		for(String error : errors) {
			System.out.println(error);
		}
		return true;
	}
	
	private String cNULL(String str) {
		if(str==null) {
			return "";
		}
		return str.trim();
	}
}
