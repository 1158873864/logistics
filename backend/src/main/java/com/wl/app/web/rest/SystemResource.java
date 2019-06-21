package com.wl.app.web.rest;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wl.app.domain.Goods;
import com.wl.app.domain.GoodsExchange;
import com.wl.app.domain.GoodsSource;
import com.wl.app.domain.IntegralChangeRecord;
import com.wl.app.domain.IntegralRule;
import com.wl.app.domain.LogisticsDdn;
import com.wl.app.domain.LogisticsDdnPic;
import com.wl.app.domain.LogisticsDdnWWW;
import com.wl.app.domain.PreferentialActivities;
import com.wl.app.domain.SysRecruitmentInformation;
import com.wl.app.domain.Topic;
import com.wl.app.domain.TopicComment;
import com.wl.app.domain.TopicFabulous;
import com.wl.app.domain.TopicForward;
import com.wl.app.domain.TopicViewed;
import com.wl.app.domain.UserDdnFavorites;
import com.wl.app.domain.UserInfo;
import com.wl.app.repository.GoodsExchangeRepository;
import com.wl.app.repository.GoodsRepository;
import com.wl.app.repository.GoodsSourceRepository;
import com.wl.app.repository.IntegralChangeRecordRepository;
import com.wl.app.repository.IntegralRuleRepository;
import com.wl.app.repository.LogisticsDdnPicRepository;
import com.wl.app.repository.LogisticsDdnRepository;
import com.wl.app.repository.LogisticsDdnWWWRepository;
import com.wl.app.repository.PreferentialActivitiesRepository;
import com.wl.app.repository.SysRecruitmentInformationRepository;
import com.wl.app.repository.TopicCommentRepository;
import com.wl.app.repository.TopicFabulousRepository;
import com.wl.app.repository.TopicForwardRepository;
import com.wl.app.repository.TopicRepository;
import com.wl.app.repository.TopicViewedRepository;
import com.wl.app.repository.UserDdnFavoritesRepository;
import com.wl.app.repository.UserInfoRepository;
import com.wl.app.repository.search.GoodsExchangeSearchRepository;
import com.wl.app.repository.search.GoodsSearchRepository;
import com.wl.app.repository.search.GoodsSourceSearchRepository;
import com.wl.app.repository.search.IntegralChangeRecordSearchRepository;
import com.wl.app.repository.search.IntegralRuleSearchRepository;
import com.wl.app.repository.search.LogisticsDdnPicSearchRepository;
import com.wl.app.repository.search.LogisticsDdnSearchRepository;
import com.wl.app.repository.search.LogisticsDdnWWWSearchRepository;
import com.wl.app.repository.search.PreferentialActivitiesSearchRepository;
import com.wl.app.repository.search.SysRecruitmentInformationSearchRepository;
import com.wl.app.repository.search.TopicCommentSearchRepository;
import com.wl.app.repository.search.TopicFabulousSearchRepository;
import com.wl.app.repository.search.TopicForwardSearchRepository;
import com.wl.app.repository.search.TopicSearchRepository;
import com.wl.app.repository.search.TopicViewedSearchRepository;
import com.wl.app.repository.search.UserDdnFavoritesSearchRepository;
import com.wl.app.repository.search.UserInfoSearchRepository;
import com.wl.app.security.AuthoritiesConstants;

@RestController
@RequestMapping("/api")
public class SystemResource {

	private final UserInfoRepository userInfoRepository;
	private final UserInfoSearchRepository userInfoSearchRepository;
	private final GoodsSourceRepository goodsSourceRepository;
	private final GoodsSourceSearchRepository goodsSourceSearchRepository;
	private final LogisticsDdnPicRepository logisticsDdnPicRepository;
	private final LogisticsDdnPicSearchRepository logisticsDdnPicSearchRepository;
	private final LogisticsDdnRepository logisticsDdnRepository;
	private final LogisticsDdnSearchRepository logisticsDdnSearchRepository;
	private final LogisticsDdnWWWRepository logisticsDdnWWWRepository;
	private final LogisticsDdnWWWSearchRepository logisticsDdnWWWSearchRepository;
	private final IntegralRuleRepository integralRuleRepository;
	private final IntegralRuleSearchRepository integralRuleSearchRepository;
	private final IntegralChangeRecordRepository integralChangeRecordRepository;
	private final IntegralChangeRecordSearchRepository integralChangeRecordSearchRepository;
	private final GoodsRepository goodsRepository;
	private final GoodsSearchRepository goodsSearchRepository;
	private final GoodsExchangeRepository goodsExchangeRepository;
	private final GoodsExchangeSearchRepository goodsExchangeSearchRepository;
	private final UserDdnFavoritesRepository userDdnFavoritesRepository;
	private final UserDdnFavoritesSearchRepository userDdnFavoritesSearchRepository;
	private final PreferentialActivitiesRepository preferentialActivitiesRepository;
	private final PreferentialActivitiesSearchRepository preferentialActivitiesSearchRepository;
	private final SysRecruitmentInformationRepository sysRecruitmentInformationRepository;
	private final SysRecruitmentInformationSearchRepository sysRecruitmentInformationSearchRepository;
	private final TopicRepository topicRepository;
	private final TopicSearchRepository topicSearchRepository;
	private final TopicFabulousRepository topicFabulousRepository;
	private final TopicFabulousSearchRepository topicFabulousSearchRepository;
	private final TopicCommentRepository topicCommentRepository;
	private final TopicCommentSearchRepository topicCommentSearchRepository;
	private final TopicForwardRepository topicForwardRepository;
	private final TopicForwardSearchRepository topicForwardSearchRepository;
	private final TopicViewedRepository topicViewedRepository;
	private final TopicViewedSearchRepository topicViewedSearchRepository;

	public SystemResource(UserInfoRepository userInfoRepository, UserInfoSearchRepository userInfoSearchRepository,
			GoodsSourceRepository goodsSourceRepository, GoodsSourceSearchRepository goodsSourceSearchRepository,
			LogisticsDdnPicRepository logisticsDdnPicRepository,
			LogisticsDdnPicSearchRepository logisticsDdnPicSearchRepository,
			LogisticsDdnRepository logisticsDdnRepository, LogisticsDdnSearchRepository logisticsDdnSearchRepository,
			LogisticsDdnWWWRepository logisticsDdnWWWRepository,
			LogisticsDdnWWWSearchRepository logisticsDdnWWWSearchRepository,
			IntegralRuleRepository integralRuleRepository, IntegralRuleSearchRepository integralRuleSearchRepository,
			IntegralChangeRecordRepository integralChangeRecordRepository,
			IntegralChangeRecordSearchRepository integralChangeRecordSearchRepository, GoodsRepository goodsRepository,
			GoodsSearchRepository goodsSearchRepository, GoodsExchangeRepository goodsExchangeRepository,
			GoodsExchangeSearchRepository goodsExchangeSearchRepository,
			UserDdnFavoritesRepository userDdnFavoritesRepository,
			UserDdnFavoritesSearchRepository userDdnFavoritesSearchRepository,
			PreferentialActivitiesRepository preferentialActivitiesRepository,
			PreferentialActivitiesSearchRepository preferentialActivitiesSearchRepository,
			SysRecruitmentInformationRepository sysRecruitmentInformationRepository,
			SysRecruitmentInformationSearchRepository sysRecruitmentInformationSearchRepository,
			TopicRepository topicRepository, TopicSearchRepository topicSearchRepository,
			TopicFabulousRepository topicFabulousRepository,
			TopicFabulousSearchRepository topicFabulousSearchRepository, TopicCommentRepository topicCommentRepository,
			TopicCommentSearchRepository topicCommentSearchRepository, TopicForwardRepository topicForwardRepository,
			TopicForwardSearchRepository topicForwardSearchRepository, TopicViewedRepository topicViewedRepository,
			TopicViewedSearchRepository topicViewedSearchRepository) {
		this.userInfoRepository = userInfoRepository;
		this.userInfoSearchRepository = userInfoSearchRepository;
		this.goodsSourceRepository = goodsSourceRepository;
		this.goodsSourceSearchRepository = goodsSourceSearchRepository;
		this.logisticsDdnPicRepository = logisticsDdnPicRepository;
		this.logisticsDdnPicSearchRepository = logisticsDdnPicSearchRepository;
		this.logisticsDdnRepository = logisticsDdnRepository;
		this.logisticsDdnSearchRepository = logisticsDdnSearchRepository;
		this.logisticsDdnWWWRepository = logisticsDdnWWWRepository;
		this.logisticsDdnWWWSearchRepository = logisticsDdnWWWSearchRepository;
		this.integralRuleRepository = integralRuleRepository;
		this.integralRuleSearchRepository = integralRuleSearchRepository;
		this.integralChangeRecordRepository = integralChangeRecordRepository;
		this.integralChangeRecordSearchRepository = integralChangeRecordSearchRepository;
		this.goodsRepository = goodsRepository;
		this.goodsSearchRepository = goodsSearchRepository;
		this.goodsExchangeRepository = goodsExchangeRepository;
		this.goodsExchangeSearchRepository = goodsExchangeSearchRepository;
		this.userDdnFavoritesRepository = userDdnFavoritesRepository;
		this.userDdnFavoritesSearchRepository = userDdnFavoritesSearchRepository;
		this.preferentialActivitiesRepository = preferentialActivitiesRepository;
		this.preferentialActivitiesSearchRepository = preferentialActivitiesSearchRepository;
		this.sysRecruitmentInformationRepository = sysRecruitmentInformationRepository;
		this.sysRecruitmentInformationSearchRepository = sysRecruitmentInformationSearchRepository;
		this.topicRepository = topicRepository;
		this.topicSearchRepository = topicSearchRepository;
		this.topicFabulousRepository = topicFabulousRepository;
		this.topicFabulousSearchRepository = topicFabulousSearchRepository;
		this.topicCommentRepository = topicCommentRepository;
		this.topicCommentSearchRepository = topicCommentSearchRepository;
		this.topicForwardRepository = topicForwardRepository;
		this.topicForwardSearchRepository = topicForwardSearchRepository;
		this.topicViewedRepository = topicViewedRepository;
		this.topicViewedSearchRepository = topicViewedSearchRepository;
	}

	@GetMapping(path = "/sysData")
	@Secured(AuthoritiesConstants.ADMIN)
	public String synchronousDataData() {

		System.out.println("-------init data-----------------");
		userInfoSearchRepository.deleteAll();
		List<UserInfo> userInfoList = userInfoRepository.findAll();
		if (userInfoList != null && userInfoList.size() > 0) {
			userInfoSearchRepository.saveAll(userInfoList);
		}
		goodsSourceSearchRepository.deleteAll();
		List<GoodsSource> goodsSourceList = goodsSourceRepository.findAll();
		if (goodsSourceList != null && goodsSourceList.size() > 0) {
			goodsSourceSearchRepository.saveAll(goodsSourceList);
		}
		logisticsDdnPicSearchRepository.deleteAll();
		List<LogisticsDdnPic> logisticsDdnPicList = logisticsDdnPicRepository.findAll();
		if (logisticsDdnPicList != null && logisticsDdnPicList.size() > 0) {
			logisticsDdnPicSearchRepository.saveAll(logisticsDdnPicList);
		}
		logisticsDdnSearchRepository.deleteAll();
		List<LogisticsDdn> logisticsDdnList = logisticsDdnRepository.findAll();
		if (logisticsDdnList != null && logisticsDdnList.size() > 0) {
			logisticsDdnSearchRepository.saveAll(logisticsDdnList);
		}
		logisticsDdnWWWSearchRepository.deleteAll();
		List<LogisticsDdnWWW> logisticsDdnWWWList = logisticsDdnWWWRepository.findAll();
		if (logisticsDdnWWWList != null && logisticsDdnWWWList.size() > 0) {
			logisticsDdnWWWSearchRepository.saveAll(logisticsDdnWWWList);
		}
		integralRuleSearchRepository.deleteAll();
		List<IntegralRule> integralRuleList = integralRuleRepository.findAll();
		if (integralRuleList != null && integralRuleList.size() > 0) {
			integralRuleSearchRepository.saveAll(integralRuleList);
		}
		integralChangeRecordSearchRepository.deleteAll();
		List<IntegralChangeRecord> integralChangeRecordList = integralChangeRecordRepository.findAll();
		if (integralChangeRecordList != null && integralChangeRecordList.size() > 0) {
			integralChangeRecordSearchRepository.saveAll(integralChangeRecordList);
		}
		goodsSearchRepository.deleteAll();
		List<Goods> goodsList = goodsRepository.findAll();
		if (goodsList != null && goodsList.size() > 0) {
			goodsSearchRepository.saveAll(goodsList);
		}
		goodsExchangeSearchRepository.deleteAll();
		List<GoodsExchange> goodsExchangeList = goodsExchangeRepository.findAll();
		if (goodsExchangeList != null && goodsExchangeList.size() > 0) {
			goodsExchangeSearchRepository.saveAll(goodsExchangeList);
		}
		userDdnFavoritesSearchRepository.deleteAll();
		List<UserDdnFavorites> userDdnFavoritesList = userDdnFavoritesRepository.findAll();
		if (userDdnFavoritesList != null && userDdnFavoritesList.size() > 0) {
			userDdnFavoritesSearchRepository.saveAll(userDdnFavoritesList);
		}
		preferentialActivitiesSearchRepository.deleteAll();
		List<PreferentialActivities> preferentialActivitiesList = preferentialActivitiesRepository.findAll();
		if (preferentialActivitiesList != null && preferentialActivitiesList.size() > 0) {
			preferentialActivitiesSearchRepository.saveAll(preferentialActivitiesList);
		}
		sysRecruitmentInformationSearchRepository.deleteAll();
		List<SysRecruitmentInformation> sysRecruitmentInformationList = sysRecruitmentInformationRepository.findAll();
		if (sysRecruitmentInformationList != null && sysRecruitmentInformationList.size() > 0) {
			sysRecruitmentInformationSearchRepository.saveAll(sysRecruitmentInformationList);
		}
		topicSearchRepository.deleteAll();
		List<Topic> topicList = topicRepository.findAll();
		if (topicList != null && topicList.size() > 0) {
			topicSearchRepository.saveAll(topicList);
		}
		topicFabulousSearchRepository.deleteAll();
		List<TopicFabulous> topicFabulousList = topicFabulousRepository.findAll();
		if (topicFabulousList != null && topicFabulousList.size() > 0) {
			topicFabulousSearchRepository.saveAll(topicFabulousList);
		}
		topicCommentSearchRepository.deleteAll();
		List<TopicComment> topicCommentList = topicCommentRepository.findAll();
		if (topicCommentList != null && topicCommentList.size() > 0) {
			topicCommentSearchRepository.saveAll(topicCommentList);
		}
		topicForwardSearchRepository.deleteAll();
		List<TopicForward> topicForwardList = topicForwardRepository.findAll();
		if (topicForwardList != null && topicForwardList.size() > 0) {
			topicForwardSearchRepository.saveAll(topicForwardList);
		}
		topicViewedSearchRepository.deleteAll();
		List<TopicViewed> topicViewedList = topicViewedRepository.findAll();
		if (topicViewedList != null && topicViewedList.size() > 0) {
			topicViewedSearchRepository.saveAll(topicViewedList);
		}
		System.out.println("-------init data-----------------");

		return "success";
	}

}