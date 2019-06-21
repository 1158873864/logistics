package com.wl.app.service.util;

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

public class TestUtil {

	public static void main(String[] args) {
		generateSynchronousDataCode();
	}

	public static void generateSynchronousDataCode() {
		String[] domains = new String[] { 
				UserInfo.class.getSimpleName(), 
				GoodsSource.class.getSimpleName(), 
				LogisticsDdnPic.class.getSimpleName(), 
				LogisticsDdn.class.getSimpleName(), 
				LogisticsDdnWWW.class.getSimpleName(), 
				IntegralRule.class.getSimpleName(), 
				IntegralChangeRecord.class.getSimpleName(), 
				Goods.class.getSimpleName(), 
				GoodsExchange.class.getSimpleName(), 
				UserDdnFavorites.class.getSimpleName(), 
				PreferentialActivities.class.getSimpleName(), 
				SysRecruitmentInformation.class.getSimpleName(), 
				Topic.class.getSimpleName(), 
				TopicFabulous.class.getSimpleName(), 
				TopicComment.class.getSimpleName(), 
				TopicForward.class.getSimpleName(), 
				TopicViewed.class.getSimpleName()

		};

		for (String domain : domains) {
			String upper = domain;
			String lowerCase = domain.substring(0, 1).toLowerCase() + domain.substring(1);

			System.out.println("private final " + upper + "Repository " + lowerCase + "Repository;");
			System.out.println("private final " + upper + "SearchRepository " + lowerCase + "SearchRepository;");
		}

		for (String domain : domains) {
			String upper = domain;
			String lowerCase = domain.substring(0, 1).toLowerCase() + domain.substring(1);

			System.out.println("" + upper + "Repository " + lowerCase + "Repository,");
			System.out.println("" + upper + "SearchRepository " + lowerCase + "SearchRepository,");
		}

		for (String domain : domains) {
			String upper = domain;
			String lowerCase = domain.substring(0, 1).toLowerCase() + domain.substring(1);

			System.out.println("this." + lowerCase + "Repository = " + lowerCase + "Repository;");
			System.out.println("this." + lowerCase + "SearchRepository = " + lowerCase + "SearchRepository;");
		}
		System.out.println("---------------------------------------------------------------------");

		for (String domain : domains) {
			String upper = domain;
			String lowerCase = domain.substring(0, 1).toLowerCase() + domain.substring(1);

			System.out.println(lowerCase + "SearchRepository.deleteAll();");
			System.out.println("List<" + upper + "> " + lowerCase + "List = " + lowerCase + "Repository.findAll();");
			System.out.println("if (" + lowerCase + "List != null && " + lowerCase + "List.size() > 0) {");
			System.out.println("" + lowerCase + "SearchRepository.save(" + lowerCase + "List);");
			System.out.println("}");
		}

		for (String domain : domains) {
			String lowerCase = domain.substring(0, 1).toLowerCase() + domain.substring(1);

			System.out.println(lowerCase + "SearchRepository.deleteAll();");
			System.out.println(lowerCase + "Repository.deleteAll();");
		}

		for (String domain : domains) {
			String upper = domain;
			String lowerCase = domain.substring(0, 1).toLowerCase() + domain.substring(1);

			System.out.println("if(entityName.equals(\"" + upper + "\")){");
			System.out.println(lowerCase + "SearchRepository.deleteAll();");
			System.out.println(lowerCase + "Repository.deleteAll();");
			System.out.println("}");
		}
	}

}
