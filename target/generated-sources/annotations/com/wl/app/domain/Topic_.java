package com.wl.app.domain;

import com.wl.app.domain.enumeration.Status;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Topic.class)
public abstract class Topic_ extends com.wl.app.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Topic, UserInfo> userInfo;
	public static volatile SingularAttribute<Topic, Integer> viewedCount;
	public static volatile SingularAttribute<Topic, Long> id;
	public static volatile SingularAttribute<Topic, Instant> published;
	public static volatile SingularAttribute<Topic, Topic> source;
	public static volatile SingularAttribute<Topic, String> title;
	public static volatile SingularAttribute<Topic, Integer> forwardCount;
	public static volatile SingularAttribute<Topic, Integer> fabulousCount;
	public static volatile SingularAttribute<Topic, String> content;
	public static volatile SingularAttribute<Topic, Boolean> forwarded;
	public static volatile SingularAttribute<Topic, Integer> commentCount;
	public static volatile SingularAttribute<Topic, Status> status;

}

