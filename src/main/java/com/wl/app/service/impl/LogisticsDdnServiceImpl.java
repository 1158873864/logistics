package com.wl.app.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.wl.app.domain.Area;
import com.wl.app.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wl.app.config.ApplicationProperties;
import com.wl.app.domain.LogisticsDdn;
import com.wl.app.domain.LogisticsDdnPic;
import com.wl.app.domain.enumeration.Status;
import com.wl.app.repository.search.LogisticsDdnSearchRepository;
import com.wl.app.service.LogisticsDdnPicService;
import com.wl.app.service.LogisticsDdnService;
import com.wl.app.service.dto.LogisticsDdnImportDTO;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

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

	private final LogisticsDdnPicRepository logisticsDdnPicRepository;

	private final LogisticsDdnWWWRepository logisticsDdnWWWRepository;

	private final UserDdnFavoritesRepository userDdnFavoritesRepository;

	private final ApplicationProperties applicationProperties;



	private final AreaRepository areaRepository;

	public LogisticsDdnServiceImpl(ApplicationProperties applicationProperties,
								   LogisticsDdnRepository logisticsDdnRepository, LogisticsDdnSearchRepository logisticsDdnSearchRepository, LogisticsDdnPicService logisticsDdnPicService, LogisticsDdnPicRepository logisticsDdnPicRepository, LogisticsDdnWWWRepository logisticsDdnWWWRepository, UserDdnFavoritesRepository userDdnFavoritesRepository, AreaRepository areaRepository) {
		this.logisticsDdnRepository = logisticsDdnRepository;
		this.logisticsDdnSearchRepository = logisticsDdnSearchRepository;
		this.applicationProperties = applicationProperties;
		this.logisticsDdnPicService = logisticsDdnPicService;
		this.logisticsDdnPicRepository = logisticsDdnPicRepository;
		this.logisticsDdnWWWRepository = logisticsDdnWWWRepository;
		this.userDdnFavoritesRepository = userDdnFavoritesRepository;
		this.areaRepository=areaRepository;
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
		logisticsDdnPicRepository.deleteByLogisticsDdnId(id);
		logisticsDdnWWWRepository.deleteByLogisticsDdnId(id);
		userDdnFavoritesRepository.deleteByDdnId(id);
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
		System.out.println(query);
		return logisticsDdnSearchRepository.search(queryStringQuery(query), pageable);
	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public Page<LogisticsDdn> search(String query, Pageable pageable) {
//		List<LogisticsDdn> logisticsDdns=logisticsDdnRepository.findAll();
//		List<LogisticsDdn> logisticsDdnList=new ArrayList<>();
//		Page<LogisticsDdn> result = new Page<LogisticsDdn>() {
//			public List<LogisticsDdn> list=new ArrayList<>();
//
//			@Override
//			public int getTotalPages() {
//				return 0;
//			}
//
//			@Override
//			public long getTotalElements() {
//				return 0;
//			}
//
//			@Override
//			public <U> Page<U> map(Function<? super LogisticsDdn, ? extends U> function) {
//				return null;
//			}
//
//			@Override
//			public int getNumber() {
//				return 0;
//			}
//
//			@Override
//			public int getSize() {
//				return 0;
//			}
//
//			@Override
//			public int getNumberOfElements() {
//				return 0;
//			}
//
//			@Override
//			public List<LogisticsDdn> getContent() {
//				return list;
//			}
//
//			public void addContent(LogisticsDdn logisticsDdn) {
//				list.add(logisticsDdn);
//			}
//			@Override
//			public boolean hasContent() {
//				return false;
//			}
//
//			@Override
//			public Sort getSort() {
//				return null;
//			}
//
//			@Override
//			public boolean isFirst() {
//				return false;
//			}
//
//			@Override
//			public boolean isLast() {
//				return false;
//			}
//
//			@Override
//			public boolean hasNext() {
//				return false;
//			}
//
//			@Override
//			public boolean hasPrevious() {
//				return false;
//			}
//
//			@Override
//			public Pageable nextPageable() {
//				return null;
//			}
//
//			@Override
//			public Pageable previousPageable() {
//				return null;
//			}
//
//			@Override
//			public Iterator<LogisticsDdn> iterator() {
//				return null;
//			}
//		};
//		for(int i=0;i<logisticsDdns.size();i++){
//			LogisticsDdn logisticsDdn=logisticsDdns.get(i);
//			if (logisticsDdn.getManagerFullname().trim().indexOf(query)!=-1||logisticsDdn.getTitle().trim().indexOf(query)!=-1||logisticsDdn.getThroughCity().trim().indexOf(query)!=-1||logisticsDdn.getCoverCity().trim().indexOf(query)!=-1||logisticsDdn.getLocationCity().trim().indexOf(query)!=-1||logisticsDdn.getAddress().trim().indexOf(query)!=-1||logisticsDdn.getManagerMobilePhone().trim().indexOf(query)!=-1||logisticsDdn.getManagerPhone().trim().indexOf(query)!=-1||logisticsDdn.getBusinessPhone().trim().indexOf(query)!=-1){
//				logisticsDdnList.add(logisticsDdn);
//			}
//
//		}
//		int pageNum=pageable.getPageNumber();
//		int pageSize=pageable.getPageSize();
//		int max=(logisticsDdnList.size()<((pageNum+2)*pageSize))?logisticsDdnList.size():(pageNum+2)*pageSize;
//		for(int i=(pageNum+1)*pageSize;i<max;i++){
//			result.getContent().add(logisticsDdnList.get(i));
//		}
//		return result;
//	}

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
						String[] pics=logisticsDdnImportDTO.getPic().split(",");
						if(pics.length>0) {
							logisticsDdn.setPic("logistics-ddn-pics/"+pics[0]);
						}

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
						for(int i=0;i<pics.length;i++){
							LogisticsDdnPic logisticsDdnPic = new LogisticsDdnPic();
							logisticsDdnPic.setLogisticsDdn(logisticsDdn);
							logisticsDdnPic.setPath("logistics-ddn-pics/"+pics[i]);
							logisticsDdnPic.setRemark("暂无");
							logisticsDdnPic.setStatus(Status.ENABLE);
							logisticsDdnPic.setTitle("暂无");
							logisticsDdnPicService.save(logisticsDdnPic);
						}
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
	public List<LogisticsDdn> findAll(String startLine, String endLine, Pageable pageable) {
		List<LogisticsDdn> logisticsDdnList=new ArrayList<>();
		List<LogisticsDdn> sortedList=new ArrayList<>();
		String province=findProvinceByCity(endLine);
		List<LogisticsDdn> logisticsDdns=logisticsDdnRepository.findAll();
		List<LogisticsDdn> result=new ArrayList<>();
		endLine=endLine.trim();
		if (endLine.trim().startsWith("北京")||endLine.trim().startsWith("天津")||endLine.trim().startsWith("重庆")||endLine.trim().startsWith("上海")) {
			endLine=endLine.substring(0,2);
		}
		for(int i=0;i<logisticsDdns.size();i++){
			LogisticsDdn logisticsDdn=logisticsDdns.get(i);
			if(logisticsDdn.getLocationCity().equals(startLine)&&(logisticsDdn.getCoverCity().indexOf(endLine)!=(-1)||logisticsDdn.getThroughCity().indexOf(endLine)!=(-1)||logisticsDdn.getCoverCity().indexOf(province)!=(-1)||logisticsDdn.getCoverCity().indexOf(province)!=(-1))){
				logisticsDdnList.add(logisticsDdn);
			}
		}
		for(int i=0;i<logisticsDdnList.size();i++){
			if(logisticsDdnList.get(i).getThroughCity().indexOf(endLine)!=(-1)){
				sortedList.add(logisticsDdnList.get(i));
			}
		}
		for(int i=0;i<logisticsDdnList.size();i++){
			if(logisticsDdnList.get(i).getThroughCity().indexOf(endLine)==(-1)){
				sortedList.add(logisticsDdnList.get(i));
			}
		}
		int pageNum=pageable.getPageNumber();
		int pageSize=pageable.getPageSize();
		int max=(sortedList.size()<(pageNum*pageSize))?sortedList.size():pageNum*pageSize;
		for(int i=(pageNum-1)*pageSize;i<max;i++){
			result.add(sortedList.get(i));
		}
		return result;
	}

	public String findProvinceByCity(String city){
		String result="";
		List<Area> areaList = areaRepository.findByParentId(0);
		for(int i=0;i<areaList.size();i++){
			List<Area> cityList = areaRepository.findByParentId(areaList.get(i).getId().intValue());
			for(int j=0;j<cityList.size();j++){
				if(cityList.get(j).getCity().equals(city)){
					return areaList.get(i).getCity();
				}
			}
		}
		return result;
	}

//	@Override
//	public Page<LogisticsDdn> findAll(String startLine, String endLine, Pageable pageable) {
//
//		if (endLine.trim().startsWith("北京")||endLine.trim().startsWith("天津")||endLine.trim().startsWith("重庆")||endLine.trim().startsWith("上海")) {
//			endLine = endLine.substring(0, endLine.indexOf(" "));
//		}else {
//			endLine = endLine.substring(endLine.indexOf(" ")+1);
//		}
//
//		System.out.println(endLine);
//
//		StringBuffer queryBuffer = new StringBuffer();
//		queryBuffer.append("status:").append(Status.ENABLE.name()).append(" & ");
//		if (startLine != null) {
//			queryBuffer.append("locationCity:").append(startLine).append(" & ");
//		}
//		if (endLine != null) {
//			queryBuffer.append("throughCity:" + endLine);
//		}
//
//		QueryBuilder keywordQuery = queryStringQuery(queryBuffer.toString()).defaultOperator(Operator.AND);
//		return logisticsDdnSearchRepository.search(keywordQuery, pageable);
//	}

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
