package com.wl.app.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.wl.app.domain.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.wl.app.domain.Area;
import com.wl.app.domain.LogisticsDdn;
import com.wl.app.domain.User;
import com.wl.app.domain.enumeration.Status;
import com.wl.app.module.aliyun.Sms;
import com.wl.app.repository.AreaRepository;
import com.wl.app.repository.UserRepository;
import com.wl.app.service.LogisticsDdnPicService;
import com.wl.app.service.LogisticsDdnService;
import com.wl.app.service.LogisticsDdnWWWService;
import com.wl.app.service.UserService;
import com.wl.app.service.dto.DdnBannerDTO;
import com.wl.app.service.dto.DdnDTO;
import com.wl.app.service.dto.ListActivityDTO;
import com.wl.app.service.dto.PreferentialActivitiesDTO;
import com.wl.app.service.dto.SearchAndFavoritesDdnAndBannerDTO;
import com.wl.app.service.dto.SearchAndFavoritesDdnDTO;
import com.wl.app.service.dto.StartCityDTO;
import com.wl.app.web.rest.errors.EmailAlreadyUsedException;
import com.wl.app.web.rest.errors.InvalidPasswordException;
import com.wl.app.web.rest.errors.LoginAlreadyUsedException;
import com.wl.app.web.rest.errors.Result;
import com.wl.app.web.rest.errors.ResultGenerator;
import com.wl.app.web.rest.vm.ManagedUserVM;
import com.wl.app.web.rest.vm.RUserVM;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api/app-static")
public class AppResource {

	private final UserRepository userRepository;
	private final UserService userService;
	private final LogisticsDdnService logisticsDdnService;
	private final LogisticsDdnWWWService logisticsDdnWWWService;
	private final LogisticsDdnPicService logisticsDdnPicService;
	private final AreaRepository areaRepository;

	public AppResource(UserRepository userRepository, UserService userService, LogisticsDdnService logisticsDdnService,
			LogisticsDdnWWWService logisticsDdnWWWService, LogisticsDdnPicService logisticsDdnPicService,AreaRepository areaRepository) {
		super();
		this.userRepository = userRepository;
		this.userService = userService;
		this.logisticsDdnService = logisticsDdnService;
		this.logisticsDdnWWWService = logisticsDdnWWWService;
		this.logisticsDdnPicService = logisticsDdnPicService;
		this.areaRepository = areaRepository;
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
        Page<LogisticsDdn> page = logisticsDdnService.findAll(startLine,endLine,pageable);
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
			result.put("result", true);
			result.put("msg", "短信验证码发送成功!");
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
		}else {
			result.put("result", false);
			result.put("msg", "短信验证码发送失败!");
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
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
	public ResponseEntity<Result> login(@Valid @RequestBody RUserVM userVM){
		Map<String, Object> result = new HashMap<>();
//		Optional<User> existingUser = userRepository.findOneByLogin(userVM.getMobilePhone());
//		if (!existingUser.isPresent()) {
//			result.put("result", false);
//			result.put("msg", "手机号码未注册使用!");
//			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
//		}
		System.out.println("login");
		boolean isSuccess = Sms.me().checkVerificationCode(userVM.getMobilePhone(), userVM.getVcode());
		if (isSuccess) {
			result.put("result", true);
			result.put("msg", "登陆成功");
			UserInfo userInfo=new UserInfo();
			userInfo.setMobilePhone(userVM.getMobilePhone());
            result.put("userInfo",userInfo);
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
		}else {
			result.put("result", false);
			result.put("msg", "验证码超时或者错误，请重试！");
			return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
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
	public ResponseEntity<Result> sendContact(@RequestParam(required = true) String mobilePhone) {
		Map<String, Object> result = new HashMap<>();
		// 校验手机号是否已经注册
		 Optional<User> existingUser = userRepository.findOneByLogin(mobilePhone);
	        if (!existingUser.isPresent()) {
	            result.put("result", false);
				result.put("msg", "手机号码不存在!");
				return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
	        }
		String code = Sms.me().sendContact(mobilePhone);
		if (code.equals(Sms.SEND_SUCCESS)) {
			result.put("result", true);
			result.put("msg", "短信发送成功!");
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
}
