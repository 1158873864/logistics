package com.wl.app.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wl.app.config.ApplicationProperties;
import com.wl.app.domain.LogisticsDdn;
import com.wl.app.domain.LogisticsDdnPic;
import com.wl.app.domain.enumeration.Status;
import com.wl.app.repository.LogisticsDdnRepository;
import com.wl.app.repository.search.LogisticsDdnSearchRepository;
import com.wl.app.service.LogisticsDdnPicService;
import com.wl.app.service.LogisticsDdnService;
import com.wl.app.service.dto.LogisticsDdnImportDTO;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

/**
 * Service Implementation for managing LogisticsDdn.
 */
@Service
@Transactional
public class LogisticsDdnServiceImpl implements LogisticsDdnService {

	private final Logger log = LoggerFactory.getLogger(LogisticsDdnServiceImpl.class);

	private final LogisticsDdnRepository logisticsDdnRepository;

	private final LogisticsDdnSearchRepository logisticsDdnSearchRepository;
	
	private final LogisticsDdnPicService logisticsDdnPicService;

	private final ApplicationProperties applicationProperties;

	public LogisticsDdnServiceImpl(ApplicationProperties applicationProperties,
			LogisticsDdnRepository logisticsDdnRepository, LogisticsDdnSearchRepository logisticsDdnSearchRepository,LogisticsDdnPicService logisticsDdnPicService) {
		this.logisticsDdnRepository = logisticsDdnRepository;
		this.logisticsDdnSearchRepository = logisticsDdnSearchRepository;
		this.applicationProperties = applicationProperties;
		this.logisticsDdnPicService = logisticsDdnPicService;
	}

	/**
	 * Save a logisticsDdn.
	 *
	 * @param logisticsDdn the entity to save
	 * @return the persisted entity
	 */
	@Override
	public LogisticsDdn save(LogisticsDdn logisticsDdn) {
		log.debug("Request to save LogisticsDdn : {}", logisticsDdn);
		LogisticsDdn result = logisticsDdnRepository.save(logisticsDdn);
		logisticsDdnSearchRepository.save(result);
		return result;
	}

	/**
	 * Get all the logisticsDdns.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<LogisticsDdn> findAll(Pageable pageable) {
		log.debug("Request to get all LogisticsDdns");
		return logisticsDdnRepository.findAll(pageable);
	}

	/**
	 * Get one logisticsDdn by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<LogisticsDdn> findOne(Long id) {
		log.debug("Request to get LogisticsDdn : {}", id);
		return logisticsDdnRepository.findById(id);
	}

	/**
	 * Delete the logisticsDdn by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete LogisticsDdn : {}", id);
		logisticsDdnRepository.deleteById(id);
		logisticsDdnSearchRepository.deleteById(id);
	}

	/**
	 * Search for the logisticsDdn corresponding to the query.
	 *
	 * @param query    the query of the search
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<LogisticsDdn> search(String query, Pageable pageable) {
		log.debug("Request to search for a page of LogisticsDdns for query {}", query);
		return logisticsDdnSearchRepository.search(queryStringQuery(query), pageable);
	}

	@Override
	public boolean batchImportDDN(String path) {
		ImportParams params = new ImportParams();
		List<LogisticsDdnImportDTO> logisticsDdnImportList = ExcelImportUtil.importExcel(
				new File(applicationProperties.getStaticResourcePath() + "/" + path), LogisticsDdnImportDTO.class,
				params);
		for (LogisticsDdnImportDTO logisticsDdnImportDTO : logisticsDdnImportList) {
			if (logisticsDdnImportDTO.getTitle() != null && !logisticsDdnImportDTO.getTitle().trim().equals("") && logisticsDdnImportDTO.getManagerFullname().trim() != null) {
				try {
					List<LogisticsDdn> logisticsDdnList = logisticsDdnRepository
							.findAllByTitleAndManagerFullname(logisticsDdnImportDTO.getTitle().trim(),logisticsDdnImportDTO.getManagerFullname().trim());
					if (logisticsDdnList == null || logisticsDdnList.isEmpty()) {
						LogisticsDdn logisticsDdn = new LogisticsDdn();
						logisticsDdn.setTitle(isNull(logisticsDdnImportDTO.getTitle().trim()));
						logisticsDdn.setAddress(isNull(logisticsDdnImportDTO.getAddress()));
						logisticsDdn.setCoverCity(isNull(logisticsDdnImportDTO.getCoverCity()));
						logisticsDdn.setGood(logisticsDdnImportDTO.getGoodBoolean());
						logisticsDdn.setLocationCity(isNull(logisticsDdnImportDTO.getLocationCity()));
						logisticsDdn.setManagerFullname(isNull(logisticsDdnImportDTO.getManagerFullname().trim()));
						logisticsDdn.setManagerMobilePhone(isNull(logisticsDdnImportDTO.getManagerMobilePhone()));
						logisticsDdn.setManagerPhone(isNull(logisticsDdnImportDTO.getManagerPhone()));
						logisticsDdn.setBusinessPhone(isNull(logisticsDdnImportDTO.getBusinessPhone()));
						logisticsDdn.setPic(isNull(logisticsDdnImportDTO.getPic()));
						logisticsDdn.setSpecialTransport(logisticsDdnImportDTO.getSpecialTransportBoolean());
						logisticsDdn.setThroughCity(logisticsDdnImportDTO.getThroughCity());
						logisticsDdn.setAuth(logisticsDdnImportDTO.getAuthBoolean());
						logisticsDdn.setHome(logisticsDdnImportDTO.getHomeBoolean());
						logisticsDdn.setBanner(logisticsDdnImportDTO.getBannerBoolean());
						logisticsDdn.setGoThereAndback(logisticsDdnImportDTO.getBackBoolean());
						logisticsDdn.setWww(isNull(logisticsDdnImportDTO.getWww()));
						logisticsDdn.setVip(false);
						logisticsDdn.setStatus(logisticsDdnImportDTO.getStatusEnum());
						this.save(logisticsDdn);
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		return true;
	}

	@Override
	public Page<LogisticsDdn> findAll(String startLine, String endLine, Pageable pageable) {
		
		if (endLine.trim().startsWith("北京")||endLine.trim().startsWith("天津")||endLine.trim().startsWith("重庆")||endLine.trim().startsWith("上海")) {
			endLine = endLine.substring(0, endLine.indexOf(" "));
		}else {
			endLine = endLine.substring(endLine.indexOf(" ")+1);
		}
		
		System.out.println(endLine);
		
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("status:").append(Status.ENABLE.name()).append(" & ");
		if (startLine != null) {
			queryBuffer.append("locationCity:").append(startLine).append(" & ");
		}
		if (endLine != null) {
			queryBuffer.append("throughCity:" + endLine);
		}

		QueryBuilder keywordQuery = queryStringQuery(queryBuffer.toString()).defaultOperator(Operator.AND);
		return logisticsDdnSearchRepository.search(keywordQuery, pageable);
	}

	@Override
	public List<String> findStartCitys() {
		return logisticsDdnRepository.findStartCitys();
	}

	@Override
	public List<LogisticsDdn> findAllByHome(boolean isHome) {
		return logisticsDdnRepository.findAllByHome(isHome);
	}

	@Override
	public List<LogisticsDdn> findAllByBanner(boolean isBanner) {
		return logisticsDdnRepository.findAllByBanner(isBanner);
	}
	
	private String isNull(String str) {
		if(str==null || str.equals("")) {
			return "暂无";
		}	
		return str;
	}

	@Override
	public boolean importDDNPics(String ddmTitle,String managerFullname,String path,boolean isCover) {
		List<LogisticsDdn> logisticsDdnList = logisticsDdnRepository.findAllByTitleAndManagerFullname(ddmTitle,managerFullname);
		if(logisticsDdnList!=null && !logisticsDdnList.isEmpty()) {
			LogisticsDdn logisticsDdn = logisticsDdnList.get(0);
			if(isCover) {//封面
				logisticsDdn.setPic(path);
				this.save(logisticsDdn);
			}else {
				LogisticsDdnPic logisticsDdnPic = new LogisticsDdnPic();
				logisticsDdnPic.setLogisticsDdn(logisticsDdn);
				logisticsDdnPic.setPath(path);
				logisticsDdnPic.setRemark("暂无");
				logisticsDdnPic.setStatus(Status.ENABLE);
				logisticsDdnPic.setTitle("暂无");
				logisticsDdnPicService.save(logisticsDdnPic);
			}
		}
		return true;
	}
}
