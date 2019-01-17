package com.wl.app.domain;

import com.wl.app.domain.enumeration.Status;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LogisticsDdn.class)
public abstract class LogisticsDdn_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<LogisticsDdn, String> managerFullname;
	public static volatile SingularAttribute<LogisticsDdn, String> address;
	public static volatile SingularAttribute<LogisticsDdn, Boolean> goThereAndback;
	public static volatile SingularAttribute<LogisticsDdn, Boolean> auth;
	public static volatile SingularAttribute<LogisticsDdn, String> managerMobilePhone;
	public static volatile SingularAttribute<LogisticsDdn, Boolean> banner;
	public static volatile SingularAttribute<LogisticsDdn, String> pic;
	public static volatile SingularAttribute<LogisticsDdn, String> title;
	public static volatile SingularAttribute<LogisticsDdn, Boolean> good;
	public static volatile SingularAttribute<LogisticsDdn, Boolean> home;
	public static volatile SingularAttribute<LogisticsDdn, String> managerPhone;
	public static volatile SingularAttribute<LogisticsDdn, String> www;
	public static volatile SingularAttribute<LogisticsDdn, Boolean> specialTransport;
	public static volatile SingularAttribute<LogisticsDdn, String> throughCity;
	public static volatile SingularAttribute<LogisticsDdn, String> coverCity;
	public static volatile SingularAttribute<LogisticsDdn, Long> id;
	public static volatile SingularAttribute<LogisticsDdn, String> businessPhone;
	public static volatile SingularAttribute<LogisticsDdn, Boolean> vip;
	public static volatile SingularAttribute<LogisticsDdn, String> locationCity;
	public static volatile SingularAttribute<LogisticsDdn, Status> status;

}

