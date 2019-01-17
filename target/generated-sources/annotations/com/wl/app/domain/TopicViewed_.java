package com.wl.app.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TopicViewed.class)
public abstract class TopicViewed_ {

	public static volatile SingularAttribute<TopicViewed, UserInfo> userInfo;
	public static volatile SingularAttribute<TopicViewed, Topic> topic;
	public static volatile SingularAttribute<TopicViewed, Long> id;
	public static volatile SingularAttribute<TopicViewed, Instant> oDateTime;

}

