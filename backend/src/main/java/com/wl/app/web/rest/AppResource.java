package com.wl.app.web.rest;

import java.io.File;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.wl.app.domain.*;
import com.wl.app.domain.enumeration.GoodsSourceProperty;
import com.wl.app.repository.LogisticsDdnRepository;
import com.wl.app.service.*;
import com.wl.app.service.dto.*;
import com.wl.app.web.rest.errors.*;
import com.wl.app.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.enumeration.Status;
import com.wl.app.module.aliyun.Sms;
import com.wl.app.repository.AreaRepository;
import com.wl.app.repository.UserRepository;
import com.wl.app.web.rest.vm.ManagedUserVM;
import com.wl.app.web.rest.vm.RUserVM;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.data.domain.PageRequest.of;


/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api/app-static")
public class AppResource {

	private final UserRepository userRepository;
	private final UserService userService;
    private final UserInfoService userInfoService;
	private final LogisticsDdnService logisticsDdnService;
	private final LogisticsDdnWWWService logisticsDdnWWWService;
	private final LogisticsDdnPicService logisticsDdnPicService;
	private final AreaRepository areaRepository;
	private final UserDdnFavoritesService userDdnFavoritesService;
	private final GoodsSourceService goodsSourceService;
	private final TopicService topicService;
	private final TopicCommentService topicCommentService;
	private final TopicFabulousService topicFabulousService;
    private final SysRecruitmentInformationService sysRecruitmentInformationService;
	private final TopicForwardService topicForwardService;
	private final GoodsExchangeService goodsExchangeService;
	private final MemberService memberService;
	private final PreferentialActivitiesService preferentialActivitiesService;
	private final GoodsService goodsService;
	private final IntegralChangeRecordService integralChangeRecordService;
	private final IntegralRuleService integralRuleService;
    private final StorageService storageService;
    private final AreaService areaService;
	private final VersionService versionService;
	private final StatisticsService statisticsService;
	private final IOSQualificationBlService iosQualificationBlService;
    private  final DdnUpdateService ddnUpdateService;
    private final BrowseService browseService;
    private final LogisticsDdnRepository logisticsDdnRepository;
	public AppResource(UserRepository userRepository, UserService userService, LogisticsDdnService logisticsDdnService,
					   LogisticsDdnWWWService logisticsDdnWWWService, LogisticsDdnPicService logisticsDdnPicService, AreaRepository areaRepository,
					   UserInfoService userInfoService, UserDdnFavoritesService userDdnFavoritesService, GoodsSourceService goodsSourceService,
					   TopicService topicService, TopicCommentService topicCommentService, TopicFabulousService topicFabulousService, SysRecruitmentInformationService sysRecruitmentInformationService
			, TopicForwardService topicForwardService, GoodsExchangeService goodsExchangeService, MemberService memberService, PreferentialActivitiesService preferentialActivitiesService, GoodsService goodsService, IntegralChangeRecordService integralChangeRecordService, IntegralRuleService integralRuleService, StorageService storageService, AreaService areaService, VersionService versionService, StatisticsService statisticsService, IOSQualificationBlService iosQualificationBlService, DdnUpdateService ddnUpdateService, BrowseService browseService, LogisticsDdnRepository logisticsDdnRepository) {
		super();
		this.userRepository = userRepository;
		this.userService = userService;
		this.logisticsDdnService = logisticsDdnService;
		this.logisticsDdnWWWService = logisticsDdnWWWService;
		this.logisticsDdnPicService = logisticsDdnPicService;
		this.areaRepository = areaRepository;
        this.userInfoService =userInfoService;
        this.userDdnFavoritesService=userDdnFavoritesService;
        this.goodsSourceService=goodsSourceService;
		this.topicService=topicService;
		this.topicCommentService=topicCommentService;
		this.topicFabulousService=topicFabulousService;
        this.sysRecruitmentInformationService=sysRecruitmentInformationService;
		this.topicForwardService=topicForwardService;
		this.goodsExchangeService =goodsExchangeService;
		this.memberService=memberService;
		this.preferentialActivitiesService=preferentialActivitiesService;
		this.goodsService=goodsService;
		this.integralChangeRecordService=integralChangeRecordService;
		this.integralRuleService=integralRuleService;
		this.storageService = storageService;
		this.areaService = areaService;
		this.versionService = versionService;
		this.statisticsService = statisticsService;
		this.iosQualificationBlService = iosQualificationBlService;
		this.ddnUpdateService = ddnUpdateService;
		this.browseService = browseService;
		this.logisticsDdnRepository = logisticsDdnRepository;
	}

	/**
	 * 始发城市列表
	 *
	 * @return
	 */
	@GetMapping("/start-citys")
	@Timed
	public ResponseEntity<Result> getStartCitys() {
		List<StartCityDTO> result = new ArrayList<>();
		List<String> startCitys = logisticsDdnService.findStartCitys();
		if(startCitys!=null) {
			for(String city : startCitys) {
				result.add(new StartCityDTO(city));
			}
		}
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	/**
	 * 最新查找 和 收藏 的专线 以及banner
	 *
	 * @return
	 */
	@GetMapping("/")
	@Timed
	public ResponseEntity<Result> getLatestSearchAndFavoritesDdnAndBanner() {

		SearchAndFavoritesDdnAndBannerDTO searchAndFavoritesDdnAndBannerDTO = new SearchAndFavoritesDdnAndBannerDTO();

		List<DdnBannerDTO> ddnBanners = new ArrayList<>();

		List<LogisticsDdn> banners = logisticsDdnService.findAllByBanner(true);

		if(banners!=null && !banners.isEmpty()) {
			for(LogisticsDdn logisticsDdn : banners) {
				ddnBanners.add(new DdnBannerDTO(logisticsDdn.getId(),logisticsDdn.getPic()));
			}
		}

		searchAndFavoritesDdnAndBannerDTO.setDdnBanners(ddnBanners);
		List<SearchAndFavoritesDdnDTO> searchAndFavoritesDdns = new ArrayList<>();
		List<LogisticsDdn> homes = logisticsDdnService.findAllByHome(true);

		if(homes!=null && !homes.isEmpty()) {
			for(LogisticsDdn logisticsDdn : homes) {
				searchAndFavoritesDdns.add(new SearchAndFavoritesDdnDTO(logisticsDdn.getId(), logisticsDdn.getTitle(),logisticsDdn.getManagerMobilePhone() ,logisticsDdn.getLocationCity(), logisticsDdn.getThroughCity(), logisticsDdn.getManagerFullname(), logisticsDdn.isVip()));
			}
		}
		searchAndFavoritesDdnAndBannerDTO.setSearchAndFavoritesDdns(searchAndFavoritesDdns);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(searchAndFavoritesDdnAndBannerDTO), HttpStatus.OK);
	}

	/**
	 * 获取专线详情
	 *
	 * @param id 专线ID
	 * @return
	 */
	@GetMapping("/ddn-view/{id}")
	@Timed
	public ResponseEntity<Result> getDDNView(@PathVariable Long id) {
		DdnDTO result = new DdnDTO();
		Optional<LogisticsDdn> logisticsDdnOptional = logisticsDdnService.findOne(id);
		if(logisticsDdnOptional.isPresent()) {
			result.setId(id);
			result.setInfo(logisticsDdnOptional.get());
			result.setBranchs(logisticsDdnWWWService.findAllByLogisticsDdnIdAndStatus(id, Status.ENABLE));
			result.setPics(logisticsDdnPicService.findAllByLogisticsDdnIdAndStatus(id, Status.ENABLE));
		}
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

//	/**
//	 * 获取banner列表信息
//	 * @return
//	 */
//	@GetMapping("/ddn-banners")
//	@Timed
//	public ResponseEntity<Result> getHomeBanners() {
//		List<DdnBannerDTO> banners = new ArrayList<>();
//		banners.add(new DdnBannerDTO(1l,"http://pic19.nipic.com/20120210/7827303_221233267358_2.jpg"));
//		return new ResponseEntity<>(ResultGenerator.genSuccessResult(banners), HttpStatus.OK);
//	}
	/**
	 * 所有活动
	 * @return
	 */
	@GetMapping("/all-activities")
	@Timed
	public ResponseEntity<Result> getAllActivities() {
		List<ListActivityDTO> listActivitys = new ArrayList<>();
		listActivitys.add(new ListActivityDTO(1l, "快邀好友，赚18元红包", "http://pic19.nipic.com/20120210/7827303_221233267358_2.jpg"));
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(listActivitys), HttpStatus.OK);
	}

	/**
	 * 活动详情
	 * @return
	 */
	@GetMapping("/activity/view/{id}")
	@Timed
	public ResponseEntity<Result> getActivity(@PathVariable Long id) {
		PreferentialActivitiesDTO result = new PreferentialActivitiesDTO();
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	/**
	 * 查询物流专线
	 * @param startLine
	 * @param endLine
	 * @param pageable
	 * @return
	 */
	@GetMapping("/search-ddns")
    @Timed
    public ResponseEntity<Result> getAllLogisticsDdns(@RequestParam(value = "startLine")String startLine,
    		@RequestParam(value = "endLine")String endLine,Pageable pageable) {
        List<LogisticsDdn> page = logisticsDdnService.findAll(startLine,endLine,pageable);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(page), HttpStatus.OK);
    }

	/**
	 * 录入好专线
	 * @param logisticsDdn
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/addlogistics-ddns")
	@Timed
	public ResponseEntity<Result> createLogisticsDdn(@Valid @RequestBody LogisticsDdn logisticsDdn) throws URISyntaxException {
		if (logisticsDdn.getId() != null) {
			throw new BadRequestAlertException("A new logisticsDdn cannot already have an ID", "logisticsDdn", "idexists");
		}
		LogisticsDdn result = logisticsDdnService.save(logisticsDdn);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	/**
	 * 特种运输
	 * @param
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/Earch_logisticsddns")
	@Timed
	public ResponseEntity<Result> EarchLogisticsDdnByGoodsLogistics() throws URISyntaxException {
		System.out.println("===============000000000");
		/*if (specialTransport == null) {
			throw new BadRequestAlertException("A new logisticsDdn cannot already have an ID", "specialTransport", "idexists");
		}*/
	    List<LogisticsDdn> result = logisticsDdnService.findAllByspecialTransport(true);

		if (result==null) {
			Map<String,Object> re=new HashMap<>();
			re.put("result","暂时没有特种运输");
		}
		Map<String,Object> re=new HashMap<>();
		re.put("result",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 好专线
	 * @param
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/Earch_Goodlogisticsone")
	@Timed
	public ResponseEntity<Result> EarchLogisticsDdnByGoods() throws URISyntaxException {
		System.out.println("===============000000000");
		/*if (good == null) {
			throw new BadRequestAlertException("A new logisticsDdn cannot already have an ID", "specialTransport", "idexists");
		}*/
		List<LogisticsDdn> result = logisticsDdnService.findAllBygood(true);
		if(result==null){
			Map<String,Object> re=new HashMap<>();
			re.put("msg","暂未开通好专线");
		}
		Map<String,Object> re=new HashMap<>();
		re.put("result",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}


	@GetMapping("/area/province")
    @Timed
    public ResponseEntity<Result> getAreaProvince() {
        List<Area> list = areaRepository.findByParentId(0);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(list), HttpStatus.OK);
    }

	@GetMapping("/area/city-and-county")
    @Timed
    public ResponseEntity<Result> getCityAndCounty(@RequestParam(value = "parentId")Integer parentId) {
        List<Area> list = areaRepository.findByParentId(parentId);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(list), HttpStatus.OK);
    }

	/**
	 * 发送短信验证码
	 * @param mobilePhone
	 * @return
	 */
	@PostMapping("/msg/send-vcode")
	@Timed
	public ResponseEntity<Result> sendVCode(@RequestParam(required = true) String mobilePhone) {
		Map<String, Object> result = new HashMap<>();
		String code = Sms.me().sendVerificationCode(mobilePhone);
		if (code.equals(Sms.SEND_SUCCESS)) {
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.FAIL,"短信验证码发送失败!"), HttpStatus.OK);
		}
	}

	@PostMapping("/msg/send-vcode2")
	@Timed
	public ResponseEntity<Result> sendVCode2(@RequestParam(required = true) String mobilePhone) {
		Map<String, Object> result = new HashMap<>();
		Optional<LogisticsDdn> optionalUserInfo=logisticsDdnRepository.findByManagerMobilePhone(mobilePhone);
		if(optionalUserInfo.isPresent()){
			String code = Sms.me().sendVerificationCode(mobilePhone);
			if (code.equals(Sms.SEND_SUCCESS)) {
				return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.FAIL,"短信验证码发送失败!"), HttpStatus.OK);
			}
		}
		else {
			return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.FAIL,"该号码无对应专线，无法获取验证码认领专线!"), HttpStatus.OK);
		}

	}

    @PostMapping("/register")
    @Timed
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Result> registerAccount(@Valid @RequestBody RUserVM userVM) {

		Map<String, Object> result = new HashMap<>();
		Optional<User> existingUser = userRepository.findOneByLogin(userVM.getMobilePhone());
		if (existingUser.isPresent()) {
			result.put("result", false);
			result.put("msg", "手机号码已经被注册使用!");
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
		}
//        if (!checkPasswordLength(userVM.getPassword())) {
//        	result.put("result", false);
//			result.put("msg", "密码不合法!");
//			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
//        }
//
        boolean isSuccess = Sms.me().checkVerificationCode(userVM.getMobilePhone(), userVM.getVcode());
		if (isSuccess) {

			result.put("result", true);
			result.put("msg", "注册成功");
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
		}else {
			result.put("result", false);
			result.put("msg", "验证码超时或者错误，请重试！");
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
		}
    }

    @PostMapping("/login")
	@Timed
	public ResponseEntity<Result> login(@RequestParam(required = true) String mobilePhone,@RequestParam(required = true) String vcode){
		Map<String, Object> result = new HashMap<>();
//		Optional<User> existingUser = userRepository.findOneByLogin(userVM.getMobilePhone());
//		if (!existingUser.isPresent()) {
//			result.put("result", false);
//			result.put("msg", "手机号码未注册使用!");
//			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
//		}
		System.out.println("login");
		boolean isSuccess = Sms.me().checkVerificationCode(mobilePhone, vcode);
		if (isSuccess) {
            UserInfo userInfo=new UserInfo();
			Optional<UserInfo> optionalUserInfo= userInfoService.findOneByMobilePhone(mobilePhone);
            if(optionalUserInfo.isPresent()) {
                userInfo=optionalUserInfo.get();
				userInfo.setLastLoginedDate(Instant.now());
				userInfoService.save(userInfo);
            }else {
                RUserVM userVM=new RUserVM();
                userVM.setMobilePhone(mobilePhone);
                userVM.setVcode(vcode);
				userInfo=userService.registerUser(userVM,"123456");
            }

            result.put("userInfo",userInfo);
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.FAIL,"验证码超时或者错误，请重试！"), HttpStatus.OK);
		}
	}

    /**
	 * 发送业务短信
	 *
	 * @param mobilePhone
	 * @return
	 */
	@PostMapping("/msg/send-contact")
	@Timed
	public ResponseEntity<Result> sendContact(@RequestParam(required = true) String mobilePhone,@RequestParam(required = true) String userMobilePhone) {
		Map<String, Object> result = new HashMap<>();
		// 校验手机号是否已经注册
		//Optional<User> existingUser = userRepository.findOneByLogin(mobilePhone);
		String code = Sms.me().sendContact(mobilePhone,userMobilePhone);
		if (code.equals(Sms.SEND_SUCCESS)) {
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
		}
		result.put("result", false);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	/**
	 * 小程序发送业务短信
	 *
	 * @param mobilePhone
	 * @return
	 */
	@PostMapping("/msg/send-contact2")
	@Timed
	public ResponseEntity<Result> sendContact2(@RequestParam(required = true) String mobilePhone,@RequestParam(required = true) String userMobilePhone) {
		Map<String, Object> result = new HashMap<>();
		// 校验手机号是否已经注册
		//Optional<User> existingUser = userRepository.findOneByLogin(mobilePhone);
		String code = Sms.me().sendContact2(mobilePhone,userMobilePhone);
		if (code.equals(Sms.SEND_SUCCESS)) {
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
		}
		result.put("result", false);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}


	@PostMapping("/decrypt")
	@Timed
	public ResponseEntity<Result> decrypt(@RequestParam(required = true) String sessionKey,@RequestParam(required = true) String encryptedData,@RequestParam(required = true) String iv) throws Exception {
		Map<String, Object> result = new HashMap<>();
		String re=logisticsDdnService.decrypt(sessionKey,encryptedData,iv);
		result.put("result", re);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }

	@PostMapping("/user-ddn-favorites")
	@Timed
	public ResponseEntity<Result> addUserDdnFavorites(@RequestParam(required = true) long user_id,@RequestParam(required = true) long ddn_id) throws URISyntaxException {
		Map<String, Object> re = new HashMap<>();
		UserDdnFavorites userDdnFavorites=new UserDdnFavorites();
		LogisticsDdn logisticsDdn=null;
		UserInfo userInfo=null;
		Optional<LogisticsDdn> optionalLogisticsDdn=logisticsDdnService.findOne(ddn_id);
		if(optionalLogisticsDdn.isPresent()){
			logisticsDdn=optionalLogisticsDdn.get();
		}
		else{
			return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.FAIL,"专线不存在"), HttpStatus.OK);
		}
		Optional<UserInfo> optionalUserInfoService=userInfoService.findOne(user_id);
		if(optionalUserInfoService.isPresent()){
			userInfo=optionalUserInfoService.get();
		}
		else{

			return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.FAIL,"用户不存在"), HttpStatus.OK);
		}
		userDdnFavorites.setDdn(logisticsDdn);
		userDdnFavorites.setUserInfo(userInfo);
		userDdnFavoritesService.save(userDdnFavorites);
        re.put("userDdnFavorites",userDdnFavorites);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	@GetMapping("/user-ddn-favorites/{user_id}")
	@Timed
	public ResponseEntity<Result> getUserDdnFavorites(@PathVariable Long user_id) {
		List<UserDdnFavorites> userDdnFavorites = userDdnFavoritesService.findByUserId(user_id);
		Map<String, Object> re = new HashMap<>();
		re.put("list",userDdnFavorites);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	@GetMapping("/user-ddn-favorites-one/{id}")
	@Timed
	public ResponseEntity<Result> getOneDdnFavorites(@PathVariable Long id) {
		Optional<UserDdnFavorites> userDdnFavorites = userDdnFavoritesService.findOne(id);

		Map<String, Object> re = new HashMap<>();
		re.put("userDdnFavorites",userDdnFavorites.get());
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	@DeleteMapping("/user-ddn-favorites/{id}")
	@Timed
	public ResponseEntity<Result> deleteUserDdnFavorites(@PathVariable Long id) {
		userDdnFavoritesService.delete(id);
		Map<String, Object> re = new HashMap<>();
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}


	@GetMapping("/isFavorite")
	@Timed
	public ResponseEntity<Result> isFavorite(@RequestParam(required = true) long user_id,@RequestParam(required = true) long ddn_id) throws URISyntaxException {
		Map<String, Object> re = new HashMap<>();
		boolean is=false;
		List<UserDdnFavorites> list=userDdnFavoritesService.findByUserId(user_id);
		for(int i=0;i<list.size();i++){
			if(list.get(i).getDdn().getId()==ddn_id){
				is=true;
				break;
			}
		}
		re.put("result",is);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 获取货源列表
	 * @return
	 */
	@GetMapping("/allSource")
	@Timed
	public ResponseEntity<Result> allSource(Pageable pageable) {
		Page<GoodsSource> page = goodsSourceService.findAll(pageable);
		Map<String, Object> re = new HashMap<>();
        re.put("goodsSourcePage",page.getContent());
        Map<String,Object> sortedMap = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        Iterator<String> item = re.keySet().iterator();
        while(item.hasNext()){
            list.add(item.next());
        }
        Collections.sort(list);
        Iterator<String> item2 = list.iterator();
        while(item2.hasNext()){
            String key = item2.next();
            sortedMap.put(key,re.get(key));
        }

		return new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
	}

	/**
	* 获取货源列表
	* @return
	*/
	@GetMapping("/allSourceByTime")
	@Timed
	public ResponseEntity<Result> allSourceByTime(int size,int page) {
		Page<GoodsSource> result = goodsSourceService.findAllGoodsSource(size,page);
		System.out.println("==result===="+result);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSourcePage",result.getContent());
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}


	/**
	 * 根据开始、结束路线和货源类型获取货源列表
	 * @return
	 *//*
	@GetMapping("/lineSource")
	@Timed
	public ResponseEntity<Result> lineSource(String size,String page,@RequestParam(required = false) String start,@RequestParam(required = false) String end,@RequestParam(required = false) String goodsSourceProperty){
		System.out.println("111111111"+start+"000000000"+end+"222222222"+size+"effe"+page);
		int pageInt = 0;//默认页数0
		int sizeInt = 10;//默认每页条数10条


		if (!StringUtils.isBlank(page) && StringUtils.isNumeric(page)) {//页数不为空
			pageInt = Integer.parseInt(page);
		}
		if (!StringUtils.isBlank(size) && StringUtils.isNumeric(size)) {//每页条数不为空
			sizeInt = Integer.parseInt(size);
		}
		GoodsSource goodsSource=new GoodsSource();
		goodsSource.setStart(start);
		goodsSource.setEnd(end);
		Pageable pageable = of(pageInt, sizeInt);
		switch (goodsSourceProperty){
			case "ZH":
				goodsSource.setGoodsSourceProperty(GoodsSourceProperty.ZH);
				break;
			case "QH":
				goodsSource.setGoodsSourceProperty(GoodsSourceProperty.QH);
				break;
			case "SB":
				goodsSource.setGoodsSourceProperty(GoodsSourceProperty.SB);
				break;
			case "HG":
				goodsSource.setGoodsSourceProperty(GoodsSourceProperty.HG);
				break;
			default:
				goodsSource.setGoodsSourceProperty(GoodsSourceProperty.OTHER);
				break;
		}
		Page<GoodsSource> goodsSourcePage=goodsSourceService.findall(pageable,goodsSource);
		Map<String, Object> re = new HashMap<>();
		if(start.equals(goodsSourcePage.getContent().get(1).getStart()) ){
			re.put("goodsSourcePage",start);
		}
		if(end.equals(goodsSourcePage.getContent().get(2).getEnd())){
			re.put("goodsSourcePage",end);
		}

		re.put("goodsSourcePage",goodsSourcePage);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}*/


	/**
	 * 根据开始、结束路线和货源类型获取货源列表
	 * @return
	 */
	@GetMapping("/lineSource")
	@Timed
	public ResponseEntity<Result> findALLGoodsSourceByCondition(@RequestParam(required = false) String start,@RequestParam(required = false)  String end, @RequestParam(required = false)GoodsSourceProperty goodsSourceProperty){
		System.out.println("111111111"+start+"000000000"+end+"222222222"+goodsSourceProperty+"===effe");
		List<GoodsSource> goodsSourcePage=goodsSourceService.findGoodsSourceByCondition(start,end,goodsSourceProperty);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSourcePage",goodsSourcePage);
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

    /**
     * 根据开始、结束路线和货源类型获取货源列表(2.0)
     * @return
     */
    @GetMapping("/lineSourceTwo")
    @Timed
    public ResponseEntity<Result> findALLGoodsSourceByConditionTwo(@RequestParam(required = false) String start,@RequestParam(required = false) String end, @RequestParam(required = false) GoodsSourceProperty goodsSourceProperty,Pageable pageable){
        System.out.println("111111111"+start+"000000000"+end+"222222222"+goodsSourceProperty+"===effe");
        List<GoodsSource> goodsSourcePage=goodsSourceService.findGoodsSourceByConditionTwo(start,end,goodsSourceProperty, pageable);
        Map<String, Object> re = new HashMap<>();
        System.out.println("re======="+re.size()+"11111111111111");
        re.put("goodsSourcePage",goodsSourcePage);
        return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
    }

	/**
	 * 根据开始、结束路线和货源类型获取货源列表(3.0)
	 * @return
	 */
	@GetMapping("/lineSourceThree")
	@Timed
	public ResponseEntity<Result> findALLGoodsSourceByConditionThree(@RequestParam(required = false) String start,@RequestParam(required = false) String end, @RequestParam(required = false) GoodsSourceProperty goodsSourceProperty,Pageable pageable){
		System.out.println("111111111"+start+"000000000"+end+"222222222"+goodsSourceProperty+"===effe");
		List<GoodsSource> goodsSourcePage=goodsSourceService.findGoodsSourceByConditionThree(start,end,goodsSourceProperty, pageable);
		Map<String, Object> re = new HashMap<>();
		System.out.println("re======="+re.size()+"11111111111111");
		re.put("goodsSourcePage",goodsSourcePage);
		Map<String,Object> sortedMap = new LinkedHashMap<>();
		List<String> list = new ArrayList<>();
		Iterator<String> item = re.keySet().iterator();
		while(item.hasNext()){
			list.add(item.next());
		}
		Collections.sort(list);
		Iterator<String> item2 = list.iterator();
		while(item2.hasNext()){
			String key = item2.next();
			sortedMap.put(key,re.get(key));
		}
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
	}

	/**
	 * 根据开始、结束路线和货源类型获取货源列表(3.0)
	 * @return
	 */
	@GetMapping("/lineSourceNext")
	@Timed
	public ResponseEntity<Result> findALLGoodsSourceByConditionNext(@RequestParam(required = false) String start,@RequestParam(required = false) String end, @RequestParam(required = false) GoodsSourceProperty goodsSourceProperty,Pageable pageable){
		System.out.println("111111111"+start+"000000000"+end+"222222222"+goodsSourceProperty+"===effe");
		List<GoodsSource> goodsSourcePage=goodsSourceService.findGoodsSourceByConditionNEXT(start,end,goodsSourceProperty, pageable);
		Map<String, Object> re = new HashMap<>();
		System.out.println("re======="+re.size()+"11111111111111");
		re.put("goodsSourcePage",goodsSourcePage);
		Map<String,Object> sortedMap = new LinkedHashMap<>();
		List<String> list = new ArrayList<>();
		Iterator<String> item = re.keySet().iterator();
		while(item.hasNext()){
			list.add(item.next());
		}
		Collections.sort(list);
		Iterator<String> item2 = list.iterator();
		while(item2.hasNext()){
			String key = item2.next();
			sortedMap.put(key,re.get(key));
		}
		/*Collections.sort(list, new Comparator<GoodsSource>() {
			@Override
			public int compare(GoodsSource o1, GoodsSource o2) {
				//降序
				return o2.getEffectiveDate().compareTo(o1.getEffectiveDate());
			}
		});*/

		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
	}

	/**
	 * 通过时间findGoodsSourceByConditionNEXTone
	 * @param start
	 * @param end
	 * @param goodsSourceProperty
	 * @param size
	 * @param page
	 * @return
	 */
	@GetMapping("/findALLGoodsSourceByTimeDESC")
	@Timed
	public ResponseEntity<Result> findALLGoodsSourceByTimeDESC(@RequestParam(required = false) String start,@RequestParam(required = false) String end, @RequestParam(required = false) GoodsSourceProperty goodsSourceProperty,int size,int page){
		System.out.println("111111111"+start+"000000000"+end+"222222222"+goodsSourceProperty+"===effe");
		List<GoodsSource> goodsSourcePage=goodsSourceService.findGoodsSourceBytime(start,end,goodsSourceProperty, size,page);
		Map<String, Object> re = new HashMap<>();
		System.out.println("re======="+re.size()+"11111111111111");
		re.put("goodsSourcePage",goodsSourcePage);
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}


	/**
	 * 根据开始、结束路线和货源类型获取货源列表,没有任何条件
	 * @return
	 */
	@GetMapping("/findALLGoodsSourceNext")
	@Timed
	public ResponseEntity<Result> findALLGoodsSourceNext(@RequestParam(required = false) String start,@RequestParam(required = false) String end, @RequestParam(required = false) GoodsSourceProperty goodsSourceProperty){
		System.out.println("111111111"+start+"000000000"+end+"222222222"+goodsSourceProperty+"===effe");
		List<GoodsSource> goodsSourcePage=goodsSourceService.findGoodsSourceByConditionNEXTone(start,end,goodsSourceProperty);
		System.out.println("====goodsSourcePage===="+goodsSourcePage.size()+"=====");
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSourcePage",goodsSourcePage);
		System.out.println("re======="+re.size()+"11111111111111");
		Map<String,Object> sortedMap = new LinkedHashMap<>();
		List<String> list = new ArrayList<>();
		Iterator<String> item = re.keySet().iterator();
		while(item.hasNext()){
			list.add(item.next());
		}
		Collections.sort(list);
		Iterator<String> item2 = list.iterator();
		while(item2.hasNext()){
			String key = item2.next();
			sortedMap.put(key,re.get(key));
		}
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
	}



	/**
	 * 根据货源id查看用户某条货源详情findGoodsSourceBytime
	 * @return
	 *//*
	@GetMapping("/goods-sources/{id}")
	@Timed
	public ResponseEntity<Result> getGoodsSource(@PathVariable Long id) {//
		Optional<GoodsSource> goodsSource = goodsSourceService.findOne(id);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSource",goodsSource);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}*/

	/*
	 * 根据货源id查看用户某条货源详情
	 * @return
	 */
	@GetMapping("/goods-sourceslist/{id}")
	@Timed
	public ResponseEntity<Result> getGoodsSourceList(@PathVariable Long id) {
		Optional<GoodsSource> goodsSource = goodsSourceService.findOne(id);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSource",goodsSource);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 发布货源
	 * @param goodsSource
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/goods-sources")
	@Timed
	public ResponseEntity<Result> createGoodsSource(@Valid @RequestBody GoodsSource goodsSource) throws URISyntaxException {
		goodsSource.setId(null);
		GoodsSource result = goodsSourceService.save(goodsSource);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSource",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);

	}

	/**
	 * 删除货源
	 * @param id
	 * @return
	 */
	//@DeleteMapping("/goods-sources/{id}")
	@PostMapping("/deletegoods-sources/{id}")
	@Timed
	public ResponseEntity<Result> deleteGoodsSource(@PathVariable Long id) {
		System.out.println("deleteGoodsSource===="+id);
		goodsSourceService.delete(id);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSource","删除成功");
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 查询用户发布的所有货源()
	 *
	 */
	@GetMapping("/FindAllGoodsResource/{id}")
	@Timed
	public ResponseEntity<Result> FindAllGoodsResource(@PathVariable Long id){
      System.out.println("FindAllGoodsResource===="+id);
		List<GoodsSource> GoodSource=goodsSourceService.findAllById(id);
		Map<String, Object> re = new HashMap<>();
		re.put("GoodSource",GoodSource);
		Map<String,Object> sortedMap = new LinkedHashMap<>();
		List<String> list = new ArrayList<>();
		Iterator<String> item = re.keySet().iterator();
		while(item.hasNext()){
			list.add(item.next());
		}
		Collections.sort(list);
		Iterator<String> item2 = list.iterator();
		while(item2.hasNext()){
			String key = item2.next();
			sortedMap.put(key,re.get(key));
		}
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
	}

	//查询已经接单
	/**
	 * 查询已经接单的ENABLE
	 * @param status
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/QueryGoodsSourcestatus/{status}/{id}")
	@Timed
	public ResponseEntity<Result> QueryGoodsSourcestatus (@PathVariable("status") Status status,@PathVariable("id") Long id) throws URISyntaxException {
		System.out.println("QueryGoodsSourcestatus===="+status+"-----");
		System.out.println("====QueryGoodsSourcestatus===="+id+"-------");
		List<GoodsSource>AllGoodsource=goodsSourceService.findGoodsSoourceByIdAndStatus(status,id);
		Map<String, Object> re = new HashMap<>();
		re.put("AllGoodsourceStatus",AllGoodsource);
		Map<String,Object> sortedMap = new LinkedHashMap<>();
		List<String> list = new ArrayList<>();
		Iterator<String> item = re.keySet().iterator();
		while(item.hasNext()){
			list.add(item.next());
		}
		Collections.sort(list);
		Iterator<String> item2 = list.iterator();
		while(item2.hasNext()){
			String key = item2.next();
			sortedMap.put(key,re.get(key));
		}

		return new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
	}

 	/**
	 * 修改货源信息
	 * @param goodsSource
	 * @return
	 * @throws URISyntaxException
	 */
	@PutMapping("/goods-sources")
	@Timed
	public ResponseEntity<Result> updateGoodsSource(@Valid @RequestBody GoodsSource goodsSource) throws URISyntaxException {
		if (goodsSource.getId() == null) {
			throw new BadRequestAlertException("Invalid id", "GoodsSource", "idnull");
		}
		GoodsSource result = goodsSourceService.saveGoodsSource(goodsSource);
		Map<String, Object> re = new HashMap<>();
		re.put("GoodsSource",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}
    //***********************创建话题***********************************

	/**
	 * 创建话题
	 * @param topic
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/topics")
	@Timed
	public ResponseEntity<Result> createTopic(@Valid @RequestBody Topic topic) throws URISyntaxException {
		if (topic.getId() != null) {
			throw new BadRequestAlertException("A new topic cannot already have an ID", "topic", "idexists");
		}
		//创建话题，都是0
		topic.setFabulousCount(0);
		topic.setCommentCount(0);
		topic.setForwardCount(0);
		topic.setViewedCount(0);
		topic.setPublished(Instant.now());
		topic.setForwarded(false);
		Topic result = topicService.save(topic);
		Map<String, Object> re = new HashMap<>();
		if(result!=null){
			re.put("result","话题发布成功");
		}else {
			re.put("result","话题发布失败");
		}
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 获取物流圈(话题)列表
	 * @param
	 * @return
	 */
	@GetMapping("/Alltopics")
	@Timed
	public ResponseEntity<Result> getAllTopics(String size,String page) {
		int pageInt = 0;//默认页数0
		int sizeInt = 10;//默认每页条数10条
		if (!StringUtils.isBlank(page) && StringUtils.isNumeric(page)) {//页数不为空
			pageInt = Integer.parseInt(page);
		}
		if (!StringUtils.isBlank(size) && StringUtils.isNumeric(size)) {//每页条数不为空
			sizeInt = Integer.parseInt(size);
		}
		Pageable pageable = of(pageInt, sizeInt);
		Page<Topic> pages = topicService.findAll(pageable);
		Map<String, Object> re = new HashMap<>();
		re.put("Topic",pages);
        Map<String,Object> sortedMap = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        Iterator<String> item = re.keySet().iterator();
        while(item.hasNext()){
            list.add(item.next());
        }
        Collections.sort(list);
        Iterator<String> item2 = list.iterator();
        while(item2.hasNext()){
            String key = item2.next();
            sortedMap.put(key,re.get(key));
        }
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
	}


    /**
     * 获取物流圈(话题)列表02
     * @param
     * @return
     */
    @GetMapping("/getAllTopicsByTime")
    @Timed
    public ResponseEntity<Result> getAllTopicsByTime(int size,int page) {
        int pageInt = 0;//默认页数0
        int sizeInt = 10;//默认每页条数10条
        System.out.println("=size==="+size);
        System.out.println("===page=="+page);
        Page<Topic> pages = topicService.findAllTopic(size,page);
        Map<String, Object> re = new HashMap<>();
        re.put("Topic",pages);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
    }


	/**
	 * 删除话题1
	 */
	@PostMapping("/deletetopics/{id}")
	@Timed
	public ResponseEntity<Result> deleteTopic(@PathVariable Long id) {
        System.out.println("deleteTopic======"+id);
		topicService.delete(id);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSource","删除成功");
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 删除话题2
	 */
	@PostMapping("/deleteTopicone/{id}")
	@Timed
	public ResponseEntity<Result> deleteTopicone(@PathVariable Long id) {
		System.out.println("deleteTopic======"+id);
		topicService.deleteone(id);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSource","删除成功");
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 删除话题3
	 */
	@PostMapping("/deleteTopicTwo/{id}")
	@Timed
	public ResponseEntity<Result> deleteTopicTwo(@PathVariable Long id) {
		System.out.println("deleteTopic======"+id);
		topicService.deletetwo(id);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSource","删除成功");
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 删除话题4
	 */
	@PostMapping("/deleteTopicThree/{id}")
	@Timed
	public ResponseEntity<Result> deleteTopicThree(@PathVariable Long id) {
		System.out.println("deleteTopic======"+id);
		topicService.deleteThree(id);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSource","删除成功");
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}


	/**
	 * 删除话题5
	 */
	@PostMapping("/deleteTopicFore/{id}")
	@Timed
	public ResponseEntity<Result> deleteTopicFore(@PathVariable Long id) {
		System.out.println("deleteTopic======"+id);
		topicService.deletefree(id);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSource","删除成功");
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}


	/**
	 * 话题搜索
	 * @param query
	 * @param pageable
	 * @return
	 */
	@GetMapping("/_search/topics")
	@Timed
	public ResponseEntity<Result> searchTopics(@RequestParam String query, Pageable pageable) {

		Page<Topic> page = topicService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/topics");
		Map<String, Object> re = new HashMap<>();
		re.put("page",page);
		re.put("headers",headers);
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

    /**
     * 通过ID查询详情
     * @param id
     * @return
     */
	@GetMapping("/Querytopics/{id}")
	@Timed
	public ResponseEntity<Result> getTopic(@PathVariable Long id) {
		System.out.println("getTopic====="+id);
		Optional<Topic> topic = topicService.findOne(id);
		Map<String, Object> re = new HashMap<>();
		re.put("topic",topic);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

    /**
     * 对话题的修改
     * @param topic
     * @return
     * @throws URISyntaxException
     */
    @PutMapping("/Upadatetopics")
    @Timed
    public ResponseEntity<Result> updateTopic(@Valid @RequestBody Topic topic) throws URISyntaxException {

        if (topic.getId() == null) {
            throw new BadRequestAlertException("Invalid id", "topic", "idnull");
        }
        Topic result = topicService.save(topic);
        Map<String, Object> re = new HashMap<>();
        re.put("topic",result);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
    }

	/**
	 * 查询用户发布的所有话题
	 *
	 */
	@GetMapping("/FindAllTopPicBy/{id}")
	@Timed
	public ResponseEntity<Result> FindAllTopPicByID(@PathVariable Long id){
		System.out.println("===FindAllTopPicByID===="+id);
		List<Topic> topic=topicService.findAllTopicById(id);
		Map<String, Object> re = new HashMap<>();
		re.put("GoodSource",topic);
		Map<String,Object> sortedMap = new LinkedHashMap<>();
		List<String> list = new ArrayList<>();
		Iterator<String> item = re.keySet().iterator();
		while(item.hasNext()){
			list.add(item.next());
		}
		Collections.sort(list);
		Iterator<String> item2 = list.iterator();
		while(item2.hasNext()){
			String key = item2.next();
			sortedMap.put(key,re.get(key));
		}
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
	}

	//*******************************************


	/**
	 * 获取当前话题全部的评论(ok)
	 * @param
	 * @param
	 * @return
	 */
	@GetMapping("/topicsComment/{id}")
	@Timed
	public ResponseEntity<Result> getAllTopicComment(String size,String page,@PathVariable Long id){
		if (id == null) {
			throw new BadRequestAlertException("Invalid id", "TopicComment", "idnull");
		}
		int pageInt = 0;//默认页数0
		int sizeInt = 10;//默认每页条数10条

		if (!StringUtils.isBlank(page) && StringUtils.isNumeric(page)) {//页数不为空
			pageInt = Integer.parseInt(page);
		}
		if (!StringUtils.isBlank(size) && StringUtils.isNumeric(size)) {//每页条数不为空
			sizeInt = Integer.parseInt(size);
		}
		Pageable pageable = of(pageInt, sizeInt);
		Topic topic=new Topic();
		topic.setId(id);
		Page<TopicComment> pages=topicCommentService.findbyTopic(pageable,topic);
		System.out.println("===pages===="+pages);
		Map<String, Object> re = new HashMap<>();
		re.put("TopicComment",pages);
		Map<String,Object> sortedMap = new LinkedHashMap<>();
		List<String> list = new ArrayList<>();
		Iterator<String> item = re.keySet().iterator();
		while(item.hasNext()){
			list.add(item.next());
		}
		Collections.sort(list);
		Collections.reverse(list);
		Iterator<String> item2 = list.iterator();
		while(item2.hasNext()){
			String key = item2.next();
			sortedMap.put(key,re.get(key));

		}
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
	}

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/getAllTopicCommentByDESC/{id}")
    @Timed
    public ResponseEntity<Result> getAllTopicCommentByDESC(@PathVariable Long id){
        if (id == null) {
            throw new BadRequestAlertException("Invalid id", "TopicComment", "idnull");
        }
        int pageInt = 0;//默认页数0
        int sizeInt = 10;//默认每页条数10条


        //Pageable pageable = of(pageInt, sizeInt);
        Topic topic=new Topic();
        topic.setId(id);
        List<TopicComment> pages=topicCommentService.findTopicCommentbyTopic(topic);
        System.out.println("===pages===="+pages);
        Map<String, Object> re = new HashMap<>();
        re.put("TopicComment",pages);
        Map<String,Object> sortedMap = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        Iterator<String> item = re.keySet().iterator();
        while(item.hasNext()){
            list.add(item.next());
        }
        Collections.sort(list);
        Iterator<String> item2 = list.iterator();
        while(item2.hasNext()){
            String key = item2.next();
            sortedMap.put(key,re.get(key));

        }
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
    }




	/**
	 * 添加话题评论(ok)
	 * @param topicComment
	 * @return
	 */
	@PostMapping("/topicsComment")
	@Timed
	public ResponseEntity<Result> createTopicComment(@Valid @RequestBody TopicComment topicComment){

		if (topicComment.getId() != null) {
			throw new BadRequestAlertException("Invalid id", "TopicComment", "idnull");
		}
		topicComment.setoDateTime(Instant.now());
		TopicComment topicComments=topicCommentService.save(topicComment);
		Map<String, Object> re = new HashMap<>();
		//修改话题评论+1
		Optional<Topic> topic=topicService.findOne(topicComment.getTopic().getId());
		topic.get().setCommentCount(topic.get().getCommentCount()+1);//添加评论
		topicService.save(topic.get());
		re.put("TopicComment",topicComments);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 删除话题评论（ok 删除了8）
	 * @param id
	 * @return
	 */
	@DeleteMapping("/DeletetopicsComment/{id}")
	@Timed
	public ResponseEntity<Result> deleteTopicComment(@PathVariable Long id){
		if (id == null) {
			throw new BadRequestAlertException("Invalid id", "TopicComment", "idnull");
		}
		//查询评论是否存在
		TopicComment topicComment=topicCommentService.findTopicCommentsById(id);
		if(topicComment==null){
			throw new BadRequestAlertException("Invalid id", "TopicComment", "null");
		}
		//删除评论
		topicCommentService.delete(id);
		Topic topic=topicComment.getTopic();
		topic.setCommentCount(topic.getCommentCount()-1);
		//修改评论总数
		topicService.save(topic);
		Map<String, Object> re = new HashMap<>();
		re.put("message","删除成功");
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

     /**
     * 修改话题评论
     */
     @PutMapping("/topic-comments")
     @Timed
     public ResponseEntity<Result> updateTopicComment(@Valid @RequestBody TopicComment topicComment) throws URISyntaxException {

        if (topicComment.getId() == null) {
            throw new BadRequestAlertException("Invalid id","topicComment" , "idnull");
        }
        TopicComment result = topicCommentService.save(topicComment);
        return  new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);

     }
    /**
     * 通过id获取话题评论的详情(ok)
     */
     @GetMapping("/topic-comments/{id}")
     @Timed
     public ResponseEntity<Result> getTopicComment(@PathVariable Long id) {
        Optional<TopicComment> topicComment = topicCommentService.findOne(id);
         Map<String, Object> re = new HashMap<>();
         re.put("topicComment",topicComment);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
    }

	/**
	 * 话题评论的搜索
	 * @param query
	 * @param pageable
	 * @return
	 */
	@GetMapping("/_search/topic-comments")
	@Timed
	public  ResponseEntity<Result> searchTopicComments(@RequestParam String query, Pageable pageable) {
		Page<TopicComment> page = topicCommentService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/topic-comments");
		Map<String, Object> re = new HashMap<>();
		re.put("page",page);
		re.put("headers",headers);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 查询用户发布的所有话题评论
	 *
	 */
	@GetMapping("/FindAllTopPicCommentBy/{id}")
	@Timed
	public ResponseEntity<Result> FindAllTopPicCommentByID(@PathVariable Long id){
		System.out.println("===FindAllTopPicCommentByID===="+id);
		//List<Topic> topic=topicService.findAllTopicById(id);
		List<TopicComment> topicComments=topicCommentService.findAllTopicCommentByUserID(id);
		Map<String, Object> re = new HashMap<>();
		re.put("topicComments",topicComments);
		Map<String,Object> sortedMap = new LinkedHashMap<>();
		List<String> list = new ArrayList<>();
		Iterator<String> item = re.keySet().iterator();
		while(item.hasNext()){
			list.add(item.next());
		}
		Collections.sort(list);
		Iterator<String> item2 = list.iterator();
		while(item2.hasNext()){
			String key = item2.next();
			sortedMap.put(key,re.get(key));
		}
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
	}


	//****************************************************

    /**
	 * 用户点赞(话题ID和用户ID)
	 * @param
	 * @return
	 */
	@PostMapping("/topicFabulous/{id}/{userid}")
	@Timed
	public ResponseEntity<Result> createTopicFabulous(@PathVariable Long id,@PathVariable Long userid){
		if (id == null) {
			throw new BadRequestAlertException("Invalid id", "topic", "idnull");
		}
		System.out.println("topicFabulous===="+id+"topicFabulous===="+userid+"00000");
		Topic topic=topicService.findOne(id).get();
		UserInfo userInfo=new UserInfo();
		userInfo.setId(userid);
		TopicFabulous newtopicFabulous=topicFabulousService.findTopicFabulousByUserInfoAndTopic(userInfo,topic);
		System.out.println("===newtopicFabulous==="+newtopicFabulous==null+"========");
		boolean message=true;
		if(newtopicFabulous==null){
			//此用户没有点赞
			//添加赞
			TopicFabulous topicFabulous=new TopicFabulous();
			topicFabulous.setTopic(topic);
			topicFabulous.setUserInfo(userInfo);
			topicFabulous.setoDateTime(Instant.now());
			topicFabulousService.save(topicFabulous);
			//修改赞总数+1
			topic.setFabulousCount(topic.getFabulousCount()+1);
			//topicService.save(topic);
		}else{
			message=false;
			//此用户已点赞（点赞再点就是删除赞）
			//删除赞
			topicFabulousService.delete(newtopicFabulous.getId());
			topic.setFabulousCount(topic.getFabulousCount()-1);
			//topicService.save(topic);
		}
		topicService.save(topic);
		Map<String, Object> re = new HashMap<>();
		re.put("message",message?"添加成功":"取消成功");
		re.put("code",message);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 对当前的话题点赞的修改
	 * @param topicFabulous
	 * @return
	 * @throws URISyntaxException
	 */
	@PutMapping("/topic-updatefabulous")
	@Timed
	public ResponseEntity<Result> updateTopicFabulous(@Valid @RequestBody TopicFabulous topicFabulous) throws URISyntaxException {

		if (topicFabulous.getId() == null) {
			throw new BadRequestAlertException("Invalid id", "topicFabulous", "idnull");
		}

		TopicFabulous result = topicFabulousService.save(topicFabulous);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	/**
	 *得到全部点赞主题
	 * @param pageable
	 * @return
	 */
	@GetMapping("/topic-fabulous")
	@Timed
	public ResponseEntity<Result> getAllTopicFabulous(Pageable pageable) {
		Page<TopicFabulous> page = topicFabulousService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/topic-fabulous");
		Map<String, Object> re = new HashMap<>();
		re.put("page",page);
		re.put("headers",headers);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 通过ID来得到的点赞主题
	 * @param id
	 * @return
	 */
	@GetMapping("/topic-fabulous/{id}")
	@Timed
	public ResponseEntity<Result> getTopicFabulous(@PathVariable Long id) {
		System.out.println("getTopicFabulous=====id"+id);
		Optional<TopicFabulous> topicFabulous = topicFabulousService.findOne(id);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(topicFabulous), HttpStatus.OK);
	}

	/**
	 * 删除点赞主题 通过ID
	 * @param id
	 * @return
	 */
	@DeleteMapping("/Deletetopic-fabulous/{id}")
	@Timed
	public ResponseEntity<Result> deleteTopicFabulous(@PathVariable Long id) {
		System.out.println("deleteTopicFabulous=====id"+id);
		topicFabulousService.delete(id);
		Map<String, Object> re = new HashMap<>();
		re.put("message","删除成功");
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 搜索点赞话题
	 * @param query
	 * @param pageable
	 * @return
	 */
	@GetMapping("/_search/topic-fabulous")
	@Timed
	public ResponseEntity<Result> searchTopicFabulous(@RequestParam String query, Pageable pageable) {
		Page<TopicFabulous> page = topicFabulousService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/topic-fabulous");
		Map<String, Object> re = new HashMap<>();
		re.put("page",page);
		re.put("headers",headers);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 查询用户发布的所有点赞话题
	 *
	 */
	@GetMapping("/FindAllTopPicFabulousBy/{id}")
	@Timed
	public ResponseEntity<Result> FindAllTopicFabulousByID(@PathVariable Long id){
		System.out.println("===FindAllTopicFabulousByID===="+id);
	    List<TopicFabulous> topicFabulous=topicFabulousService.findTopicFabulousByUserinfoID(id);
		Map<String, Object> re = new HashMap<>();
		re.put("topicFabulous",topicFabulous);
		Map<String,Object> sortedMap = new LinkedHashMap<>();
		List<String> list = new ArrayList<>();
		Iterator<String> item = re.keySet().iterator();
		while(item.hasNext()){
			list.add(item.next());
		}
		Collections.sort(list);
		Iterator<String> item2 = list.iterator();
		while(item2.hasNext()){
			String key = item2.next();
			sortedMap.put(key,re.get(key));
		}
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
	}


    //*************关于话题转发的一系列操作************************************

	/**
	 *创建转发话题
	 * @param topicForward
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/topic-forwards")
	@Timed
	public ResponseEntity<Result> createTopicForward(@Valid @RequestBody TopicForward topicForward) throws URISyntaxException {
		if (topicForward.getId() != null) {
			throw new BadRequestAlertException("A new topicForward cannot already have an ID", "topicForward", "idexists");
		}
		TopicForward topicForwards=topicForwardService.save(topicForward);
		Map<String, Object> re = new HashMap<>();
		//修改话题转发+1
		Topic topic=topicForward.getTopic();
		topic.setForwardCount(topic.getForwardCount()+1);//添加评论
	    topicService.save(topic);
		re.put("topicForward",topicForward);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 修改话题转发的
	 * @param topicForward
	 * @return
	 * @throws URISyntaxException
	 */
	@PutMapping("/topic-forwards")
	@Timed
	public ResponseEntity<Result> updateTopicForward(@Valid @RequestBody TopicForward topicForward) throws URISyntaxException {
		if (topicForward.getId() == null) {
			throw new BadRequestAlertException("Invalid id", "topicForward", "idnull");
		}
		TopicForward result = topicForwardService.save(topicForward);
		Map<String, Object> re = new HashMap<>();
		re.put("topicForward",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	/**
	 * 得到全部的转发话题
	 */
	@GetMapping("/topic-forwards")
	@Timed
	public ResponseEntity<Result> getAllTopicForwards(Pageable pageable) {
		Page<TopicForward> page = topicForwardService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/topic-forwards");
		Map<String, Object> re = new HashMap<>();
		re.put("GoodsSource",page);
		re.put("GoodsSource",headers);
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 通过ID得到转发的详情
	 * @param id
	 * @return
	 */
	@GetMapping("/topic-forwards/{id}")
	@Timed
	public  ResponseEntity<Result> getTopicForward(@PathVariable Long id) {
		System.out.println("getTopicForward=====id"+id);
		Optional<TopicForward> topicForward = topicForwardService.findOne(id);
		Map<String, Object> re = new HashMap<>();
		re.put("topicForward",topicForward);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 删除转发
	 * @param id
	 * @return
	 */
	@DeleteMapping("/topic-forwards/{id}")
	@Timed
	public  ResponseEntity<Result> deleteTopicForward(@PathVariable Long id) {
		System.out.println("deleteTopicForward==="+id+"===");
		topicForwardService.delete(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	/**
	 * 搜索转发
	 * @param query
	 * @param pageable
	 * @return
	 */
	@GetMapping("/_search/topic-forwards")
	@Timed
	public ResponseEntity<Result> searchTopicForwards(@RequestParam String query, Pageable pageable) {
		Page<TopicForward> page = topicForwardService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/topic-forwards");
		Map<String, Object> re = new HashMap<>();
		re.put("page",page);
		re.put("headers",headers);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	//****************招聘模块************************

	/**
	 * 招聘模块
	 * @param sysRecruitmentInformation
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/Createsys-recruitment-informations")
	@Timed
	public  ResponseEntity<Result> createSysRecruitmentInformation(@Valid @RequestBody SysRecruitmentInformation sysRecruitmentInformation) throws URISyntaxException {
        System.out.println("createSysRecruitmentInformation===="+sysRecruitmentInformation.getCategoryName()+"====CategoryName()");
		if (sysRecruitmentInformation.getId() != null) {
			throw new BadRequestAlertException("A new sysRecruitmentInformation cannot already have an ID", "sysRecruitmentInformation", "idexists");
		}
		//System.out.println("000000000===="+sysRecruitmentInformation.getUserInfo().getId()+"==========");
		SysRecruitmentInformation result = sysRecruitmentInformationService.save(sysRecruitmentInformation);
		Map<String, Object> re = new HashMap<>();
		re.put("result",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}


	/**
	 * 对招聘信息的修改
	 * @param sysRecruitmentInformation
	 * @return
	 * @throws URISyntaxException
	 */
	@PutMapping("/Upadatesys-recruitment-informations")
	@Timed
	public ResponseEntity<Result> updateSysRecruitmentInformation(@Valid @RequestBody SysRecruitmentInformation sysRecruitmentInformation) throws URISyntaxException {
		System.out.println("createSysRecruitmentInformation===="+sysRecruitmentInformation.getCategoryName()+"====CategoryName()");
		if (sysRecruitmentInformation.getId() == null) {
			throw new BadRequestAlertException("Invalid id", "sysRecruitmentInformation", "idnull");
		}
		SysRecruitmentInformation result = sysRecruitmentInformationService.save(sysRecruitmentInformation);
		Map<String, Object> re = new HashMap<>();
		re.put("page",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 查询全部的招聘信息
	 * @param
	 * @return
	 */
	@GetMapping("/sys-recruitment-informations")
	@Timed
	public ResponseEntity<Result> getAllSysRecruitmentInformations() {
		List<SysRecruitmentInformation> pages = sysRecruitmentInformationService.findAllSys();
		//HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pages, "/api/sys-recruitment-informations");
		Map<String, Object> re = new HashMap<>();
		re.put("pages",pages);
		//re.put("headers",headers);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 查询全部的招聘信息(通过ID排序)
	 * @param
	 * @return
	 */
	@GetMapping("/getAllSysRecruitmentInformationsByIdDesc")
	@Timed
	public ResponseEntity<Result> getAllSysRecruitmentInformationsByID(int size,int page) {
		System.out.println("**********"+size+"====="+page+"====00000000000000");
		Page<SysRecruitmentInformation> pages = sysRecruitmentInformationService.findAllByTIME(size,page);
		//HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pages, "/api/sys-recruitment-informations");
		Map<String, Object> re = new HashMap<>();
		re.put("pages",pages);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}




 	/**
	 * 通过ID查找详细
	 * @param id
	 * @return
	 */
	@GetMapping("/sys-recruitment-informations/{id}")
	@Timed
	public ResponseEntity<Result> getSysRecruitmentInformation(@PathVariable Long id) {
		System.out.println("getSysRecruitmentInformation==="+id+"===");
		Optional<SysRecruitmentInformation> sysRecruitmentInformation = sysRecruitmentInformationService.findOne(id);
		Map<String, Object> re = new HashMap<>();
		re.put("page",sysRecruitmentInformation);
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@DeleteMapping("/sys-recruitment-informations/{id}")
	@Timed
	public ResponseEntity<Result> deleteSysRecruitmentInformation(@PathVariable Long id) {
		System.out.println("deleteSysRecruitmentInformation==="+id+"===");
		System.out.println("id======"+id);
		sysRecruitmentInformationService.delete(id);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSource","删除成功");
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);

	}

	/**
	 * 搜索
	 * @param query
	 * @param
	 * @return
	 */
	@GetMapping("/_search/sys-recruitment-informations")
	@Timed
	public ResponseEntity<Result> QuerySysRecruitmentInformations(@RequestParam String query, String size,String page) {
		int pageInt = 0;//默认页数0
		int sizeInt = 10;//默认每页条数10条


		if (!StringUtils.isBlank(page) && StringUtils.isNumeric(page)) {//页数不为空
			pageInt = Integer.parseInt(page);
		}
		if (!StringUtils.isBlank(size) && StringUtils.isNumeric(size)) {//每页条数不为空
			sizeInt = Integer.parseInt(size);
		}
		Pageable pageable = of(pageInt, sizeInt);
		Page<SysRecruitmentInformation> pages = sysRecruitmentInformationService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, pages, "/api/_search/sys-recruitment-informations");
		Map<String, Object> re = new HashMap<>();
		re.put("page",page);
		re.put("page",headers);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}


     /**
	 * 通过ID查找全部的招聘信息(我把招聘信息的招聘人数字段改为用户ID)
	 * @return
	 */
	@GetMapping("/getSysRecruitmentInformationByUserID_id/{id}")
	@Timed
	public ResponseEntity<Result> getSysRecruitmentInformationByUserID(@PathVariable Long id,Pageable pageable) {
		System.out.println("getSysRecruitmentInformationByUserID==="+id+"===");
		List<SysRecruitmentInformation> sysRecruitmentInformation = sysRecruitmentInformationService.QuereySysRecruitemByUserID(id,pageable);
		Map<String, Object> re = new HashMap<>();
		re.put("page",sysRecruitmentInformation);
		Map<String,Object> sortedMap = new LinkedHashMap<>();
		List<String> list = new ArrayList<>();
		Iterator<String> item = re.keySet().iterator();
		while(item.hasNext()){
			list.add(item.next());
		}
		Collections.sort(list);
		Iterator<String> item2 = list.iterator();
		while(item2.hasNext()){
			String key = item2.next();
			sortedMap.put(key,re.get(key));
		}
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(sortedMap), HttpStatus.OK);
	}

	/**
	 * 通过ID查找全部的招聘信息(我把招聘信息的招聘人数字段改为用户ID)02
	 * @return
	 */
	@GetMapping("/getSysRecruitmentInformationByUserIDAndIDdesc/{id}")
	@Timed
	public ResponseEntity<Result> getSysRecruitmentInformationByUserIDAndIDdesc(@PathVariable Long id,int size,int page) {

		List<SysRecruitmentInformation> sysRecruitmentInformation = sysRecruitmentInformationService.QuereySysRecruitemByUserIDAndIDdesc(id,size,page);
		Map<String, Object> re = new HashMap<>();
		re.put("page",sysRecruitmentInformation);
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}


	//****************积分商城********************
	//商品的兑换
	@PostMapping("/goods-exchanges")
	@Timed
	public ResponseEntity<Result> InsertGoodsExchange(@Valid @RequestBody GoodsExchange goodsExchange) throws URISyntaxException {
		if (goodsExchange.getId() != null) {
			throw new BadRequestAlertException("A new goodsExchange cannot already have an ID", "goodsExchange", "idexists");
		}
		//随机生成10位字符串作为单号
		String i= RandomStringUtils.randomAlphanumeric(10);
		System.out.println("==随机生成===="+i+"========");
        goodsExchange.setOddNumbers(i);
		//这是调用商品详情方法查询出改商品，那么证明不同的GoodsID查询出不同的GOODs，改变兑换的人数
		Optional<Goods> one = goodsService.findOne(goodsExchange.getGoods().getId());
		System.out.println("====one===="+one.get().getPeopleCount());
		Integer count=one.get().getPeopleCount();
		Integer countALL=count+goodsExchange.getExchangeCount();
		one.get().setPeopleCount(countALL);
		//****************
		//得到当前剩余数量（10）,进行库存比对；
		Integer total=one.get().getTotal();
		System.out.println("===total="+total);
		//得到兑换的数量
		Integer exchangecouut=goodsExchange.getExchangeCount();
        System.out.println("==exchangecouut=="+exchangecouut);
		if(total<exchangecouut){
		Map<String, Object> re = new HashMap<>();
		re.put("result","亲，储存没有那么多~，请重新选择个数");
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
		}
		Integer totalAll=total-exchangecouut;
		if(totalAll<0||totalAll==0){
		Map<String, Object> re = new HashMap<>();
		re.put("result","该商品已经兑换完喽~，即将下架");
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
		}
		one.get().setTotal(totalAll);
		//录入商品(目的是修改商品兑换数量和商品总数)
		Goods Goodsresult = goodsService.save(one.get());
		//录入到商品兑换表
		GoodsExchange result = goodsExchangeService.save(goodsExchange);
		Map<String, Object> re = new HashMap<>();
		re.put("result",result);
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);

	}

	/**
	 * 删除兑换的商品
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deletegoods-exchanges/{id}")
	@Timed
	public ResponseEntity<Result> deleteGoodsExchange(@PathVariable Long id) {
		goodsExchangeService.delete(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	/**
	 * 搜索兑换的商品
	 * @param query
	 * @param pageable
	 * @return
	 */
	@GetMapping("/_search/goods-exchanges")
	@Timed
	public ResponseEntity<Result> searchGoodsExchanges(@RequestParam String query, Pageable pageable) {
		Page<GoodsExchange> page = goodsExchangeService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/goods-exchanges");
		Map<String, Object> re = new HashMap<>();
		re.put("page",page);
		re.put("headers",headers);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 *通过ID得到兑换商品
	 * @param id
	 * @return
	 */
	@GetMapping("/goods-exchangesBy/{id}")
	@Timed
	public ResponseEntity<Result> getGoodsExchange(@PathVariable Long id) {
		Optional<GoodsExchange> goodsExchange = goodsExchangeService.findOne(id);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(goodsExchange), HttpStatus.OK);
	}

	/**
	 * 修改兑换的商品
	 * @param goodsExchange
	 * @return
	 * @throws URISyntaxException
	 */
	@PutMapping("/Upadategoods-exchanges")
	@Timed
	public ResponseEntity<Result> updateGoodsExchange(@Valid @RequestBody GoodsExchange goodsExchange) throws URISyntaxException {

		if (goodsExchange.getId() == null) {
			throw new BadRequestAlertException("Invalid id", "goodsExchange", "idnull");
		}
		GoodsExchange result = goodsExchangeService.save(goodsExchange);
		Map<String, Object> re = new HashMap<>();
		re.put("result",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 得到全部的兑换商品
	 * @param pageable
	 * @return
	 */
	@GetMapping("/Allgoods-exchanges")
	@Timed
	public ResponseEntity<Result> getAllGoodsExchanges(Pageable pageable) {

		Page<GoodsExchange> page = goodsExchangeService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/goods-exchanges");
		Map<String, Object> re = new HashMap<>();
		re.put("page",page);
		re.put("headers",headers);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);

	}



	//***********************对会员的管理**************
	/**
	 * 申请成为会员
	 * @param member
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/members")
	@Timed
	public ResponseEntity<Result> createMember(@Valid @RequestBody Member member) throws URISyntaxException {
		if (member.getId() != null) {
			throw new BadRequestAlertException("A new member cannot already have an ID", "member", "idexists");
		}
		Member result = memberService.save(member);
		Map<String, Object> re = new HashMap<>();
		re.put("result",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 申请成为会员2.0
	 * @param member
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/inputmembers")
	@Timed
	public ResponseEntity<Result> createMemberinput(@Valid @RequestBody Member member) throws URISyntaxException {
		System.out.println("createMemberinput======="+member.getUserInfo().getMobilePhone()+"00000000000");
		if (member.getId() != null) {
			throw new BadRequestAlertException("A new member cannot already have an ID", "member", "idexists");
		}
		Member result = memberService.input(member);
		if (result!=null){
			Map<String, Object> re = new HashMap<>();
			re.put("member","该用户已经是会员");
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
		}else {
			Map<String, Object> re = new HashMap<>();
			re.put("member","该用户暂时未开通专线");
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
		}

	}


	/**
	 * 申请成为会员2.0
	 * @param member
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/inputmembersTWO")
	@Timed
	public ResponseEntity<Result> createMemberinputTWO(@Valid @RequestBody Member member) throws URISyntaxException {
		System.out.println("createMemberinput======="+member.getUserInfo().getMobilePhone()+"00000000000");
		if (member.getId() != null) {
			throw new BadRequestAlertException("A new member cannot already have an ID", "member", "idexists");
		}
		Member result = memberService.inputmember(member);
		if (result!=null){
			Map<String, Object> re = new HashMap<>();
			re.put("member","该用户已经是会员");
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
		}else {
			Map<String, Object> re = new HashMap<>();
			re.put("member","录入成功");
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
		}

	}



    /**
     *3.0申请会员
     * @param member
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/inputmembersTwo")
    @Timed
    public ResponseEntity<Result> createMemberinputThree(@Valid @RequestBody MemberTwo member) throws URISyntaxException {
        System.out.println("createMemberinput======="+member.getMobilePhone()+"00000000000");
        if (member.getId() != null) {
            throw new BadRequestAlertException("A new member cannot already have an ID", "MemberTwo", "idexists");
        }
        MemberTwo result = memberService.inputMemberTwo(member);
        if (result!=null){
            Map<String, Object> re = new HashMap<>();
            re.put("member","该用户已经是会员");
            return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
        }
        return null;
    }


	/**mvn -Pprod clean package -DskipTes
	 * 删除会员
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deletemembers/{id}")
	@Timed
	public ResponseEntity<Result> deleteMember(@PathVariable Long id) {
		memberService.delete(id);
		return new ResponseEntity<>( HttpStatus.OK);

	}

	/**
	 * 通过ID来得到会员详细信息
	 * @param id
	 * @return
	 */
	@GetMapping("/membersBy/{id}")
	@Timed
	public ResponseEntity<Result> getMember(@PathVariable Long id) {
		System.out.println("getMember====="+id+"========");
		Optional<Member> member = memberService.findOne(id);
        Map<String, Object> re = new HashMap<>();
        re.put("member",member);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 得到全部的会员信息
	 * @return
	 */
	@GetMapping("/Allmembers")
	@Timed
	public ResponseEntity<Result> getAllMembers() {
        List<Member> member= memberService.findAll();
        Map<String, Object> re = new HashMap<>();
        re.put("member",member);
        return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);

	}

    /**
     * 修改会员信息
     * @param member
     * @return
     * @throws URISyntaxException
     */
	@PutMapping("/Upadatemembers")
	@Timed
	public ResponseEntity<Result> updateMember(@Valid @RequestBody Member member) throws URISyntaxException {
		if (member.getId() == null) {
			throw new BadRequestAlertException("Invalid id", "member", "idnull");
		}
		Member result = memberService.save(member);
        return  new ResponseEntity<>(ResultGenerator.genSuccessResult(member), HttpStatus.OK);

    }

    /**
     *搜索会员
     * @param query
     * @return
     */
   /* @GetMapping("/_search/members")
    @Timed
    public ResponseEntity<Result> searchMembers(@RequestParam String query) {
		List<Member> Allmenber=memberService.queryMember(query);
		Map<String, Object> re = new HashMap<>();
		re.put("Allmenber",Allmenber);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
    }*/

	@GetMapping("/_search/members")
	@Timed
	public ResponseEntity<Result> searchMembers(@RequestParam String query) {
		System.out.println("searchMembers==="+query);
		List<Member> Allmenber=memberService.search(query);
		Map<String, Object> re = new HashMap<>();
		re.put("Allmenber",Allmenber);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

   //****************************************************

    /**创建优惠活动
	 * POST  /preferential-activities : Create a new preferentialActivities.
     *
	 * @param preferentialActivities the preferentialActivities to create
     * @return the ResponseEntity with status 201 (Created) and with body the new preferentialActivities, or with status 400 (Bad Request) if the preferentialActivities has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
	@PostMapping("/preferential-activities")
	@Timed
	public ResponseEntity<Result> createPreferentialActivities(@Valid @RequestBody PreferentialActivities preferentialActivities) throws URISyntaxException {
		System.out.println("preferentialActivities====="+preferentialActivities.getId()+"===00000");
		if (preferentialActivities.getId() != null) {
			throw new BadRequestAlertException("A new preferentialActivities cannot already have an ID", "preferentialActivities", "idexists");
		}
		PreferentialActivities result = preferentialActivitiesService.save(preferentialActivities);
		Map<String, Object> re = new HashMap<>();
		re.put("result",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 修改优惠活动
	 * @param preferentialActivities
	 * @return
	 * @throws URISyntaxException
	 */
	@PutMapping("/Upadatepreferential-activities")
	@Timed
	public ResponseEntity<Result> updatePreferentialActivities(@Valid @RequestBody PreferentialActivities preferentialActivities) throws URISyntaxException {
		System.out.println("preferentialActivities====="+preferentialActivities.getId()+"===00000");
		if (preferentialActivities.getId() == null) {
			throw new BadRequestAlertException("Invalid id", "preferentialActivities", "idnull");
		}
		PreferentialActivities result = preferentialActivitiesService.save(preferentialActivities);
		Map<String, Object> re = new HashMap<>();
		re.put("result",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 得到全部得优惠活动
	 * @param pageable
	 * @return
	 */
	@GetMapping("/Allpreferential-activities")
	@Timed
	public ResponseEntity<Result> getAllPreferentialActivities(Pageable pageable) {
		Page<PreferentialActivities> page = preferentialActivitiesService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/preferential-activities");
		Map<String, Object> re = new HashMap<>();
		re.put("headers",headers);
		re.put("page",page);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 通过ID来查询优惠得详情
	 * @param id
	 * @return
	 */
	@GetMapping("/preferential-activitiesBy/{id}")
	@Timed
	public ResponseEntity<Result> getPreferentialActivities(@PathVariable Long id) {
		System.out.println("getPreferentialActivities====="+id+"===00000");
		Optional<PreferentialActivities> preferentialActivities = preferentialActivitiesService.findOne(id);
		Map<String, Object> re = new HashMap<>();
		re.put("preferentialActivities",preferentialActivities);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 删除优惠活动
	 * @param id
	 * @return
	 */
	@DeleteMapping("/Deletepreferential-activities/{id}")
	@Timed
	public ResponseEntity<Result> deletePreferentialActivities(@PathVariable Long id) {
		System.out.println("deletePreferentialActivities====="+id+"===00000");
		preferentialActivitiesService.delete(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	/**
	 * 搜索优惠条件
	 * @param query
	 * @param pageable
	 * @return
	 */
	@GetMapping("/_search/preferential-activities")
	@Timed
	public  ResponseEntity<Result> searchPreferentialActivities(@RequestParam String query, Pageable pageable) {
		Page<PreferentialActivities> page = preferentialActivitiesService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/preferential-activities");
		Map<String, Object> re = new HashMap<>();
		re.put("headers",headers);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}


    //*********商品得发布****************************************

	@PostMapping("/Creategoods")
	@Timed
	public ResponseEntity<Result> createGoods(@Valid @RequestBody Goods goods) throws URISyntaxException {
		System.out.println("createGoods===="+goods.getName()+"==0000000");
		if (goods.getId() != null) {
			throw new BadRequestAlertException("A new goods cannot already have an ID", "goods", "idexists");
		}
		goods.setPeopleCount(0);
		Goods result = goodsService.save(goods);
		Map<String, Object> re = new HashMap<>();
		re.put("result",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 商品得修改
	 * @param goods
	 * @return
	 * @throws URISyntaxException
	 */
	@PutMapping("/Upadategoods")
	@Timed
	public ResponseEntity<Result> updateGoods(@Valid @RequestBody Goods goods) throws URISyntaxException {
		if (goods.getId() == null) {
			throw new BadRequestAlertException("Invalid id", "goods", "idnull");
		}
		Goods result = goodsService.save(goods);
		Map<String, Object> re = new HashMap<>();
		re.put("result",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 得到全部得商品
	 * @param pageable
	 * @return
	 */
	@GetMapping("/Allgoods")
	@Timed
	public ResponseEntity<Result> getAllGoods(Pageable pageable) {
		Page<Goods> page = goodsService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/goods");
		Map<String, Object> re = new HashMap<>();
		re.put("page",page);
		re.put("headers",headers);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);

	}

	/**
	 * 通过ID查询商品详情
	 * @param id
	 * @return
	 */
	@GetMapping("/goodsBy/{id}")
	@Timed
	public ResponseEntity<Result> getGoods(@PathVariable Long id) {
		System.out.println("getGoods==="+id+"=====000");
		Optional<Goods> goods = goodsService.findOne(id);
		Map<String, Object> re = new HashMap<>();
		re.put("goods",goods);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 删除商品
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deletegoods/{id}")
	@Timed
	public ResponseEntity<Result> deleteGoods(@PathVariable Long id) {
		System.out.println("deleteGoods==="+id+"=====000");
		goodsService.delete(id);
		Map<String, Object> re = new HashMap<>();
		re.put("good","删除成功");
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 对商品得模糊搜索
	 * @param query
	 * @param pageable
	 * @return
	 */
	@GetMapping("/_search/goods")
	@Timed
	public ResponseEntity<Result> searchGoods(@RequestParam String query, Pageable pageable) {
		Page<Goods> page = goodsService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/goods");
		Map<String, Object> re = new HashMap<>();
		re.put("headers",headers);
		re.put("page",page);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);

	}

    //***************我的信息************************

	/**
	 * 创建用户
	 * @param userInfo
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/adduser-infos")
	@Timed
	public ResponseEntity<Result> createUserInfo(@Valid @RequestBody UserInfo userInfo) throws URISyntaxException {
		if (userInfo.getId() != null) {
			throw new BadRequestAlertException("A new userInfo canno atlready have an ID", "userInfo", "idexists");
		}
		List<UserInfo> user =userInfoService.FindUserInfoBynickName(userInfo.getNickName());
		System.out.println("===user=="+user.size());
		if(user.size()>1){
		Map<String, Object> re = new HashMap<>();
		re.put("massage","用户昵称重复，请重试");
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
		}
		userInfo.setGoodsSourceCount(0);
		userInfo.setIntegral(0);
		userInfo.setRegisterDate(Instant.now());
		userInfo.setLastLoginedDate(Instant.now());
		UserInfo result = userInfoService.save(userInfo);

		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 * @throws URISyntaxException
	 */
	@PutMapping("/Upadateuser-infos")
	@Timed
	public  ResponseEntity<Result> updateUserInfo(@Valid @RequestBody UserInfo userInfo) throws URISyntaxException {
		if (userInfo.getId() == null) {
			throw new BadRequestAlertException("Invalid id", "userInfo", "idnull");
		}
		UserInfo result = userInfoService.save(userInfo);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	/**
	 * 得到全部的用户信息
	 * @param pageable
	 * @return
	 */
	@GetMapping("/Alluser-infos")
	@Timed
	public ResponseEntity<Result> getAllUserInfos(Pageable pageable) {
		Page<UserInfo> page = userInfoService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-infos");
		Map<String, Object> re = new HashMap<>();
		re.put("page",page);
		re.put("headers",headers);
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 得到用户详细信息
	 * @param id
	 * @return
	 */
	@GetMapping("/user-infos/{id}")
	@Timed
	public ResponseEntity<Result> getUserInfo(@PathVariable Long id) {
		Optional<UserInfo> userInfo = userInfoService.findOne(id);
		Map<String, Object> re = new HashMap<>();
		re.put("userInfo",userInfo);
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteuser-infos/{id}")
	@Timed
	public  ResponseEntity<Result> deleteUserInfo(@PathVariable Long id) {
		userInfoService.delete(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	/**
	 * 搜索用户
	 * @param query
	 * @param pageable
	 * @return
	 */
	@GetMapping("/_search/user-infos")
	@Timed
	public ResponseEntity<Result> searchUserInfos(@RequestParam String query, Pageable pageable) {
		Page<UserInfo> page = userInfoService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/user-infos");
		Map<String, Object> re = new HashMap<>();
		re.put("headers",headers);
		re.put("page",page);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	//修改昵称；
	@PostMapping("/UpadateUserInfoByPhoto/{id}")
	@Timed
	public  ResponseEntity<Result> UpadateUserInfoBynickName(@PathVariable Long id,String nickName) {
		System.out.println("==id==="+id+"========");
		System.out.println("==nickName==="+nickName+"========");
		List<UserInfo> user =userInfoService.FindUserInfoBynickName(nickName);
		if(user.size()>1){
			Map<String, Object> map = new HashMap<>();
			map.put("massage","用户昵称重复，请重试");
			return  new ResponseEntity<>(ResultGenerator.genSuccessResult(map), HttpStatus.OK);
		}
		Optional<UserInfo> userInfo = userInfoService.findOne(id);
		userInfo.get().setNickName(nickName);
		UserInfo result = userInfoService.save(userInfo.get());
		Map<String, Object> re=new HashMap<>();
		re.put("result",result);
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	//修改头像
	@PutMapping("/UpadateUserInfoByphotoLogo/{id}")
	@Timed
	public  ResponseEntity<Result> UpadateUserInfoByphotoLogo(@PathVariable Long id,String Photo) {
		System.out.println("==id==="+Photo+"========");
		System.out.println("==nickName==="+Photo+"========");
		Optional<UserInfo> userInfo = userInfoService.findOne(id);
		userInfo.get().setPhoto(Photo);
		UserInfo result = userInfoService.save(userInfo.get());
		Map<String, Object> re=new HashMap<>();
		re.put("result",result);
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

    /**
     * 上传用户头像
     * @param file
     * @param request
     * @return
     */
    @PostMapping("filesUpload/{id}")
    public  ResponseEntity<Result> filesUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,Long id) {
	    System.out.println("==files===="+file);
        System.out.println("==request===="+request);
        System.out.println("==id===="+id);
        List<String> list = new ArrayList<String>();
        //System.out.println("==length=="+files.length+"=======");
		System.out.println("==files=="+file == null+"=======");
        if (file != null ) {
            //for (int i = 0; i < files.length; i++) {
                //MultipartFile file = files[i];
                // 保存文件
                list = saveFile(request, file, list,id);
                System.out.println("===list=="+list.size());
            }
       // }
        //写着测试，删了就可以
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
        }
        Map<String, Object> re=new HashMap<>();
        re.put("result",list);
        return  new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
    }

    private List<String> saveFile(HttpServletRequest request, MultipartFile file, List<String> list,Long id) {
    	System.out.println("==进入了saveFile中===");
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                // )
                String filePath = request.getSession().getServletContext()
                        .getRealPath("/")
                        + "opt/wl-prod/data/upload/UserPhoto/" + file.getOriginalFilename();
                System.out.println("==filePath==="+filePath+"====");
                list.add(file.getOriginalFilename());
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();

                // 转存文件
                file.transferTo(saveDir);
                //把图片地址存入用户头像中
                Optional<UserInfo> userInfo = userInfoService.findOne(id);
                userInfo.get().setPhoto(filePath);
                UserInfo result = userInfoService.save(userInfo.get());
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return list;
    }

	/*@CrossOrigin
	@PostMapping("/upload")
	public ResponseEntity<Result> upload(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("1233223");
		String originalFilename = file.getOriginalFilename();
		String url = storageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);
		Map<String, Object> data = new HashMap<>();
		data.put("url", url);
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(data), HttpStatus.OK);
	}
*/





	//**********积分模块**************************************
    //积分变化记录

	/**
	 * 积分变化记录（积分增加）
	 * @param integralChangeRecord
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/Addintegral-change-records")
	@Timed
	public ResponseEntity<Result> createIntegralChangeRecord(@Valid @RequestBody IntegralChangeRecord integralChangeRecord) throws URISyntaxException {
		System.out.println("用户ID"+integralChangeRecord.getUserInfo());
		if (integralChangeRecord.getId() != null) {
			throw new BadRequestAlertException("A new integralChangeRecord cannot already have an ID", "integralChangeRecord", "idexists");
		}
		//先添加进去记录在修改积分，顺序可能会有印象
		IntegralChangeRecord result = integralChangeRecordService.save(integralChangeRecord);
		Optional<UserInfo> userInfo= userInfoService.findOne(integralChangeRecord.getUserInfo().getId());
		//把这个字段value作为传值对象
		Integer i=userInfo.get().getIntegral();
		System.out.println("积分的增加==i==="+i+"==========");
		Integer k=integralChangeRecord.getValue();
		System.out.println("积分的增加===k=="+k+"==========");
		Integer j=i+k;
		System.out.println("积分的增加===j=="+j+"==========");
		userInfo.get().setIntegral(j);

		//把修改后的值添加进去
		userInfoService.save(userInfo.get());
        //integralChangeRecord.setValue(userInfo.get().getIntegral());
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	/**
	 * 积分变化记录（积分减少）
	 * @param integralChangeRecord
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/reduceintegral-change-records")
	@Timed
	public ResponseEntity<Result> ReduceIntegralChangeRecord(@Valid @RequestBody IntegralChangeRecord integralChangeRecord) throws URISyntaxException {
		if (integralChangeRecord.getId() != null) {
			throw new BadRequestAlertException("A new integralChangeRecord cannot already have an ID", "integralChangeRecord", "idexists");
		}
		//先添加在修改
		System.out.println("用户ID"+integralChangeRecord.getUserInfo().getId());
		IntegralChangeRecord result = integralChangeRecordService.save(integralChangeRecord);
		Optional<UserInfo> userInfo= userInfoService.findOne(integralChangeRecord.getUserInfo().getId());
		//把这个字段value作为传值对象
		Integer i=userInfo.get().getIntegral();
		System.out.println("积分的减少====="+i+"==========");
		Integer k=integralChangeRecord.getValue();
		Integer j=i-k;
		if(j<0){
		  String hi="您的积分不足以兑换";
          Map<String,Object> map=new HashMap<>();
          map.put("hi",hi);
		  return  new ResponseEntity<>(ResultGenerator.genSuccessResult(map), HttpStatus.OK);
		}else {
			userInfo.get().setIntegral(j);
			//把修改后的值添加进去
			userInfoService.save(userInfo.get());
		}
		integralChangeRecord.setValue(userInfo.get().getIntegral());
		return  new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}


	/**
	 * 修改积分记录
	 * @param integralChangeRecord
	 * @return
	 * @throws URISyntaxException
	 */
	@PutMapping("/Upadateintegral-change-records")
	@Timed
	public  ResponseEntity<Result> updateIntegralChangeRecord(@Valid @RequestBody IntegralChangeRecord integralChangeRecord) throws URISyntaxException {

		if (integralChangeRecord.getId() == null) {
			throw new BadRequestAlertException("Invalid id", "integralChangeRecord", "idnull");
		}
		IntegralChangeRecord result = integralChangeRecordService.save(integralChangeRecord);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	/**
	 * 得到全部的积分
	 * @param pageable
	 * @return
	 */
	@GetMapping("/integral-change-records")
	@Timed
	public ResponseEntity<Result> getAllIntegralChangeRecords(Pageable pageable) {
		Page<IntegralChangeRecord> page = integralChangeRecordService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/integral-change-records");
		Map<String, Object> re = new HashMap<>();
		re.put("headers",headers);
		re.put("page",page);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 通过ID得到积分详情
	 * @param id
	 * @return
	 */
	@GetMapping("/integral-change-records/{id}")
	@Timed
	public  ResponseEntity<Result> getIntegralChangeRecord(@PathVariable Long id) {

		Optional<IntegralChangeRecord> integralChangeRecord = integralChangeRecordService.findOne(id);
		Map<String, Object> re = new HashMap<>();
		re.put("integralChangeRecord",integralChangeRecord);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 清空积分
	 * @param id
	 * @return
	 */
	@DeleteMapping("/Deleteintegral-change-records/{id}")
	@Timed
	public  ResponseEntity<Result> deleteIntegralChangeRecord(@PathVariable Long id) {
		integralChangeRecordService.delete(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}


   //积分规则
	/**
	 * 增加积分规则
	 * @param integralRule
	 * @return
	 * @throws URISyntaxException
	 */
   @PostMapping("/Addintegral-rules")
   @Timed
   public ResponseEntity<Result> createIntegralRule(@Valid @RequestBody IntegralRule integralRule) throws URISyntaxException {

	   if (integralRule.getId() != null) {
		   throw new BadRequestAlertException("A new integralRule cannot already have an ID", "integralRule", "idexists");
	   }
	   IntegralRule result = integralRuleService.save(integralRule);
	   Map<String, Object> re = new HashMap<>();
	   re.put("result",result);
	   return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);

   }

	/**
	 * 修改积分规则
	 * @param integralRule
	 * @return
	 * @throws URISyntaxException
	 */
	@PutMapping("/Upadateintegral-rules")
	@Timed
	public ResponseEntity<Result> updateIntegralRule(@Valid @RequestBody IntegralRule integralRule) throws URISyntaxException {

		if (integralRule.getId() == null) {
			throw new BadRequestAlertException("Invalid id", "integralRule", "idnull");
		}
		IntegralRule result = integralRuleService.save(integralRule);
		Map<String, Object> re = new HashMap<>();
		re.put("result",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);

	}

	/**
	 * 得到全部的积分规则
	 * @param pageable
	 * @return
	 */
	@GetMapping("/Allintegral-rules")
	@Timed
	public ResponseEntity<Result> getAllIntegralRules(Pageable pageable) {
		Page<IntegralRule> page = integralRuleService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/integral-rules");
		Map<String, Object> re = new HashMap<>();
		re.put("page",page);
		re.put("headers",headers);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 通过id得到积分规则详情
	 * @param id
	 * @return
	 */
	@GetMapping("/integral-rules/{id}")
	@Timed
	public ResponseEntity<Result> getIntegralRule(@PathVariable Long id) {

		Optional<IntegralRule> integralRule = integralRuleService.findOne(id);
		Map<String, Object> re = new HashMap<>();
		re.put("integralRule",integralRule);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 删除积分规则
	 * @param id
	 * @return
	 */
	@DeleteMapping("/Deleteintegral-rules/{id}")
	@Timed
	public ResponseEntity<Result> deleteIntegralRule(@PathVariable Long id) {
		integralRuleService.delete(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	/**
	 * 搜索积分规则
	 * @param query
	 * @param pageable
	 * @return
	 */
	@GetMapping("/_search/integral-rules")
	@Timed
	public ResponseEntity<Result> searchIntegralRules(@RequestParam String query, Pageable pageable) {
		Page<IntegralRule> page = integralRuleService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/integral-rules");
		Map<String, Object> re = new HashMap<>();
		re.put("page",page);
		re.put("headers",headers);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}


	@PostMapping("/uploadFace")
	@Timed
	public ResponseEntity<Result> uploadFace(@RequestParam("file") MultipartFile file) {

		Map<String, Object> result = new HashMap<>();

		String path = storageService.store("face",file);
		if(!path.equals("error")) {
			result.put("path", path);
		}else {
			result.put("path", "文件错误");
		}
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	@PostMapping("/uploadTopic")
	@Timed
	public ResponseEntity<Result> uploadTopic(@RequestParam("file") MultipartFile[] file) {

		Map<String, Object> result = new HashMap<>();
        List<String> paths=new ArrayList<>();
		for(int i=0;i<file.length;i++){
            String path = storageService.store("topic",file[i]);
            if(!path.equals("error")) {
                paths.add(path);
            }else {
                paths.add("文件错误");
            }
        }
        result.put("paths",paths);

		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}


	@PostMapping("/area/import")
	@ResponseBody
	public ResponseEntity<Boolean> areaImport(@RequestParam("file") MultipartFile file) {
		Boolean isSuccess = null;

		String path = storageService.store("area",file);
		if (!path.equals("error")) {
			isSuccess = areaService.ImportArea(path);
		}
		return ResponseUtil.wrapOrNotFound(Optional.of(isSuccess));
	}

	@GetMapping("/version/get")
	@Timed
	public Version get() {
		return versionService.get();
	}

	@GetMapping("/version/save")
	@Timed
	public Version save() {
		return versionService.save();
	}

	@PostMapping("/version/upload")
	@Timed
	public String upload(@RequestParam("file") MultipartFile file) {

		Map<String, Object> result = new HashMap<>();

		String path = storageService.store("version",file);
		if(!path.equals("error")) {
			return path;
		}else {
			return "文件错误";
		}

	}

	@PostMapping("/version/set")
	@Timed
	public String set(@RequestParam("number") String number,@RequestParam("path") String path,@RequestParam("info") String info) {
		versionService.set(number,path,info);
		return "成功";
	}

	@GetMapping("/version/get2")
	@Timed
	public Version get2() {
		return versionService.get2();
	}

	@GetMapping("/version/save2")
	@Timed
	public Version save2() {
		return versionService.save2();
	}

	@PostMapping("/version/upload2")
	@Timed
	public String upload2(@RequestParam("file") MultipartFile file) {

		Map<String, Object> result = new HashMap<>();

		String path = storageService.store("version",file);
		if(!path.equals("error")) {
			return path;
		}else {
			return "文件错误";
		}

	}

	@PostMapping("/version/set2")
	@Timed
	public String set2(@RequestParam("number") String number,@RequestParam("path") String path,@RequestParam("info") String info) {
		versionService.set2(number,path,info);
		return "成功";
	}

	@GetMapping("/statistics/userToday")
	@Timed
	public int userToday() {
		return statisticsService.userToday();
	}

	@GetMapping("/statistics/userAll")
	@Timed
	public int userAll() {
		return statisticsService.userAll();
	}

	@GetMapping("/statistics/goodsToday")
	@Timed
	public int goodsToday() {
		return statisticsService.goodsToday();
	}

	@GetMapping("/statistics/goodsAll")
	@Timed
	public int goodsAll() {
		return statisticsService.goodsAll();
	}

	@GetMapping("/statistics/memberToday")
	@Timed
	public int memberToday() {
		return statisticsService.memberToday();
	}

	@GetMapping("/statistics/memberAll")
	@Timed
	public int memberAll() {
		return statisticsService.memberAll();
	}

	@GetMapping("/statistics/topicToday")
	@Timed
	public int topicToday() {
		return statisticsService.topicToday();
	}

	@GetMapping("/statistics/topicAll")
	@Timed
	public int topicAll() {
		return statisticsService.topicAll();
	}

	@GetMapping("/statistics/commentToday")
	@Timed
	public int commentToday() {
		return statisticsService.commentToday();
	}

	@GetMapping("/statistics/commentAll")
	@Timed
	public int commentAll() {
		return statisticsService.commentAll();
	}

	@GetMapping("/statistics/findStartCitys")
	@Timed
	public List<String> findStartCitys() {
		return statisticsService.findStartCitys();
	}

	@PostMapping("/statistics/ddn")
	@Timed
	public int ddn(@RequestParam("start") String start) {
		return statisticsService.ddn(start);
	}

	@GetMapping("/getOpenIdAndSessionKey")
	@Timed
	public OpenIdAndSessionKeyResponse getOpenIdAndSessionKey(String jsCode) throws CannotGetOpenIdAndSessionKeyException {
		return logisticsDdnService.getOpenIdAndSessionKey(jsCode);
	}

	@GetMapping("/getAppQualification")
	@Timed
	public boolean getAppQualification() {
		return iosQualificationBlService.getIOSQualification();
	}


	@PostMapping("/claim")
	@Timed
	public ResponseEntity<Result> claim(@RequestParam(required = true) String mobilePhone,@RequestParam(required = true) String userMobilePhone,@RequestParam(required = true) String vcode){
		Map<String, Object> result = new HashMap<>();
//		Optional<User> existingUser = userRepository.findOneByLogin(userVM.getMobilePhone());
//		if (!existingUser.isPresent()) {
//			result.put("result", false);
//			result.put("msg", "手机号码未注册使用!");
//			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
//		}
		boolean isSuccess = Sms.me().checkVerificationCode(mobilePhone, vcode);
		if (isSuccess) {
			String ddnId="";
            Optional<UserInfo> optionalUserInfo=userInfoService.findOneByMobilePhone(userMobilePhone);
            if(optionalUserInfo.isPresent()){
            	UserInfo userInfo=optionalUserInfo.get();
            	LogisticsDdn logisticsDdn=logisticsDdnService.findAllByPhoneNumber(mobilePhone);
            	ddnId=String.valueOf(logisticsDdn.getId());
            	userInfo.setRegisterSum(ddnId);
            	userInfoService.save(userInfo);
			}
			result.put("ddnId",ddnId);
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.FAIL,"验证码超时或者错误，请重试！"), HttpStatus.OK);
		}
	}

	@PostMapping("/uploadUpdate")
	@Timed
	public ResponseEntity<Result> uploadUpdate(@RequestParam("file") MultipartFile[] file) {

		Map<String, Object> result = new HashMap<>();
		List<String> paths=new ArrayList<>();
		for(int i=0;i<file.length;i++){
			String path = storageService.store("update",file[i]);
			if(!path.equals("error")) {
				paths.add(path);
			}else {
				paths.add("文件错误");
			}
		}
		result.put("paths",paths);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	@PostMapping("/update/save")
	@Timed
	public ResponseEntity<Result> saveUpdate(@Valid @RequestBody DdnUpdate ddnUpdate) {

		Map<String, Object> result = new HashMap<>();
		ddnUpdate.setStatus(Status.ENABLE);
		ddnUpdate.setId(null);
        ddnUpdateService.save(ddnUpdate);
        result.put("ddnUpdate",ddnUpdate);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	@PostMapping("/update/update")
	@Timed
	public ResponseEntity<Result> updateUpdate(@Valid @RequestBody DdnUpdate ddnUpdate) {

		Map<String, Object> result = new HashMap<>();
		ddnUpdateService.update(ddnUpdate);
		result.put("ddnUpdate",ddnUpdate);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	@PostMapping("/update/delete")
	@Timed
	public ResponseEntity<Result> deleteUpdate(@RequestParam("id") Long id) {

		ddnUpdateService.delete(id);

		return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
	}

	@PostMapping("/update/findOne")
	@Timed
	public ResponseEntity<Result> findOneUpdate(@RequestParam("id") Long id) {
		Map<String, Object> result = new HashMap<>();
		DdnUpdate ddnUpdate=ddnUpdateService.findOne(id).get();
		result.put("ddnUpdate",ddnUpdate);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	@PostMapping("/update/findAll")
	@Timed
	public ResponseEntity<Result> findAll(Pageable pageable) {
		Map<String, Object> result = new HashMap<>();
		Page<DdnUpdate> ddnUpdates=ddnUpdateService.findAll(pageable);
		result.put("ddnUpdates",ddnUpdates);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	@PostMapping("/update/find")
	@Timed
	public ResponseEntity<Result> findUpdate(@RequestParam("query")String query, Pageable pageable) {
		Map<String, Object> result = new HashMap<>();
		Page<DdnUpdate> ddnUpdates=ddnUpdateService.find(query,pageable);
		result.put("ddnUpdates",ddnUpdates);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	@GetMapping("/browse/get")
	@Timed
	public ResponseEntity<Result> getBrowse(@RequestParam("ddnid") Long ddnid) {
		Map<String, Object> result = new HashMap<>();
		int number=browseService.get(ddnid);
		result.put("number",number);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	}

	@PostMapping("/browse/add")
	@Timed
	public ResponseEntity<Result> addBrowse(@RequestParam("ddnid") Long ddnid) {
		browseService.add(ddnid);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
	}
}
