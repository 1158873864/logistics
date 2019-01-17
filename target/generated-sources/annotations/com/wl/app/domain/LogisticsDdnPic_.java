package com.wl.app.domain;

import com.wl.app.domain.enumeration.Status;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LogisticsDdnPic.class)
public abstract class LogisticsDdnPic_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<LogisticsDdnPic, String> path;
	public static volatile SingularAttribute<LogisticsDdnPic, LogisticsDdn> logisticsDdn;
	public static volatile SingularAttribute<LogisticsDdnPic, String> remark;
	public static volatile SingularAttribute<LogisticsDdnPic, Long> id;
	public static volatile SingularAttribute<LogisticsDdnPic, String> title;
	public static volatile SingularAttribute<LogisticsDdnPic, Status> status;

}

