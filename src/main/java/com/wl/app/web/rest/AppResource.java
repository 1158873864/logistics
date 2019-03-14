package com.wl.app.web.rest;

import java.net.URISyntaxException;
import java.time.Instant;
import java.util.*;

import javax.validation.Valid;

import com.wl.app.domain.*;
import com.wl.app.domain.enumeration.GoodsSourceProperty;
import com.wl.app.service.*;
import com.wl.app.web.rest.errors.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.enumeration.Status;
import com.wl.app.module.aliyun.Sms;
import com.wl.app.repository.AreaRepository;
import com.wl.app.repository.UserRepository;
import com.wl.app.service.dto.DdnBannerDTO;
import com.wl.app.service.dto.DdnDTO;
import com.wl.app.service.dto.ListActivityDTO;
import com.wl.app.service.dto.PreferentialActivitiesDTO;
import com.wl.app.service.dto.SearchAndFavoritesDdnAndBannerDTO;
import com.wl.app.service.dto.SearchAndFavoritesDdnDTO;
import com.wl.app.service.dto.StartCityDTO;
import com.wl.app.web.rest.vm.ManagedUserVM;
import com.wl.app.web.rest.vm.RUserVM;

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


	public AppResource(UserRepository userRepository, UserService userService, LogisticsDdnService logisticsDdnService,
			LogisticsDdnWWWService logisticsDdnWWWService, LogisticsDdnPicService logisticsDdnPicService,AreaRepository areaRepository,
					   UserInfoService userInfoService,UserDdnFavoritesService userDdnFavoritesService,GoodsSourceService goodsSourceService,
					   TopicService topicService,TopicCommentService topicCommentService,TopicFabulousService topicFabulousService) {
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
	@GetMapping("/latest-search-and-favorites-ddns-and-banner")
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
			User user = userService.registerUser(userVM,"");
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
            	userInfo.setFullname("");
            	userInfo.setGoodsSourceCount(0);
            	userInfo.setIntegral(0);
            	userInfo.setLastLoginedDate(Instant.now());
            	userInfo.setNickName("");
            	userInfo.setOpenId("");
            	userInfo.setPhoto("");
            	userInfo.setRegisterDate(Instant.now());
            	userInfo.setStatus(Status.ENABLE);
            	userInfo.setRegisterSum("");
                userInfo.setMobilePhone(mobilePhone);
                RUserVM userVM=new RUserVM();
                userVM.setMobilePhone(mobilePhone);
                userVM.setVcode(vcode);
				userService.registerUser(userVM,"123456");
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
	public ResponseEntity<Result> allSource(Pageable pageable){
		Page<GoodsSource> page = goodsSourceService.findAll(pageable);
		Map<String, Object> re = new HashMap<>();
		re.put("goodsSourcePage",page.getContent());
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 根据开始、结束路线和货源类型获取货源列表
	 * @return
	 */
	@GetMapping("/lineSource")
	@Timed
	public ResponseEntity<Result> lineSource(String size,String page,@RequestParam(required = false) String start,@RequestParam(required = false) String end,@RequestParam(required = false) String goodsSourceProperty){
		int pageInt = 0;//默认页数0
		int sizeInt = 10;//默认每页条数10条

		if (!StringUtils.isBlank(page) && StringUtils.isNumeric(page)) {//页数不为空
			pageInt = Integer.parseInt(page);
		}
		if (!StringUtils.isBlank(size) && StringUtils.isNumeric(size)) {//每页条数不为空
			sizeInt = Integer.parseInt(size);
		}
		Pageable pageable = of(pageInt, sizeInt);
		GoodsSource goodsSource=new GoodsSource();
		goodsSource.setStart(start);
		goodsSource.setEnd(end);
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
		re.put("goodsSourcePage",goodsSourcePage);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 根据货源id查看用户某条货源详情
	 * @return
	 */
	@GetMapping("/goods-sources/{id}")
	@Timed
	public ResponseEntity<Result> getGoodsSource(@PathVariable Long id) {
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
	@DeleteMapping("/goods-sources/{id}")
	@Timed
	public ResponseEntity deleteGoodsSource(@PathVariable Long id) {
		goodsSourceService.delete(id);
		return new ResponseEntity<>( HttpStatus.OK);
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
		GoodsSource result = goodsSourceService.save(goodsSource);
		Map<String, Object> re = new HashMap<>();
		re.put("GoodsSource",result);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}
	/**
	 * 获取物流圈列表
	 * @param
	 * @return
	 */
	@GetMapping("/topics")
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
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 获取当前话题的评论
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
		Map<String, Object> re = new HashMap<>();
		re.put("TopicComment",pages);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 添加话题评论
	 * @param topicComment
	 * @return
	 */
	@PostMapping("/topicsComment")
	@Timed
	public ResponseEntity<Result> createTopicComment(@Valid @RequestBody TopicComment topicComment){

		if (topicComment.getId() != null) {
			throw new BadRequestAlertException("Invalid id", "TopicComment", "idnull");
		}
		//添加评论
		TopicComment topicComments=topicCommentService.save(topicComment);
		Map<String, Object> re = new HashMap<>();
		//修改话题评论+1
		Topic topic=topicComment.getTopic();
		topic.setCommentCount(topic.getCommentCount()+1);
		topicService.save(topic);
		re.put("TopicComment",topicComments);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}

	/**
	 * 删除话题评论
	 * @param id
	 * @return
	 */
	@DeleteMapping("/topicsComment/{id}")
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
	 * 用户点赞
	 * @param
	 * @return
	 */
	@PostMapping("/topicFabulous")
	@Timed
	public ResponseEntity<Result> createTopicFabulous(@PathVariable Long id,@PathVariable Long userid){
		if (id == null) {
			throw new BadRequestAlertException("Invalid id", "topic", "idnull");
		}
		Topic topic=topicService.findOne(id).get();
		UserInfo userInfo=new UserInfo();
		userInfo.setId(userid);
		TopicFabulous newtopicFabulous=topicFabulousService.findTopicFabulousByUserInfoAndTopic(userInfo,topic);
		boolean message=true;
		if(newtopicFabulous==null){
			//此用户没有点赞
			//添加赞
			TopicFabulous topicFabulous=new TopicFabulous();
			topicFabulous.setTopic(topic);
			topicFabulous.setUserInfo(userInfo);
			topicFabulousService.save(topicFabulous);
			//修改赞总数+1
			topic.setFabulousCount(topic.getForwardCount()+1);
		}else{
			message=false;
			//此用户已点赞
			//删除赞
			topicFabulousService.delete(newtopicFabulous.getId());
			topic.setForwardCount(topic.getFabulousCount()-1);
		}
		topicService.save(topic);
		Map<String, Object> re = new HashMap<>();
		re.put("message",message?"添加成功":"取消成功");
		re.put("code",message);
		return new ResponseEntity<>(ResultGenerator.genSuccessResult(re), HttpStatus.OK);
	}
}
