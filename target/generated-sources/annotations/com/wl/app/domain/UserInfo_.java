package com.wl.app.domain;

import com.wl.app.domain.enumeration.Status;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserInfo.class)
public abstract class UserInfo_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<UserInfo, String> nickName;
	public static volatile SingularAttribute<UserInfo, String> openId;
	public static volatile SingularAttribute<UserInfo, String> photo;
	public static volatile SingularAttribute<UserInfo, String> registerSum;
	public static volatile SingularAttribute<UserInfo, String> mobilePhone;
	public static volatile SingularAttribute<UserInfo, Integer> goodsSourceCount;
	public static volatile SingularAttribute<UserInfo, Integer> integral;
	public static volatile SingularAttribute<UserInfo, Long> id;
	public static volatile SingularAttribute<UserInfo, String> fullname;
	public static volatile SingularAttribute<UserInfo, User> user;
	public static volatile SingularAttribute<UserInfo, Instant> lastLoginedDate;
	public static volatile SingularAttribute<UserInfo, Instant> registerDate;
	public static volatile SingularAttribute<UserInfo, Status> status;

}

