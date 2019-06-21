package com.wl.app.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.wl.app.service.util.AESDecodeUtils;
import net.sf.json.JSONObject;
import com.wl.app.domain.Area;
import com.wl.app.repository.*;
import com.wl.app.service.dto.CannotGetOpenIdAndSessionKeyException;
import com.wl.app.service.dto.OpenIdAndSessionKeyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
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
import org.springframework.web.client.RestTemplate;

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

    public static List<LogisticsDdn> sortedList;

	private final AreaRepository areaRepository;

	private String wechatUrl="https://api.weixin.qq.com/sns/jscode2session?appid=";

	private String appId="wxc0571072a7d63176";

	private String appSecret="dbd1019cf3075c7d09a25d33760b1a00";

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
//				return false;
//			@Override
//			public boolean hasContent() {
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
		List<LogisticsDdn> result = new ArrayList<>();
		if(pageable.getPageNumber()==1) {

            sortedList=new ArrayList<>();
			List<LogisticsDdn> logisticsDdnList = new ArrayList<>();
			List<LogisticsDdn> coverList = new ArrayList<>();
			List<LogisticsDdn> throughList = new ArrayList<>();
			List<LogisticsDdn> throughTownList = new ArrayList<>();
			String province = "";
			province = findProvinceByCity(endLine);
			String city="";
			List<LogisticsDdn> logisticsDdns = logisticsDdnRepository.findAll();

			System.out.println("province："+province);
			if(endLine.equals("扬州-宝应县")){
                city="扬州";
                province = findProvinceByCity(city);
			}
			else if(endLine.equals("苏州-张家港市")){
                city="苏州";
                province = findProvinceByCity(city);
			}
			else if(endLine.equals("苏州-连云港市")){
                city="苏州";
                province = findProvinceByCity(city);
			}
			else if(endLine.equals("苏州-昆山市")){
                city="苏州";
                province = findProvinceByCity(city);
			}
			else if(endLine.equals("苏州-太仓市")){
				city="苏州";
				province = findProvinceByCity(city);
			}
			else if(endLine.equals("苏州-常熟市")){
                city="苏州";
                province = findProvinceByCity(city);
			}
			else if(endLine.equals("无锡-张阴市")){
                city="无锡";
                province = findProvinceByCity(city);
			}
			else if(endLine.equals("北京")&&endLine.equals("天津")&&endLine.equals("重庆")&&endLine.equals("上海")){
				province="";
			}
			else{
			    city="******";
                province = findProvinceByCity(endLine);
            }

			if (province.equals("")) {
				for (int i = 0; i < logisticsDdns.size(); i++) {
					LogisticsDdn logisticsDdn = logisticsDdns.get(i);
					if (logisticsDdn.getLocationCity().equals(startLine) && (logisticsDdn.getCoverCity().indexOf(endLine) != (-1) || logisticsDdn.getThroughCity().indexOf(endLine) != (-1))) {
						logisticsDdnList.add(logisticsDdn);
					}
				}

				for (int i = 0; i < logisticsDdnList.size(); i++) {
					if (logisticsDdnList.get(i).getThroughCity().indexOf(endLine) != (-1)) {
						throughList.add(logisticsDdnList.get(i));
					}
				}
				for (int i = 0; i < logisticsDdnList.size(); i++) {
					if (logisticsDdnList.get(i).getCoverCity().indexOf(endLine) != (-1) && logisticsDdnList.get(i).getThroughCity().indexOf(endLine) == (-1)) {
						coverList.add(logisticsDdnList.get(i));
					}
				}
			} else {
				System.out.println(endLine);
				for (int i = 0; i < logisticsDdns.size(); i++) {
					LogisticsDdn logisticsDdn = logisticsDdns.get(i);
					if (logisticsDdn.getLocationCity().equals(startLine) && (logisticsDdn.getCoverCity().indexOf(endLine) != (-1) || logisticsDdn.getThroughCity().indexOf(endLine) != (-1) || logisticsDdn.getThroughCity().indexOf(province) != (-1) || logisticsDdn.getCoverCity().indexOf(province) != (-1)|| logisticsDdn.getThroughCity().indexOf(city) != (-1)|| logisticsDdn.getThroughCity().indexOf(city) != (-1))) {
						logisticsDdnList.add(logisticsDdn);
					}
				}

				for (int i = 0; i < logisticsDdnList.size(); i++) {
					if (logisticsDdnList.get(i).getThroughCity().indexOf(endLine) != (-1) ) {
						throughTownList.add(logisticsDdnList.get(i));
					}
				}
				for (int i = 0; i < logisticsDdnList.size(); i++) {
					if ((logisticsDdnList.get(i).getThroughCity().indexOf(province) != (-1)||logisticsDdnList.get(i).getThroughCity().indexOf(city) != (-1))&& logisticsDdnList.get(i).getThroughCity().indexOf(endLine) == (-1)) {
						throughList.add(logisticsDdnList.get(i));
					}
				}
				for (int i = 0; i < logisticsDdnList.size(); i++) {
					if ((logisticsDdnList.get(i).getCoverCity().indexOf(endLine) != (-1) || logisticsDdnList.get(i).getCoverCity().indexOf(province) != (-1)|| logisticsDdnList.get(i).getCoverCity().indexOf(city) != (-1)) && logisticsDdnList.get(i).getThroughCity().indexOf(endLine) == (-1) && logisticsDdnList.get(i).getThroughCity().indexOf(province) == (-1)&& logisticsDdnList.get(i).getThroughCity().indexOf(city) == (-1)) {
						coverList.add(logisticsDdnList.get(i));
					}
				}
			}

			List<LogisticsDdn> temp01 = new ArrayList<>();
			List<LogisticsDdn> temp02 = new ArrayList<>();
			List<LogisticsDdn> temp03 = new ArrayList<>();
			List<LogisticsDdn> temp04 = new ArrayList<>();
			List<LogisticsDdn> temp1 = new ArrayList<>();
			List<LogisticsDdn> temp2 = new ArrayList<>();
			List<LogisticsDdn> temp3 = new ArrayList<>();
			List<LogisticsDdn> temp4 = new ArrayList<>();
			List<LogisticsDdn> temp5 = new ArrayList<>();
			List<LogisticsDdn> temp6 = new ArrayList<>();
			List<LogisticsDdn> temp7 = new ArrayList<>();
			List<LogisticsDdn> temp8 = new ArrayList<>();

			for (int i = 0; i < throughTownList.size(); i++) {
				if (throughTownList.get(i).isVip() && throughTownList.get(i).isGood()) {
					temp01.add(throughTownList.get(i));
				}
			}
			Collections.shuffle(temp01);
			for (int i = 0; i < throughTownList.size(); i++) {
				if (throughTownList.get(i).isVip() && !throughTownList.get(i).isGood()) {
					temp02.add(throughTownList.get(i));
				}
			}
			Collections.shuffle(temp02);
			for (int i = 0; i < throughTownList.size(); i++) {
				if (!throughTownList.get(i).isVip() && throughTownList.get(i).isGood()) {
					temp03.add(throughTownList.get(i));
				}
			}
			Collections.shuffle(temp03);
			for (int i = 0; i < throughTownList.size(); i++) {
				if (!throughTownList.get(i).isVip() && !throughTownList.get(i).isGood()) {
					temp04.add(throughTownList.get(i));
				}
			}
			Collections.shuffle(temp04);

			for (int i = 0; i < throughList.size(); i++) {
				if (throughList.get(i).isVip() && throughList.get(i).isGood()) {
					temp1.add(throughList.get(i));
				}
			}
			Collections.shuffle(temp1);
			for (int i = 0; i < throughList.size(); i++) {
				if (throughList.get(i).isVip() && !throughList.get(i).isGood()) {
					temp2.add(throughList.get(i));
				}
			}
			Collections.shuffle(temp2);
			for (int i = 0; i < throughList.size(); i++) {
				if (!throughList.get(i).isVip() && throughList.get(i).isGood()) {
					temp3.add(throughList.get(i));
				}
			}
			Collections.shuffle(temp3);
			for (int i = 0; i < throughList.size(); i++) {
				if (!throughList.get(i).isVip() && !throughList.get(i).isGood()) {
					temp4.add(throughList.get(i));
				}
			}
			Collections.shuffle(temp4);


			for (int i = 0; i < coverList.size(); i++) {
				if (coverList.get(i).isVip() && coverList.get(i).isGood()) {
					temp5.add(coverList.get(i));
				}
			}
			Collections.shuffle(temp5);

			for (int i = 0; i < coverList.size(); i++) {
				if (!coverList.get(i).isVip() && coverList.get(i).isGood()) {
					temp6.add(coverList.get(i));
				}
			}
			Collections.shuffle(temp6);

			for (int i = 0; i < coverList.size(); i++) {
				if (coverList.get(i).isVip() && !coverList.get(i).isGood()) {
					temp7.add(coverList.get(i));
				}
			}
			Collections.shuffle(temp7);

			for (int i = 0; i < coverList.size(); i++) {
				if (!coverList.get(i).isVip() && !coverList.get(i).isGood()) {
					temp8.add(coverList.get(i));
				}
			}
			Collections.shuffle(temp8);

			for (LogisticsDdn logisticsDdn : temp01) {
				sortedList.add(logisticsDdn);
			}
			for (LogisticsDdn logisticsDdn : temp02) {
				sortedList.add(logisticsDdn);
			}
			for (LogisticsDdn logisticsDdn : temp03) {
				sortedList.add(logisticsDdn);
			}
			for (LogisticsDdn logisticsDdn : temp04) {
				sortedList.add(logisticsDdn);
			}

			for (LogisticsDdn logisticsDdn : temp1) {
				sortedList.add(logisticsDdn);
			}
			for (LogisticsDdn logisticsDdn : temp2) {
				sortedList.add(logisticsDdn);
			}
			for (LogisticsDdn logisticsDdn : temp3) {
				sortedList.add(logisticsDdn);
			}
			for (LogisticsDdn logisticsDdn : temp4) {
				sortedList.add(logisticsDdn);
			}
			for (LogisticsDdn logisticsDdn : temp5) {
				sortedList.add(logisticsDdn);
			}
			for (LogisticsDdn logisticsDdn : temp6) {
				sortedList.add(logisticsDdn);
			}
			for (LogisticsDdn logisticsDdn : temp7) {
				sortedList.add(logisticsDdn);
			}
			for (LogisticsDdn logisticsDdn : temp8) {
				sortedList.add(logisticsDdn);
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


	@Override
	public OpenIdAndSessionKeyResponse getOpenIdAndSessionKey(String jsCode) throws CannotGetOpenIdAndSessionKeyException {
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>("", headers);
		ResponseEntity<String> response = client.exchange(wechatUrl + appId + "&secret=" + appSecret + "&js_code=" + jsCode + "&grant_type=authorization_code", HttpMethod.GET, entity, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("jsCode = [" + jsCode + "]");
			System.out.println("hhhhhhh" + (String) JSONObject.fromObject(response.getBody()).get("openid"));
			System.out.println(response);
			String openid = (String) JSONObject.fromObject(response.getBody()).get("openid");
//            User user=null;
//			try {
//				user = userDataService.getUserByOpenid(openid);
//			} catch (NotExistException e) {
//				e.printStackTrace();
//			}

			//JwtUser jwtUser = (JwtUser) jwtUserDetailsService.loadUserByUsername(openid);
			String token = "";
			//token = jwtService.generateToken(jwtUser, EXPIRATION);

			return new OpenIdAndSessionKeyResponse(openid, (String) JSONObject.fromObject(response.getBody()).get("session_key"), token);
		} else {
			throw new CannotGetOpenIdAndSessionKeyException();
		}
	}

	@Override
	public String decrypt(String sessionKey,String encryptedData,String iv) throws Exception {
        return AESDecodeUtils.decrypt(sessionKey,encryptedData,iv);
	}

	//这是进行判断的
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

	@Override
	public LogisticsDdn findAllByPhoneNumber(String PhoneNumber){
		return logisticsDdnRepository.findLogisticsDdnByManagerMobilePhone(PhoneNumber);

	}

	@Override
	public List<LogisticsDdn> findAllByspecialTransport(boolean specialTransport) {
		System.out.println("specialTransport====="+specialTransport+"==========");
		return logisticsDdnRepository.findAllBySpecialTransport(specialTransport);
	}

	@Override
	public List<LogisticsDdn> findAllBygood(boolean isgood) {
		System.out.println("specialTransport====="+isgood+"==========");
		return	logisticsDdnRepository.findAllByGood(isgood);
	}

	@Override
	public Page<LogisticsDdn> find(String query, Pageable pageable){
		List<LogisticsDdn> logisticsDdns=logisticsDdnRepository.findAll();
		List<LogisticsDdn> sortedList=new ArrayList<>();
		for(int i=0;i<logisticsDdns.size();i++){
			LogisticsDdn logisticsDdn=logisticsDdns.get(i);
			if(logisticsDdn.getThroughCity().indexOf(query)!=(-1)||logisticsDdn.getCoverCity().indexOf(query)!=(-1)||logisticsDdn.getBusinessPhone().indexOf(query)!=(-1)||logisticsDdn.getManagerPhone().indexOf(query)!=(-1)||logisticsDdn.getManagerMobilePhone().indexOf(query)!=(-1)||logisticsDdn.getAddress().indexOf(query)!=(-1)||logisticsDdn.getLocationCity().indexOf(query)!=(-1)||logisticsDdn.getTitle().indexOf(query)!=(-1)||logisticsDdn.getManagerFullname().indexOf(query)!=(-1)||String.valueOf(logisticsDdn.getId()).indexOf(query)!=(-1)){
				sortedList.add(logisticsDdn);
			}
		}
		return listConvertToPage(sortedList,pageable);

	}


	public <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
		int start = (int)pageable.getOffset();
		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
		return new PageImpl<T>(list.subList(start, end), pageable, list.size());
	}
}
