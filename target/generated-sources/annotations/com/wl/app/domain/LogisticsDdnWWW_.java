package com.wl.app.domain;

import com.wl.app.domain.enumeration.Status;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LogisticsDdnWWW.class)
public abstract class LogisticsDdnWWW_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<LogisticsDdnWWW, LogisticsDdn> logisticsDdn;
	public static volatile SingularAttribute<LogisticsDdnWWW, String> mobilePhone;
	public static volatile SingularAttribute<LogisticsDdnWWW, String> phone;
	public static volatile SingularAttribute<LogisticsDdnWWW, String> name;
	public static volatile SingularAttribute<LogisticsDdnWWW, String> remark;
	public static volatile SingularAttribute<LogisticsDdnWWW, Long> id;
	public static volatile SingularAttribute<LogisticsDdnWWW, String> contacts;
	public static volatile SingularAttribute<LogisticsDdnWWW, Status> status;

}

